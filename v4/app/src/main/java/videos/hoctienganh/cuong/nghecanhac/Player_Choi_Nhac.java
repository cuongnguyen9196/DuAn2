package videos.hoctienganh.cuong.nghecanhac;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import videos.hoctienganh.cuong.learnenglish.R;

public class Player_Choi_Nhac extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_player_choi_nhac);
        getSupportActionBar().hide();
    }
}
