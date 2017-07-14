package com.example.cuong.hocquavideo;

import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import android.support.v7.app.AppCompatActivity;
import com.example.cuong.learnenglish.R;

public class Main_Video_Activity extends AppCompatActivity {

    private static final String url = "https://youtube-video-data-b6aaf.firebaseio.com/.json";
    private ProgressDialog pDialog;
    private List<English> engList = new ArrayList<English>();
    private ListView listView;
    private CustomListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main__video);
        getSupportActionBar().hide();
        listView = (ListView) findViewById(R.id.listview1);
        adapter = new CustomListAdapter(this, engList);
        listView.setAdapter(adapter);
        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Loading...");
        pDialog.show();
        final JsonArrayRequest movieReq = new JsonArrayRequest(url,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        hidePDialog();
                        for (int i = 0; i < response.length(); i++) {
                            try {
                                JSONObject obj = response.getJSONObject(i);
                                English eng = new English();
                                eng.setTitle(obj.getString("tieude"));
                                eng.setThumbnailUrl(obj.getString("image"));
                                eng.setVid_link(obj.getString("link"));
                                eng.setDuration(((Number)obj.get("thoigian")).doubleValue());
                                engList.add(eng);
                            }
                            catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        adapter.notifyDataSetChanged();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                hidePDialog();
            }
        });
        AppController.getInstance().addToRequestQueue(movieReq);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(Main_Video_Activity.this, Youtube_Activity.class);
                intent.putExtra("link", engList.get(i).getVid_link());
                startActivity(intent);
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        hidePDialog();
    }

    private void hidePDialog() {
        if (pDialog != null) {
            pDialog.dismiss();
            pDialog = null;
        }
    }
}
