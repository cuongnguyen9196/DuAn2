package videos.hoctienganh.cuong.timkiemtumoi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.github.nkzawa.emitter.Emitter;
import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URISyntaxException;

import videos.hoctienganh.cuong.learnenglish.R;

public class BaiHocTheoGoiY extends AppCompatActivity {
    String id;
    TextView tomTat,NoiDung;
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
        getSupportActionBar().hide();
        AddControl();
        mSocket.connect();
        Event();
    }
    private  void AddControl(){
        tomTat = (TextView) findViewById(R.id.txt_TomTatNoidung);
        NoiDung = (TextView) findViewById(R.id.txt_NoiDung);

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
                        tomTat.setText(arr.getJSONObject(0).getString("TomTaTNoiDung"));
                        NoiDung.setText(arr.getJSONObject(0).getString("NoiDung"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    try {
                        tomTat.setText(data.getString("dulieu"));
                    } catch (JSONException e) {
                        return;
                    }
                }
            });
        }
    };
}
