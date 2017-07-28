package videos.hoctienganh.cuong.hocquavideo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import videos.hoctienganh.cuong.learnenglish.R;

public class Error_Hoc_Qua_Youtube extends AppCompatActivity {

    String vid_link;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_video_view);
        Bundle bundle1 = getIntent().getExtras();
        vid_link = bundle1.getString("link");
        WebView webView = (WebView)findViewById(R.id.web);
        webView.setWebViewClient(new CustomWeb());
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webView.loadUrl(vid_link);
    }

    private class CustomWeb extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }
}
