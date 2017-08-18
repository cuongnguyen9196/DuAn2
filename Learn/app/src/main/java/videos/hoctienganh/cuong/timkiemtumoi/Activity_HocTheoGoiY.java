package videos.hoctienganh.cuong.timkiemtumoi;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
import com.github.nkzawa.emitter.Emitter;
import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import videos.hoctienganh.cuong.Adapter.Adapter_Lesson;
import videos.hoctienganh.cuong.Model.Model_Lesson;
import videos.hoctienganh.cuong.learnenglish.R;

public class Activity_HocTheoGoiY extends AppCompatActivity {

    RecyclerView re;
    List<Model_Lesson> listitem;
    Adapter_Lesson adapter_lesson;
    private Socket mSocket;
    {
        try {
            mSocket = IO.socket("http://192.168.100.4:3000");
        } catch (URISyntaxException e) {}
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_tim_kiem_layout_2);
        getSupportActionBar().hide();
        mSocket.connect();
        re= (RecyclerView) findViewById(R.id.listHoctheogoiy);
        re.setHasFixedSize(true);
        re.setLayoutManager(new LinearLayoutManager(this));
        listitem = new ArrayList<>();
        new LayDuLieu().execute("http://192.168.100.4:3000");
    }
    public class LayDuLieu extends AsyncTask<String, Void, List<Model_Lesson>> {

        @Override
        protected void onPostExecute(List<Model_Lesson> model_lessons) {
            super.onPostExecute(model_lessons);
            adapter_lesson = new Adapter_Lesson(model_lessons, Activity_HocTheoGoiY.this);
            re.setAdapter(adapter_lesson);
        }

        @Override
        protected List<Model_Lesson> doInBackground(String... params) {
            try {
                OkHttpClient okHttpClient = new OkHttpClient();
                Request request = new Request.Builder().url(params[0]).build();
                Response response = okHttpClient.newCall(request).execute();

                JSONArray jsonArray = new JSONArray(response.body().string());
                for (int i = 0; i < jsonArray.length(); i++) {
                    listitem.add(new Model_Lesson(
                            jsonArray.getJSONObject(i).getString("IdBai"),
                            jsonArray.getJSONObject(i).getString("TenBai")
                    ));
                }
            } catch (Exception e) {
            }
            return listitem;
        }
}}
