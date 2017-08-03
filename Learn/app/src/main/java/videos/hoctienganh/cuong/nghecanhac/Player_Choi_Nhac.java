package videos.hoctienganh.cuong.nghecanhac;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.os.Handler;
import android.widget.SeekBar;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import java.util.List;
import java.util.concurrent.TimeUnit;
import videos.hoctienganh.cuong.learnenglish.R;


public class Player_Choi_Nhac extends AppCompatActivity {

    String tenbaihat, linknhac, linkImages, nameCasi;
    TextView textView1, textView2, textView3;
    MediaPlayer player;
    ImageView view1, view2, view3, view4, view5;
    double timeElapsed = 0, finalTime = 0;
    int forwardTime = 2000, backwardTime = 2000;
    Context context;
    Handler durationHandler = new Handler();
    SeekBar seekbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_player_choi_nhac);
        getSupportActionBar().hide();
        Bundle bundle = getIntent().getExtras();
        tenbaihat = bundle.getString("tenbai");
        linknhac = bundle.getString("link");
        linkImages = bundle.getString("image");
        nameCasi = bundle.getString("tencasi");
        textView1 = (TextView)findViewById(R.id.textbaihat);
        seekbar = (SeekBar)findViewById(R.id.seekbar1);
        textView2 = (TextView)findViewById(R.id.textcasi);
        textView3 = (TextView)findViewById(R.id.textTime);
        view1 = (ImageView)findViewById(R.id.imageSongs);
        view2 = (ImageView)findViewById(R.id.image1);
        view3 = (ImageView)findViewById(R.id.image2);
        view4 = (ImageView)findViewById(R.id.image3);
        view5 = (ImageView)findViewById(R.id.image4);
        textView1.setText(tenbaihat);
        textView2.setText(nameCasi);
        // load nhac
        player = MediaPlayer.create(Player_Choi_Nhac.this, Uri.parse(linknhac));
        finalTime = player.getDuration();
        seekbar.setMax((int) finalTime);
        seekbar.setClickable(false);
        // load anh
        Picasso.with(Player_Choi_Nhac.this).load(linkImages).into(view1);
        // nut rewind
        view2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if ((timeElapsed - backwardTime) > 0) {
                    timeElapsed = timeElapsed - backwardTime;
                    player.seekTo((int) timeElapsed);
                }
            }
        });
        // nut pause
        view3.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                player.pause();
            }
        });
        // nut play
        view4.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                player.start();
                timeElapsed = player.getCurrentPosition();
                seekbar.setProgress((int) timeElapsed);
                durationHandler.postDelayed(updateSeekBarTime, 100);
            }
        });
        // nut forward
        view5.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if ((timeElapsed + forwardTime) <= finalTime) {
                    timeElapsed = timeElapsed + forwardTime;
                    player.seekTo((int) timeElapsed);
                }
            }
        });
    }

    Runnable updateSeekBarTime = new Runnable() {
        public void run() {
            timeElapsed = player.getCurrentPosition();
            seekbar.setProgress((int) timeElapsed);
            double timeRemaining = finalTime - timeElapsed;
            textView3.setText("Duration : " + String.format("%d min : %d sec", TimeUnit.MILLISECONDS.toMinutes((long) timeRemaining),
                    TimeUnit.MILLISECONDS.toSeconds((long) timeRemaining) -
                            TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes((long) timeRemaining))));
            durationHandler.postDelayed(this, 100);
        }
    };

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            player.stop();
            player.release();
            player.reset();
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onPause() {
        if (SentToBackground(Player_Choi_Nhac.this)){
            player.stop();
            player.release();
            player.reset();
        }
        super.onPause();
    }

    private boolean SentToBackground(Player_Choi_Nhac player_choi_nhac) {
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> tasks = am.getRunningTasks(1);
        if (!tasks.isEmpty()) {
            ComponentName topActivity = tasks.get(0).topActivity;
            if (!topActivity.getPackageName().equals(context.getPackageName())) {
                return true;
            }
        }
        return false;
    }
}
