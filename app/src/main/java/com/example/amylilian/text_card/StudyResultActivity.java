package com.example.amylilian.text_card;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageButton;

public class StudyResultActivity extends AppCompatActivity {

    ImageButton gotostudy;
    ImageButton gototest;

    //add intent
    private Context context;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_study_result);

        gotostudy = (ImageButton) findViewById(R.id.gotoButton_studyresult);
        gototest = (ImageButton) findViewById(R.id.imageButton);

        context = this;
        gototest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gototest.setImageResource(R.drawable.btm_gotest_b);

                intent = new Intent(context , ReviewTestActivity.class);
                startActivity(intent);
                finish();
            }
        });
        gotostudy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotostudy.setImageResource(R.drawable.btm_golearn_b);

                intent = new Intent(context , StudyActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
    //返回
    @Override
    public void onBackPressed() {
    }
}
