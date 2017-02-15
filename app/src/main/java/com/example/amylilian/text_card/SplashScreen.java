package com.example.amylilian.text_card;

import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;


public class SplashScreen extends AppCompatActivity {
    ImageButton i;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        i = (ImageButton) findViewById(R.id.imageButton11);

        context = this;
        i.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context , MainActivity.class);
                startActivity(intent);
            }
        });
/*
        //12.02新增:隱藏Action Bar
        ActionBar actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        //actionBar.setIcon(R.drawable.help);
        actionBar.hide();
*/

        //移除狀態列
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        //移除標題
        //getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
        //        WindowManager.LayoutParams.FLAG_FULLSCREEN);

        /*new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent().setClass(SplashScreen.this, MainActivity.class));
            }
        }, 3000);*/

    }
}
