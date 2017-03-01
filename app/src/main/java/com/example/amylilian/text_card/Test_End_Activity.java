package com.example.amylilian.text_card;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class Test_End_Activity extends AppCompatActivity {

    //correct number
    int correct;

    //add intent
    private Context context;
    private Intent intent;

    //add layout item
    TextView rightcount;
    ImageButton testgo;
    ImageButton gotostudy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test__end_);

        rightcount = (TextView) findViewById(R.id.textView22);
        testgo = (ImageButton) findViewById(R.id.imageButton9);
        gotostudy = (ImageButton) findViewById(R.id.imageButton10);

        //get bundle
        Bundle extras = getIntent().getExtras();
        correct = extras.getInt("correct");
        rightcount.setText(correct + "");

        context = this;

        testgo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                testgo.setImageResource(R.drawable.btm2_gotest_b);
                intent = new Intent(context , ReviewTestActivity.class);
                startActivity(intent);
                finish();
            }
        });

        gotostudy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotostudy.setImageResource(R.drawable.btm2_golearn_b);
                intent = new Intent(context , StudyActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
