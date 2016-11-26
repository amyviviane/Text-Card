package com.example.amylilian.text_card;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class StudyResultActivity extends AppCompatActivity {

    ImageButton gotostudy;

    //add intent
    private Context context;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_study_result);

        gotostudy = (ImageButton) findViewById(R.id.gotoButton_studyresult);

        context = this;
        gotostudy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotostudy.setImageResource(R.drawable.btm_end_b);

                intent = new Intent(context , StudyActivity.class);
                startActivity(intent);
            }
        });
    }
}
