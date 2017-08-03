package videos.hoctienganh.cuong.timkiemtumoi;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;
import com.github.nkzawa.emitter.Emitter;
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


public class Activity_Translate extends AppCompatActivity {
    Toolbar toolbar;
    EditText tucandich;
    TextView dulieutrave;
    RecyclerView re;
    List<Model_Lesson> listitem;
    Adapter_Lesson adapter_lesson;

    private Socket mSocket;

    {
        try {
            mSocket = IO.socket("http://192.168.100.4:3000");
        } catch (URISyntaxException e) {
        }
    }


    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_tim_kiem);
        getSupportActionBar().hide();
        AddControl();
        mSocket.connect();
        tucandich.setSelected(false);

        // Find the toolbar view inside the activity layout
        mSocket.on("data-tra-ve", LayduLieuReturn);



    }


    //dgoogle translate
    public void AddControl() {
        tucandich = (EditText) findViewById(R.id.edt_tran);
        dulieutrave = (TextView) findViewById(R.id.txt_show);

    }

    public void btn_dich(View v) {
        mSocket.emit("send-tu-de-dich", tucandich.getText().toString());
    }

    private Emitter.Listener LayduLieuReturn = new Emitter.Listener() {
        @Override
        public void call(final Object... args) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    JSONObject data = (JSONObject) args[0];
                    try {
                        dulieutrave.setText(data.getString("dulieu"));
                    } catch (JSONException e) {
                        return;
                    }
                }
            });
        }
    };

}
