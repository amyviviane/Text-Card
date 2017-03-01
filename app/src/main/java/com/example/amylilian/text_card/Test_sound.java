package com.example.amylilian.text_card;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

import static com.example.amylilian.text_card.DBColumns.BeginTime;
import static com.example.amylilian.text_card.DBColumns.EXT;
import static com.example.amylilian.text_card.DBColumns.EndTime;
import static com.example.amylilian.text_card.DBColumns.ID;
import static com.example.amylilian.text_card.DBColumns.IMG;
import static com.example.amylilian.text_card.DBColumns.ORG;
import static com.example.amylilian.text_card.DBColumns.TRL;
import static com.example.amylilian.text_card.DBColumns.Table_Name;

public class Test_sound extends AppCompatActivity {

    //題目聲音按鈕
    ImageButton sound;

    //選項imageButton
    ImageButton c1;
    ImageButton c2;
    ImageButton c3;
    ImageButton c4;

    //選項textView
    TextView t1;
    TextView t2;
    TextView t3;
    TextView t4;

    //now count
    TextView c;

    //對錯的題數
    TextView e1;
    TextView e2;

    //ImageButton next page
    ImageButton next;

    //get bundle values
    int total;
    int count;
    String[] word;
    int correct;
    int wrong;
    String[] org;
    String[] ext;
    double[] begin;
    double[] end;
    String[] img;
    int sta;
    int fin;

    //random number
    int rand;

    //ans
    int ans;

    //ans color
    String[] color;

    //media
    MediaPlayer mediaPlayer;
    int starttime;
    int duration;

    //add intent
    private Context context;
    private Intent intent;

