package com.example.amylilian.text_card;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class StudyContentActivity extends AppCompatActivity {

    ImageButton sound_botton;
    ImageButton next_botton;
    TextView text1;

    //add intent
    private Context context;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_study_content);

        //get value
        sound_botton = (ImageButton) findViewById(R.id.soundButton_studyfirst);
        next_botton = (ImageButton) findViewById(R.id.nextpageButton_studyfirst);
        text1 = (TextView) findViewById(R.id.text1View_studyfirst);

        //get temply text string
        String[] text_array = getResources().getStringArray(R.array.temp_text);

        //get total string[] long
        final int group_length = text_array.length;

        //print first word
        text1.setText(text_array[0]);



        context = this;
        next_botton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                next_botton.setImageResource(R.drawable.btm_right_b);

                //put value into Bundle
                final Bundle extras = new Bundle();
                extras.putInt("length",group_length);
                extras.putInt("count",1);

                intent = new Intent(context , StudyContentMiddleActivity.class);
                intent.putExtras(extras);
                startActivity(intent);
            }
        });
    }
}
