package com.example.amylilian.text_card;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class StudyContentMiddleActivity extends AppCompatActivity {

    ImageButton sound_botton;
    ImageButton previous_botton;
    ImageButton next_botton;
    TextView text1;
    TextView text2;
    ImageView img1;
    private MediaPlayer mediaPlayer;
    private int starttime;
    private int duration;

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
        text2 = (TextView) findViewById(R.id.textView29);
        img1 = (ImageView) findViewById(R.id.imageView2);

        //get temply text string
        //String[] text_array = getResources().getStringArray(R.array.temp_text);

        //get bundle
        Bundle extras = getIntent().getExtras();
        final int count = extras.getInt("count");
        final int s = extras.getInt("sta");
        final int f = extras.getInt("fin");

        //取出小分類所有資料
        final String[] text_array = extras.getStringArray("text");
        final String[] org =  extras.getStringArray("org");
        final String[] ext =  extras.getStringArray("ext");
        final double[] begin =  extras.getDoubleArray("begin");
        final double[] end =  extras.getDoubleArray("end");
        final String[] img =  extras.getStringArray("img");

        //get total string[] long
        final int group_length = text_array.length;

        //set trl
        text1.setText(text_array[count]);
        //set org+ext
        text2.setText(org[count]+ "\n" +ext[count]);
        //set img
        String str = img[count].toLowerCase(); //將取出的圖檔名轉小寫
        Context con = getApplicationContext();
        int id = con.getResources().getIdentifier("drawable/" + str,null,con.getPackageName());
        img1.setImageResource(id);

        context = this;
        previous_botton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                previous_botton.setImageResource(R.drawable.btm_left_b);

                if ((count - 1) == 0){
                    //put value into Bundle
                    final Bundle extras = new Bundle();
                    extras.putStringArray("text",text_array);
                    extras.putInt("sta",s);
                    extras.putInt("fin",f);

                    intent = new Intent(context, StudyContentActivity.class);
                    intent.putExtras(extras);

                    startActivity(intent);
                    finish();
                }else {
                    //put value into Bundle
                    final Bundle extras = new Bundle();
                    extras.putInt("length",group_length);
                    extras.putInt("count",count - 1);
                    //傳遞小分類所有資料
                    extras.putStringArray("text",text_array);
                    extras.putStringArray("org",org);
                    extras.putStringArray("ext",ext);
                    extras.putDoubleArray("begin",begin);
                    extras.putDoubleArray("end",end);
                    extras.putStringArray("img",img);

                    intent = new Intent(context, StudyContentMiddleActivity.class);
                    intent.putExtras(extras);

                    startActivity(intent);
                    finish();
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

                    //傳遞小分類所有資料
                    extras.putStringArray("text",text_array);
                    extras.putStringArray("org",org);
                    extras.putStringArray("ext",ext);
                    extras.putDoubleArray("begin",begin);
                    extras.putDoubleArray("end",end);
                    extras.putStringArray("img",img);

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
                    //傳遞小分類所有資料
                    extras.putStringArray("text",text_array);
                    extras.putStringArray("org",org);
                    extras.putStringArray("ext",ext);
                    extras.putDoubleArray("begin",begin);
                    extras.putDoubleArray("end",end);
                    extras.putStringArray("img",img);

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
                starttime = (int)(begin[count] * 1000);
                duration = (int)((end[count] * 1000) - starttime);
                mediaPlayer = new MediaPlayer();
                mediaPlayer = MediaPlayer.create(context,R.raw.sgalvp);
                mediaPlayer.seekTo(starttime);

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
    //返回
    //@Override
    //public void onBackPressed() {
    //}

}
