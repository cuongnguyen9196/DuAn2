package videos.hoctienganh.cuong.timkiemtumoi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

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
import videos.hoctienganh.cuong.Adapter.Adapter_KQQuestion;
import videos.hoctienganh.cuong.Model.Model_cauTL;
import videos.hoctienganh.cuong.Model.Model_hocTheochude;
import videos.hoctienganh.cuong.learnenglish.R;

public class Main_CauTL extends AppCompatActivity {
    String id;
    RecyclerView reList;
    List<Model_cauTL> list ;

    private Socket mSocket;
    {
        try {
            mSocket = IO.socket("http://192.168.100.4:3000");
        } catch (URISyntaxException e) {}
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main__cau_tl);
        getSupportActionBar().hide();
        mSocket.connect();
        reList = (RecyclerView) findViewById(R.id.reTL);
        reList.setHasFixedSize(true);
        reList.setLayoutManager(new LinearLayoutManager(this));

        Event();
    }
    public  void Event(){
        Bundle b = getIntent().getExtras();
        id =(b.getString("IdQuest"));
        mSocket.emit("send-data-get-cautl",id);
        mSocket.on("data_cauTL",getDataEnd);
    }

    private Emitter.Listener getDataEnd = new Emitter.Listener() {
        @Override
        public void call(final Object... args) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    JSONObject data = (JSONObject) args[0];
                    try {
                        JSONArray arr = data.getJSONArray("data_cauTL_Eng");
                        list = new ArrayList<Model_cauTL>();
                        for ( int i = 0 ; i<arr.length();i++){
                            list.add(new Model_cauTL(
                                    arr.getJSONObject(i).getString("textTL")
                            ));

                        }

                        Adapter_KQQuestion  adapter_kqQuestion= new Adapter_KQQuestion(Main_CauTL.this,list);
                        reList.setAdapter(adapter_kqQuestion);
                    } catch (JSONException e) {
                        return;
                    }
                }
            });
        }
    };

}
