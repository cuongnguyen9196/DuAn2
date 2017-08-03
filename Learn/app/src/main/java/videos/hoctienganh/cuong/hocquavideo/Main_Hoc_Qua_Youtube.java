package videos.hoctienganh.cuong.hocquavideo;

import org.json.JSONArray;
import org.json.JSONObject;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import android.support.v7.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.List;
import videos.hoctienganh.cuong.customlayout.Custom_List_Videos_Adapter;
import videos.hoctienganh.cuong.learnenglish.R;
import videos.hoctienganh.cuong.network.NetworkController;
import videos.hoctienganh.cuong.network.RecyclerItemClickListener;
import videos.hoctienganh.cuong.objects.English;

public class Main_Hoc_Qua_Youtube extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {

    RequestQueue requestQueue;
    String url = "https://youtube-video-data-b6aaf.firebaseio.com/.json";
    RecyclerView recyclerView;
    SwipeRefreshLayout refreshLayout;
    List<English> engList = new ArrayList<English>();
    Custom_List_Videos_Adapter listAdapter;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_youtube_mv);
        getSupportActionBar().hide();
        refreshLayout = (SwipeRefreshLayout)findViewById(R.id.swipe_refresh_layout);
        recyclerView = (RecyclerView)findViewById(R.id.recyclerview);
        listAdapter = new Custom_List_Videos_Adapter(this, engList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(listAdapter);
        refreshLayout.setOnRefreshListener(this);
        refreshLayout.post(new Runnable() {

            @Override
            public void run() {
                refreshLayout.setRefreshing(true);
                data();
            }
        });
    }

    @Override
    public void onRefresh() {
        data();
        if(engList != null) {
            engList.clear();
        }
    }

    private void data() {
        refreshLayout.setRefreshing(true);
        String totalUrl = url;
        requestQueue = NetworkController.getInstance(this).getRequestQueue();
        JsonArrayRequest arrayRequest = new JsonArrayRequest(totalUrl, new Response.Listener<JSONArray>() {

            @Override
            public void onResponse(JSONArray response) {
                if(response.length() > 0) {
                    for (int i = 0; i < response.length(); i++) {
                        try {
                            JSONObject object = response.getJSONObject(i);
                            English eng = new English();
                            eng.setTitle(object.getString("tieude"));
                            eng.setThumbnailUrl(object.getString("image"));
                            eng.setVid_link(object.getString("link"));
                            eng.setDuration(((Number)object.get("thoigian")).doubleValue());
                            engList.add(eng);
                        }
                        catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    listAdapter.notifyDataSetChanged();
                }
                refreshLayout.setRefreshing(false);
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                refreshLayout.setRefreshing(false);
            }
        });
        requestQueue.add(arrayRequest);
        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(context, recyclerView,
                new RecyclerItemClickListener.OnItemClickListener() {

                    @Override
                    public void onItemClick(View view, int position) {
                        Intent intent = new Intent(Main_Hoc_Qua_Youtube.this, Youtube_Learn_API.class);
                        intent.putExtra("link", engList.get(position).getVid_link());
                        startActivity(intent);
                    }

                    @Override
                    public void onLongItemClick(View view, int position) {

                    }
            }));
        }
    }
