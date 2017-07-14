package com.example.cuong.learnenglish;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import com.example.cuong.hocquavideo.Main_Video_Activity;

public class MainScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);
        getSupportActionBar().hide();
        final LinearLayout textView1 = (LinearLayout) findViewById(R.id.textView1);
        final LinearLayout textView2 = (LinearLayout) findViewById(R.id.textView2);
        final LinearLayout textView3 = (LinearLayout) findViewById(R.id.textView3);
        final LinearLayout textView4 = (LinearLayout) findViewById(R.id.textView4);
        textView1.setVisibility(View.VISIBLE);
        textView2.setVisibility(View.VISIBLE);
        textView2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainScreen.this, Main_Video_Activity.class);
                startActivity(intent);
            }
        });
        textView3.setVisibility(View.VISIBLE);
        textView4.setVisibility(View.VISIBLE);
        final BottomNavigationView bottomNavigationView = (BottomNavigationView)findViewById(R.id.navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment selectedFragment = null;
                switch (item.getItemId()) {
                    case R.id.action_item1:
                        textView1.setVisibility(View.GONE);
                        textView2.setVisibility(View.GONE);
                        textView3.setVisibility(View.GONE);
                        textView4.setVisibility(View.GONE);
                        selectedFragment = ItemOneFragment.newInstance();
                        break;
                    case R.id.action_item2:
                        textView1.setVisibility(View.GONE);
                        textView2.setVisibility(View.GONE);
                        textView3.setVisibility(View.GONE);
                        textView4.setVisibility(View.GONE);
                        selectedFragment = ItemTwoFragment.newInstance();
                        break;
                    case R.id.action_item3:
                        textView1.setVisibility(View.GONE);
                        textView2.setVisibility(View.GONE);
                        textView3.setVisibility(View.GONE);
                        textView4.setVisibility(View.GONE);
                        selectedFragment = ItemThreeFragment.newInstance();
                        break;
                        }
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_layout, selectedFragment);
                transaction.commit();
                return true;
            }
        });

        bottomNavigationView.setOnNavigationItemReselectedListener(new BottomNavigationView.OnNavigationItemReselectedListener() {

            @Override
            public void onNavigationItemReselected(@NonNull MenuItem item) {
                Fragment selectedFragment = null;
                switch (item.getItemId()) {
                    case R.id.action_item1:
                        textView1.setVisibility(View.VISIBLE);
                        textView2.setVisibility(View.VISIBLE);
                        textView3.setVisibility(View.VISIBLE);
                        textView4.setVisibility(View.VISIBLE);
                        selectedFragment = ItemOneFragment.newInstance();
                        break;
                    case R.id.action_item2:
                        textView1.setVisibility(View.VISIBLE);
                        textView2.setVisibility(View.VISIBLE);
                        textView3.setVisibility(View.VISIBLE);
                        textView4.setVisibility(View.VISIBLE);
                        selectedFragment = ItemTwoFragment.newInstance();
                        break;
                    case R.id.action_item3:
                        textView1.setVisibility(View.VISIBLE);
                        textView2.setVisibility(View.VISIBLE);
                        textView3.setVisibility(View.VISIBLE);
                        textView4.setVisibility(View.VISIBLE);
                        selectedFragment = ItemThreeFragment.newInstance();
                        break;
                }
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_layout, selectedFragment);
                transaction.hide(selectedFragment);
                transaction.commit();
            }
        });
    }
}