package com.example.cuong.nghenhac;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.cuong.learnenglish.NetworkController;
import com.example.cuong.learnenglish.R;
import com.example.cuong.learnenglish.RecyclerItemClickListener;
import org.json.JSONArray;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

public class Main_Nghe_Nhac_Youtube extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {

    RequestQueue requestQueue;
    String url = "https://youtubemusic-a7160.firebaseio.com/.json";
    RecyclerView recyclerView;
    SwipeRefreshLayout refreshLayout;
    List<YoutubeMV> musicList = new ArrayList<YoutubeMV>();
    Custom_List_Youtube_MV reclycleItemYouTube;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_youtube_mv);
        getSupportActionBar().hide();
        refreshLayout = (SwipeRefreshLayout)findViewById(R.id.swipe_refresh_layout);
        recyclerView = (RecyclerView)findViewById(R.id.recyclerview);
        reclycleItemYouTube = new Custom_List_Youtube_MV(this, musicList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(reclycleItemYouTube);
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
        if(musicList != null) {
            musicList.clear();
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
                        YoutubeMV music = new YoutubeMV();
                        music.setTenClip(object.getString("tieude"));
                        music.setLinkYT(object.getString("link"));
                        music.setImgURL(object.getString("image"));
                        music.setThoigian(object.getString("duration"));
                        musicList.add(music);
                    }
                    catch (Exception e) {
                        e.printStackTrace();
                        }
                    }
                    reclycleItemYouTube.notifyDataSetChanged();
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
                Intent intent = new Intent(Main_Nghe_Nhac_Youtube.this, Youtube_MV_API.class);
                intent.putExtra("link", musicList.get(position).getLinkYT());
                startActivity(intent);
            }

            @Override
            public void onLongItemClick(View view, int position) {

            }
        }));
    }
}
