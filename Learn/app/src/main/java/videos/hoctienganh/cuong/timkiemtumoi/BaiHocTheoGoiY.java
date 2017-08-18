package videos.hoctienganh.cuong.timkiemtumoi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.github.nkzawa.emitter.Emitter;
import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import videos.hoctienganh.cuong.Adapter.Adapter_Hoctheochude;
import videos.hoctienganh.cuong.Model.Model_hocTheochude;
import videos.hoctienganh.cuong.learnenglish.R;

public class BaiHocTheoGoiY extends AppCompatActivity {
    String id;
    RecyclerView relist;
    List<Model_hocTheochude> list;
    Adapter_Hoctheochude adapterHoctheochude;
    private Socket mSocket;
    {
        try {
            mSocket = IO.socket("http://192.168.100.4:3000");
        } catch (URISyntaxException e) {}
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bai_hoc_theo_goi_y);
        relist = (RecyclerView) findViewById(R.id.reTheoChude);
        relist.setHasFixedSize(true);
        relist.setLayoutManager(new LinearLayoutManager(this));

        mSocket.connect();
        Event();


    }

    private void Event(){
        Bundle b = getIntent().getExtras();
        id =(b.getString("idls"));
        mSocket.emit("send-id-du-lieu",id);
        mSocket.on("data-baitap", LayduLieuReturn);
    }
    private Emitter.Listener LayduLieuReturn = new Emitter.Listener() {
        @Override
        public void call(final Object... args) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    JSONObject data = (JSONObject) args[0];
                    try {
                        JSONArray arr = data.getJSONArray("databai");

                        list=new ArrayList<Model_hocTheochude>();
                        for (int i = 0 ; i <arr.length() ; i++){
                            list.add(new Model_hocTheochude(
                             arr.getJSONObject(i).getString("IdQues"),
                             arr.getJSONObject(i).getString("CauHoi"),
                                    arr.getJSONObject(i).getString("IdBai")
                            ));


                        }
                        adapterHoctheochude = new Adapter_Hoctheochude(BaiHocTheoGoiY.this,list);
                        relist.setAdapter(adapterHoctheochude);
                    } catch (JSONException e) {
                        return;
                    }
                }
            });
        }
    };
}
