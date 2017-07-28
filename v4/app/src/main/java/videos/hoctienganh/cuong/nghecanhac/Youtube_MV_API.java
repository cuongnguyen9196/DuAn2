package videos.hoctienganh.cuong.nghecanhac;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.widget.Toast;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerFragment;
import videos.hoctienganh.cuong.learnenglish.R;

public class Youtube_MV_API extends YouTubeBaseActivity {

    public static final String API_KEY = "AIzaSyB1IOKaz5onjCR_nBzRzYncDCGmjKtYLx8";
    public String link_yt;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_youtube_main);
        Bundle bundle = getIntent().getExtras();
        link_yt = bundle.getString("link");
        FragmentManager fm = getFragmentManager();
        String tag = YouTubePlayerFragment.class.getSimpleName();
        YouTubePlayerFragment playerFragment = (YouTubePlayerFragment) fm.findFragmentByTag(tag);
        if (playerFragment == null) {
            FragmentTransaction ft = fm.beginTransaction();
            playerFragment = YouTubePlayerFragment.newInstance();
            ft.add(android.R.id.content, playerFragment, tag);
            ft.commit();
        }

        playerFragment.initialize(API_KEY, new YouTubePlayer.OnInitializedListener() {

            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                youTubePlayer.cueVideo(link_yt);
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
                try {
                    Toast.makeText(Youtube_MV_API.this, "Error while initializing YouTube", Toast.LENGTH_SHORT).show();
                    final String totalString = "https://www.youtube.com/watch?v=" + link_yt;
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {

                        @Override
                        public void run() {
                            finish();
                            Intent intent = new Intent(Youtube_MV_API.this, ErrorYoutubeMusic.class);
                            intent.putExtra("link", totalString);
                            startActivity(intent);
                        }
                    }, 3000);
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}