    private SQLiteDatabase copydb(String dbfile){
        try{
            //判斷資料庫檔案是否存在
            if(!(new File(dbfile).exists())){
                //String sfile = DBHelper.DB_LOCATION + DBHelper.DB_NAME;
                InputStream is = context.getAssets().open(DBHelper.DB_NAME);
                FileOutputStream fos = new FileOutputStream(dbfile);
                byte[] buffer = new byte[50000];
                int count = 0;
                while ((count = is.read(buffer)) > 0){
                    fos.write(buffer,0,count);
                }
                fos.close();
                is.close();
            }
            SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase(dbfile,null);
            return db;
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_sound);

        //呼叫建構子(寫進DBHelper,context換成this)
        final DBHelper helper = new DBHelper(this);

        sound = (ImageButton) findViewById(R.id.imageButton3);
        c1 = (ImageButton) findViewById(R.id.imageButton4);
        c2 = (ImageButton) findViewById(R.id.imageButton5);
        c3 = (ImageButton) findViewById(R.id.imageButton6);
        c4 = (ImageButton) findViewById(R.id.imageButton7);
        t1 = (TextView) findViewById(R.id.textView14);
        t2 = (TextView) findViewById(R.id.textView15);
        t3 = (TextView) findViewById(R.id.textView16);
        t4 = (TextView) findViewById(R.id.textView17);
        c = (TextView) findViewById(R.id.textView18);
        e1 = (TextView) findViewById(R.id.textView19);
        e2 = (TextView) findViewById(R.id.textView23);
        next = (ImageButton) findViewById(R.id.imageButton8);

        //get bundle
        Bundle extras = getIntent().getExtras();
        total = extras.getInt("total");
        count = extras.getInt("count");
        correct = extras.getInt("correct");
        wrong = extras.getInt("wrong");

        //假設為第一題 則必須先拿取資料庫資料
        if(count == 1){
            //取得sta.fin
            sta = extras.getInt("sta");
            fin = extras.getInt("fin");

            //Cursor
            Cursor cursor = null;

            //分類單字總數
            int n = fin - sta + 1;

            SQLiteDatabase db = helper.getWritableDatabase();
            cursor = db.query(Table_Name, new String[] {ID,BeginTime,EndTime,ORG,EXT,IMG,TRL}, null, null, null, null, null, null);
            //宣告要拿取的資料陣列
            begin = new double[n];
            end = new double[n];
            org = new String[n];
            ext = new String[n];
            img = new String[n];
            word = new String[n];

            //計算個數 要拿n個單字
            int i = 0;
            //先把cursor移至分類的第一個單字
            cursor.move(sta);
            //指針,存取
            if (cursor != null){
                while (i < n) {
                    //存入陣列
                    begin[i] = cursor.getDouble(cursor.getColumnIndex(BeginTime));
                    end[i] = cursor.getDouble(cursor.getColumnIndex(EndTime));
                    org[i] = cursor.getString(cursor.getColumnIndex(ORG));
                    ext[i] = cursor.getString(cursor.getColumnIndex(EXT));
                    img[i] = cursor.getString(cursor.getColumnIndex(IMG));
                    word[i] = cursor.getString(cursor.getColumnIndex(TRL));
                    cursor.moveToNext();
                    i++;
                }
            }
            //關閉
            if (cursor != null){
                cursor.close();
                db.close();
                helper.close();
            }
        }

        //如果不是第一題 則利用get bundle拿資料庫資料
        else {
            org = extras.getStringArray("org");
            ext = extras.getStringArray("ext");
            begin = extras.getDoubleArray("begin");
            end = extras.getDoubleArray("end");
            img = extras.getStringArray("img");
            word = extras.getStringArray("word");
        }
        c.setText(count + "");

        //random choose
        rand = (int) (Math.random() * 4);
        int[] ia = new int[3];
        int num = count + 2;
        for(int y = 0 ; y < 3 ; y++){
            if (num > total){
                num = num % total;
            }
            ia[y] = num;
            num++;
        }
        switch (rand) {
            case 0:
                t1.setText(word[count-1]);
                t2.setText(word[ia[0]-1]);
                t3.setText(word[ia[1]-1]);
                t4.setText(word[ia[2]-1]);
                break;
            case 1:
                t1.setText(word[ia[0]-1]);
                t2.setText(word[count - 1]);
                t3.setText(word[ia[1]-1]);
                t4.setText(word[ia[2]-1]);
                break;
            case 2:
                t1.setText(word[ia[0]-1]);
                t2.setText(word[ia[1]-1]);
                t3.setText(word[count - 1]);
                t4.setText(word[ia[2]-1]);
                break;
            case 3:
                t1.setText(word[ia[0]-1]);
                t2.setText(word[ia[1]-1]);
                t3.setText(word[ia[2]-1]);
                t4.setText(word[count - 1]);
                break;
        }

        //set color
        color = new String[4];
        for (int y = 0 ; y < 4 ; y++){
            if (y == rand){
                color[y] = "#6bfe63";
            }
            else {
                color[y] = "#ff1723";
            }
        }

        //show right and error number
        e1.setText("對：" + correct);
        e2.setText("錯：" + wrong);

        //sound buttom
        sound.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                starttime = (int)(begin[count - 1] * 1000);
                duration = (int)((end[count - 1] * 1000) - starttime);
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
                            sound.setEnabled(true);
                        }
                    }
                };
                timer.start();
                sound.setEnabled(false);
            }
        });

        c1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ans = 1;
                if (ans == (rand + 1)){
                    correct++;
                }
                else {
                    wrong++;
                }
                anwser(color);
            }
        });
        c2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ans = 2;
                if (ans == (rand + 1)){
                    correct++;
                }
                else {
                    wrong++;
                }
                anwser(color);
            }
        });
        c3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ans = 3;
                if (ans == (rand + 1)){
                    correct++;
                }
                else {
                    wrong++;
                }
                anwser(color);
            }
        });
        c4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ans = 4;
                if (ans == (rand + 1)){
                    correct++;
                }
                else {
                    wrong++;
                }
                anwser(color);
            }
        });

        context = this;
        if (total == count){
            next.setImageResource(R.drawable.btm_end_a);
            next.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    next.setImageResource(R.drawable.btm_end_b);
                    Bundle extra = new Bundle();
                    extra.putInt("correct",correct);
                    intent = new Intent(context , Test_End_Activity.class);
                    intent.putExtras(extra);
                    startActivity(intent);
                    finish();
                }
            });
        }else{
            next.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    next.setImageResource(R.drawable.btm_right_b);
                    trans(total,count);
                }
            });
        }
    }

    //x=total ; y= new count
    public void trans(int x,int y) {
        Bundle extra;
        int i = (int) (Math.random() * 3);
        //add Bundle
        extra = new Bundle();
        //package
        extra.putInt("total",x);
        extra.putInt("count",y + 1);
        extra.putStringArray("word",word);
        extra.putInt("correct",correct);
        extra.putInt("wrong",wrong);
        extra.putStringArray("org",org);
        extra.putStringArray("ext",ext);
        extra.putDoubleArray("begin",begin);
        extra.putDoubleArray("end",end);
        extra.putStringArray("img",img);
        switch (i){
            case 0:
                intent = new Intent(context , Test_pic.class);
                break;
            case 1:

                intent = new Intent(context , Test_sound.class);
                break;
            case 2:

                intent = new Intent(context , Test_word.class);
                break;
        }
        intent.putExtras(extra);
        startActivity(intent);
        finish();
    }
    public void anwser(String[] s){

        t1.setTextColor(Color.parseColor(s[0]));
        t2.setTextColor(Color.parseColor(s[1]));
        t3.setTextColor(Color.parseColor(s[2]));
        t4.setTextColor(Color.parseColor(s[3]));

        //show right and error number
        e1.setText("對：" + correct);
        e2.setText("錯：" + wrong);

        c1.setEnabled(false);
        c2.setEnabled(false);
        c3.setEnabled(false);
        c4.setEnabled(false);
    }
}
