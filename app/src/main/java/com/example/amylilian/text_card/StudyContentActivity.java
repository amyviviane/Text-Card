package com.example.amylilian.text_card;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
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
import static com.example.amylilian.text_card.DBColumns.Table_Name;



public class StudyContentActivity extends AppCompatActivity {

    ImageButton sound_botton;
    ImageButton next_botton;
    TextView text1;
    ImageView img;
    TextView text2;
    TextView text3;
    TextView text4;


    //add intent
    private Context context;
    private Intent intent;
    private MediaPlayer mediaPlayer;
    private int starttime;
    private int duration;

    private static final String TAG = "StudyContentActivity";
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
        setContentView(R.layout.activity_study_content);

        //宣告開頭跟結尾ID的int

        //get value
        sound_botton = (ImageButton) findViewById(R.id.soundButton_studyfirst);
        next_botton = (ImageButton) findViewById(R.id.nextpageButton_studyfirst);
        text1 = (TextView) findViewById(R.id.text1View_studyfirst);
        img = (ImageView) findViewById(R.id.imageView2);
        text2 = (TextView) findViewById(R.id.textView_chinese);
        text3 = (TextView) findViewById(R.id.ex0);
        text4 = (TextView) findViewById(R.id.ex1);

        //get bundle
        Bundle extras = getIntent().getExtras();
        final String[] text_array = extras.getStringArray("text");
        final int sta = extras.getInt("sta");
        final int fin = extras.getInt("fin");

        System.out.println("sta is "+sta);

        //分類單字總數
        int n = fin-sta+1;
        int count = 0;

        //Cursor
        Cursor cursor = null;
        //呼叫建構子(寫進DBHelper,context換成this)
        final DBHelper helper = new DBHelper(this);
        SQLiteDatabase db = helper.getWritableDatabase();
        //trl
        cursor = db.query(Table_Name, new String[] {ID,BeginTime,EndTime,ORG,EXT,IMG}, null, null, null, null, null, null);

        //宣告要拿取的資料陣列
        final double be[] = new double[n];
        final double en[] = new double[n];
        final String or[] = new String[n];
        final String ex[] = new String[n];
        final String im[] = new String[n];

        //計算個數 要拿n個單字
        int i = 0;
        //先把cursor移至分類的第一個單字
        cursor.move(sta);
        //指針,存取
        if (cursor != null){
            while (cursor.moveToNext() && i < n) {
                //存入陣列
                double begin = cursor.getDouble(cursor.getColumnIndex(BeginTime));
                be[i] = begin;
                double end = cursor.getDouble(cursor.getColumnIndex(EndTime));
                en[i] = end;
                String o = cursor.getString(cursor.getColumnIndex(ORG));
                or[i] = o;
                String e = cursor.getString(cursor.getColumnIndex(EXT));
                ex[i] = e;
                String img = cursor.getString(cursor.getColumnIndex(IMG));
                im[i] = img;
                i++;
            }
        }
        //關閉
        if (cursor != null){
            cursor.close();
            db.close();
            helper.close();
        }

        //set trl
        text1.setText(text_array[0]);
        //set org
        text2.setText(or[0]);
        //切割拼音
        String[] es = ex[0].split("]");
        //set ext
        text3.setText(es[0]+"]");
        text4.setText(es[1]+"]");
        //防止字數過多
        if(text_array[0].length() > 13) {
            text1.setTextSize(18);
        }
        if(es[0].length() > 11) {
            text3.setTextSize(14);
        }
        if(es[1].length() > 15) {
            text4.setTextSize(14);
        }
        if(es[1].length() > 17) {
            text4.setTextSize(12);
        }
        //set img
        String str = im[count].toLowerCase(); //將取出的圖檔名轉小寫
        Context con = getApplicationContext();
        int id = con.getResources().getIdentifier("drawable/" + str,null,con.getPackageName());
        img.setImageResource(id);

        context = this;
        next_botton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                next_botton.setImageResource(R.drawable.btm_right_b);

                //put value into Bundle
                final Bundle extras = new Bundle();
                extras.putInt("count",1);

                extras.putInt("sta",sta);
                extras.putInt("fin",fin);

                //傳遞小分類所有資料
                extras.putStringArray("text",text_array);
                extras.putStringArray("org",or);
                extras.putStringArray("ext",ex);
                extras.putDoubleArray("begin",be);
                extras.putDoubleArray("end",en);
                extras.putStringArray("img",im);

                intent = new Intent(context , StudyContentMiddleActivity.class);
                intent.putExtras(extras);
                startActivity(intent);
                finish();

            }
        });
        sound_botton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                starttime = (int)(be[0] * 1000);
                duration = (int)((en[0] * 1000) - starttime);
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
//    public static int getImageId(Context context, String imageName){
//        return context.getResources().getIdentifier("drawable/" + imageName,null,context.getPackageName());
//    }
}