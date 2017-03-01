package com.example.amylilian.text_card;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class StudyContentEndActivity extends AppCompatActivity {

    ImageButton sound_botton;
    ImageButton previous_botton;
    ImageButton end_botton;
    TextView text1;
    TextView text2;
    TextView text3;
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
        setContentView(R.layout.activity_study_content_end);

        sound_botton = (ImageButton) findViewById(R.id.soundButton_studyend);
        previous_botton = (ImageButton) findViewById(R.id.previouspageButton_studyend);
        end_botton = (ImageButton) findViewById(R.id.endButton_studyend);
        text1 = (TextView) findViewById(R.id.text1View_studyend);
        text2 = (TextView) findViewById(R.id.textView30);
        text3 = (TextView) findViewById(R.id.textView31);
        img1 = (ImageView) findViewById(R.id.imageView2);

        //get temply text string
        // String[] text_array = getResources().getStringArray(R.array.temp_text);

        //get bundle(新增2016.11.30)
        Bundle extras = getIntent().getExtras();
        final int count = extras.getInt("count");
        final int sta = extras.getInt("sta");
        final int fin = extras.getInt("fin");
        //取出小分類所有資料
        final String[] text_array = extras.getStringArray("text");
        final String[] org =  extras.getStringArray("org");
        final String[] ext =  extras.getStringArray("ext");
        final double[] begin =  extras.getDoubleArray("begin");
        final double[] end =  extras.getDoubleArray("end");
        final String[] img =  extras.getStringArray("img");

        //get total string[] long
        final int group_length = text_array.length ; //修改 (沒有-1）

        //set trl
        text1.setText(text_array[count]);
        //set org
        text2.setText(org[count]);
        //set ext
        text3.setText(ext[count]);
        //set img
        String str = img[count].toLowerCase(); //將取出的圖檔名轉小寫
        Context con = getApplicationContext();
        int id = con.getResources().getIdentifier("drawable/" + str,null,con.getPackageName());
        img1.setImageResource(id);
        System.out.println("wwwww +" +str);

        context = this;
        previous_botton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                previous_botton.setImageResource(R.drawable.btm_left_b);

                //put value into Bundle
                final Bundle extras = new Bundle();
                extras.putInt("length",group_length);
                extras.putInt("count",count - 1);
                extras.putInt("sta",sta);
                extras.putInt("fin",fin);
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
        });
        end_botton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                end_botton.setImageResource(R.drawable.btm_end_b); //修改成btm_end_b

                intent = new Intent(context, StudyResultActivity.class);
                startActivity(intent);
                //試試finish
                finish();
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
                            sound_botton.setEnabled(true);
                        }
                    }
                };
                timer.start();
                sound_botton.setEnabled(false);
            }
        });
    }
    //返回
    //@Override
    //public void onBackPressed() {
    //}

}
