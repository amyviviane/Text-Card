package com.example.amylilian.text_card;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class StudyContentEndActivity extends AppCompatActivity {

    ImageButton sound_botton;
    ImageButton previous_botton;
    ImageButton end_botton;
    TextView text1;

    //add intent
    private Context context;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_study_content_end);

        sound_botton = (ImageButton) findViewById(R.id.soundButton_studyend);
        previous_botton = (ImageButton) findViewById(R.id.previouspageButton_studyend);
        end_botton = (ImageButton) findViewById(R.id.endButton_studyend);

        //get temply text string
        String[] text_array = getResources().getStringArray(R.array.temp_text);

        //get total string[] long
        final int group_length = text_array.length - 1;

        //print first word
        text1.setText(text_array[group_length - 1]);

        context = this;
        previous_botton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                previous_botton.setImageResource(R.drawable.btm_left_b);

                //put value into Bundle
                final Bundle extras = new Bundle();
                extras.putInt("length",group_length);
                extras.putInt("count",group_length - 1);

                intent = new Intent(context, StudyContentMiddleActivity.class);
                intent.putExtras(extras);
                startActivity(intent);
            }
        });
        end_botton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                end_botton.setImageResource(R.drawable.btm_right_b);

                intent = new Intent(context, StudyResultActivity.class);
                startActivity(intent);
            }
        });
    }
}
