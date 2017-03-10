package com.example.amylilian.text_card;

import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;


public class SplashScreen extends AppCompatActivity {
    ImageButton i;
    private Context context;
    TextView t;
    boolean change;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        i = (ImageButton) findViewById(R.id.imageButton11);
        t = (TextView) findViewById(R.id.ex0);

        change = false;
        Timer timer = new Timer();

        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if(change){
                            change = false;
                            t.setTextColor(Color.LTGRAY);
                        }else{
                            change = true;
                            t.setTextColor(Color.GRAY);
                        }
                    }
                });

            }
        };
        timer.schedule(task,1,1000);
        context = this;
        i.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context , MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }
}
