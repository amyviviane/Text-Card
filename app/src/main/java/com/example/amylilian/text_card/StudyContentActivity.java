package com.example.amylilian.text_card;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import java.io.IOException;


public class StudyContentActivity extends AppCompatActivity {

    ImageButton sound_botton;
    ImageButton next_botton;
    TextView text1;

    //add intent
    private Context context;
    private Intent intent;
    private MediaPlayer mediaPlayer;
    private int starttime;
    private int duration;

    private static final String TAG = "StudyContentActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_study_content);

        //get value
        sound_botton = (ImageButton) findViewById(R.id.soundButton_studyfirst);
        next_botton = (ImageButton) findViewById(R.id.nextpageButton_studyfirst);
        text1 = (TextView) findViewById(R.id.text1View_studyfirst);

        //get temply text string
        //String[] text_array = getResources().getStringArray(R.array.temp_text);

        //get bundle
        Bundle extras = getIntent().getExtras();
        final String[] text_array = extras.getStringArray("text");

        //get total string[] long
        final int group_length = text_array.length;

        /*try{
            //cursor = db.query(Table_Name, new String[]{ID,TRL}, ID + "< 10" , null, null, null, ID);
            //cursor = db.query(Table_Name, null, ID + " < 10" , new String[]{ID,TRL}, null, null, ID);
            // 印出資料表
            Cursor c = db.rawQuery("SELECT name FROM sqlite_master WHERE type='table'", null);
            System.out.println(db.getPath());
            if (c.moveToFirst()) {
                while ( !c.isAfterLast() ) {
                    System.out.println(c.getString( c.getColumnIndex("name")));
                    c.moveToNext();
                }
            }


            cursor = db.query(Table_Name,new String[]{ID,TRL}, ID + " < ?" ,  new String[]{Integer.toString(10)}, null, null, ID);

            if (cursor != null){
                if (cursor.moveToFirst()) {
                    String trl = cursor.getString(cursor.getColumnIndex(TRL));
                    text1.setText(trl);
//                    while ( !cursor.isAfterLast() ) {
//                        int id = cursor.getInt(cursor.getColumnIndex(ID));
//                        String trl = cursor.getString(cursor.getColumnIndex(TRL));
//                        cursor.moveToNext();
//                        System.out.println(id + ":" + trl);
//                    }
                }
            }
        } finally {
            if (cursor != null){
                cursor.close();
                db.close();
                helper.close();
            }
        }
        */

        //print word
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
                extras.putStringArray("text",text_array);

                intent = new Intent(context , StudyContentMiddleActivity.class);
                intent.putExtras(extras);
                startActivity(intent);
                finish();
            }
        });
        sound_botton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer = new MediaPlayer();
                mediaPlayer = MediaPlayer.create(context,R.raw.sgalvp);
                try {
                    mediaPlayer.prepare();
                    mediaPlayer.seekTo(starttime);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                mediaPlayer.setOnSeekCompleteListener(new MediaPlayer.OnSeekCompleteListener(){
                    public void onSeekComplete(MediaPlayer m) {
                        m.start();
                    }
                });
                CountDownTimer timer = new CountDownTimer(duration, duration) {

                    @Override
                    public void onTick(long millisUntilFinished) {
                        // Nothing to do
                    }

                    @Override
                    public void onFinish() {
                        if (mediaPlayer.isPlaying()) {
                            mediaPlayer.stop();
                            mediaPlayer.release();
                        }
                    }
                };
                timer.start();
            }
        });
    }

}