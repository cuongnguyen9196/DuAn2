package videos.hoctienganh.cuong.learnenglish;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        getSupportActionBar().hide();
        Thread bamgio = new Thread() {
            public void run() {
                try {
                    sleep(4000);
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
                finally {
                    Intent intent = new Intent(getApplicationContext(), Main_Activity_Home.class);
                    startActivity(intent);
                }
            }
        };
        bamgio.start();
    }

    protected void onPause() {
        super.onPause();
        finish();
    }
}
