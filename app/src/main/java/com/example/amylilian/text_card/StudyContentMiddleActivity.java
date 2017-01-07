package com.example.amylilian.text_card;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class StudyContentMiddleActivity extends AppCompatActivity {

    ImageButton sound_botton;
    ImageButton previous_botton;
    ImageButton next_botton;
    TextView text1;
    private MediaPlayer mediaPlayer;

    //add intent
    private Context context;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_study_content_middle);

        //get value
        sound_botton = (ImageButton) findViewById(R.id.soundButton_studymiddle);
        previous_botton = (ImageButton) findViewById(R.id.previouspageButton_studymiddle);
        next_botton = (ImageButton) findViewById(R.id.nextpageButton_studymiddle);
        text1 = (TextView) findViewById(R.id.text1View_studymiddle);

        //get temply text string
        //String[] text_array = getResources().getStringArray(R.array.temp_text);

        //get bundle
        Bundle extras = getIntent().getExtras();
        int length = extras.getInt("length");
        final int count = extras.getInt("count");
        final String[] text_array = extras.getStringArray("text");

        //get total string[] long
        final int group_length = text_array.length;

        //print word
        text1.setText(text_array[count]);

        context = this;
        previous_botton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                previous_botton.setImageResource(R.drawable.btm_left_b);

                if ((count - 1) == 0){
                    intent = new Intent(context, StudyContentActivity.class);
                    startActivity(intent);
                }else {
                    //put value into Bundle
                    final Bundle extras = new Bundle();
                    extras.putInt("length",group_length);
                    extras.putInt("count",count - 1);
                    extras.putStringArray("text",text_array);

                    intent = new Intent(context, StudyContentMiddleActivity.class);
                    intent.putExtras(extras);
                    startActivity(intent);
                }
            }
        });
        next_botton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                next_botton.setImageResource(R.drawable.btm_right_b);

                if ((count + 1) == group_length - 1){ //
                    //put value into Bundle(新增2016.11.30)(應該是不可少的)
                    final Bundle extras = new Bundle();
                    extras.putInt("length",group_length);
                    extras.putInt("count",count + 1);
                    extras.putStringArray("text",text_array);

                    intent = new Intent(context, StudyContentEndActivity.class);
                    intent.putExtras(extras);
                    startActivity(intent);
                    //試試finish
                    finish();
                }else {
                    //put value into Bundle
                    final Bundle extras = new Bundle();
                    extras.putInt("length",group_length);
                    extras.putInt("count",count + 1);
                    extras.putStringArray("text",text_array);

                    intent = new Intent(context, StudyContentMiddleActivity.class);
                    intent.putExtras(extras);
                    startActivity(intent);
                    //試試finish
                    finish();
                }
            }
        });
        sound_botton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer = new MediaPlayer();
                mediaPlayer = MediaPlayer.create(context,R.raw.sgalvp);
                mediaPlayer.start();
            }
        });
    }
    //返回
    //@Override
    //public void onBackPressed() {
    //}

}
