package com.example.amylilian.text_card;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Random;

import static com.example.amylilian.text_card.DBColumns.BeginTime;
import static com.example.amylilian.text_card.DBColumns.EXT;
import static com.example.amylilian.text_card.DBColumns.EndTime;
import static com.example.amylilian.text_card.DBColumns.ID;
import static com.example.amylilian.text_card.DBColumns.IMG;
import static com.example.amylilian.text_card.DBColumns.ORG;
import static com.example.amylilian.text_card.DBColumns.TRL;
import static com.example.amylilian.text_card.DBColumns.Table_Name;

public class ReviewTestActivity extends AppCompatActivity {

    //add imagebutton
    ImageButton imageButton1_1;    ImageButton imageButton1_2;
    ImageButton imageButton2_1;    ImageButton imageButton2_2;    ImageButton imageButton2_3;
    ImageButton imageButton3_1;    ImageButton imageButton3_2;
    ImageButton imageButton4_1;    ImageButton imageButton4_2;
    ImageButton imageButton5_1;    ImageButton imageButton5_2;
    ImageButton imageButton6_1;    ImageButton imageButton6_2;
    ImageButton imageButton7;
    ImageButton imageButton8_1;    ImageButton imageButton8_2;
    ImageButton imageButton9_1;    ImageButton imageButton9_2;

    //startID. finishID
    int sta; int fin;
    //database data
    String[] org;
    String[] ext;
    double[] begin;
    double[] end;
    String[] img;
    String[] list_trl;
    int total;

    //add intent
    private Context context;
    private Intent intent;

    String[] test_word;
    //random number
    int x;
    //String y;

    //add category number
    String[] category;
    int c_number;

    //put value into Bundle
    Bundle extras = new Bundle();

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
        setContentView(R.layout.activity_review_test);

        //呼叫建構子(寫進DBHelper,context換成this)
        final DBHelper helper = new DBHelper(this);

        //人體
        imageButton1_1 = (ImageButton) findViewById(R.id.test_imageButton1_1);
        imageButton1_2 = (ImageButton) findViewById(R.id.test_imageButton1_2);
        //動物
        imageButton2_1 = (ImageButton) findViewById(R.id.test_imageButton2_1);
        imageButton2_2 = (ImageButton) findViewById(R.id.test_imageButton2_2);
        imageButton2_3 = (ImageButton) findViewById(R.id.test_imageButton3_1);
        //鳥類
        imageButton3_1 = (ImageButton) findViewById(R.id.test_imageButton4_1);
        imageButton3_2 = (ImageButton) findViewById(R.id.test_imageButton4_2);
        //昆蟲
        imageButton4_1 = (ImageButton) findViewById(R.id.test_imageButton5_1);
        imageButton4_2 = (ImageButton) findViewById(R.id.test_imageButton5_2);
        //水中生物
        imageButton5_1 = (ImageButton) findViewById(R.id.test_imageButton6_1);
        imageButton5_2 = (ImageButton) findViewById(R.id.test_imageButton6_2);
        //植物
        imageButton6_1 = (ImageButton) findViewById(R.id.test_imageButton7_1);
        imageButton6_2 = (ImageButton) findViewById(R.id.test_imageButton7_2);
        //蔬菜
        imageButton7 = (ImageButton) findViewById(R.id.test_imageButton8_1);
        //水果
        imageButton8_1 = (ImageButton) findViewById(R.id.test_imageButton9_1);
        imageButton8_2 = (ImageButton) findViewById(R.id.test_imageButton9_2);
        //食品
        imageButton9_1 = (ImageButton) findViewById(R.id.test_imageButton10_1);
        imageButton9_2 = (ImageButton) findViewById(R.id.test_imageButton10_2);

        category = getResources().getStringArray(R.array.category_number);

        context = this;
        imageButton1_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageButton1_1.setImageResource(R.drawable.btm_group01_b);
                //傳入開頭跟結尾ID
                sta=1;fin=12;
                extras.putInt("sta",sta);
                extras.putInt("fin",fin);
                extras.putStringArray("test_word",gettrl(helper,sta,fin));
                transfer();
            }
        });
        imageButton1_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageButton1_2.setImageResource(R.drawable.btm_group02_b);
                sta=13;fin=25;
                extras.putInt("sta",sta);
                extras.putInt("fin",fin);
                extras.putStringArray("test_word",gettrl(helper,sta,fin));
                transfer();
            }
        });
        imageButton2_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageButton2_1.setImageResource(R.drawable.btm_group03_b);
                sta=27;fin=36;
                extras.putInt("sta",sta);
                extras.putInt("fin",fin);
                extras.putStringArray("test_word",gettrl(helper,sta,fin));
                transfer();
            }
        });
        imageButton2_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageButton2_2.setImageResource(R.drawable.btm_group04_b);
                sta=37;fin=46;
                extras.putInt("sta",sta);
                extras.putInt("fin",fin);
                extras.putStringArray("test_word",gettrl(helper,sta,fin));
                transfer();
            }
        });
        imageButton2_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageButton2_3.setImageResource(R.drawable.btm_group05_b);
                sta=48;fin=57;
                extras.putInt("sta",sta);
                extras.putInt("fin",fin);
                extras.putStringArray("test_word",gettrl(helper,sta,fin));
                transfer();
            }
        });
        imageButton3_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageButton3_1.setImageResource(R.drawable.btm_group06_b);
                sta=59;fin=65;
                extras.putInt("sta",sta);
                extras.putInt("fin",fin);
                extras.putStringArray("test_word",gettrl(helper,sta,fin));
                transfer();
            }
        });
        imageButton3_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageButton3_2.setImageResource(R.drawable.btm_group07_b);
                sta=66;fin=74;
                extras.putInt("sta",sta);
                extras.putInt("fin",fin);
                extras.putStringArray("test_word",gettrl(helper,sta,fin));
                transfer();
            }
        });
        imageButton4_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageButton4_1.setImageResource(R.drawable.btm_group08_b);
                sta=76;fin=84;
                extras.putInt("sta",sta);
                extras.putInt("fin",fin);
                extras.putStringArray("test_word",gettrl(helper,sta,fin));
                transfer();
            }
        });
        imageButton4_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageButton4_2.setImageResource(R.drawable.btm_group09_b);
                sta=85;fin=95;
                extras.putInt("sta",sta);
                extras.putInt("fin",fin);
                extras.putStringArray("test_word",gettrl(helper,sta,fin));
                transfer();
            }
        });
        imageButton5_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageButton5_1.setImageResource(R.drawable.btm_group10_b);
                sta=97;fin=105;
                extras.putInt("sta",sta);
                extras.putInt("fin",fin);
                extras.putStringArray("test_word",gettrl(helper,sta,fin));
                transfer();
            }
        });
        imageButton5_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageButton5_2.setImageResource(R.drawable.btm_group11_b);
                sta=106;fin=115;
                extras.putInt("sta",sta);
                extras.putInt("fin",fin);
                extras.putStringArray("test_word",gettrl(helper,sta,fin));
                transfer();
            }
        });
        imageButton6_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageButton6_1.setImageResource(R.drawable.btm_group12_b);
                sta=117;fin=125;
                extras.putInt("sta",sta);
                extras.putInt("fin",fin);
                extras.putStringArray("test_word",gettrl(helper,sta,fin));
                transfer();
            }
        });
        imageButton6_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageButton6_2.setImageResource(R.drawable.btm_group13_b);
                sta=126;fin=135;
                extras.putInt("sta",sta);
                extras.putInt("fin",fin);
                extras.putStringArray("test_word",gettrl(helper,sta,fin));
                transfer();
            }
        });
        imageButton7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageButton7.setImageResource(R.drawable.btm_group14_b);
                sta=137;fin=149;
                extras.putInt("sta",sta);
                extras.putInt("fin",fin);
                extras.putStringArray("test_word",gettrl(helper,sta,fin));
                transfer();
            }
        });
        imageButton8_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageButton8_1.setImageResource(R.drawable.btm_group15_b);
                sta=151;fin=158;
                extras.putInt("sta",sta);
                extras.putInt("fin",fin);
                extras.putStringArray("test_word",gettrl(helper,sta,fin));
                transfer();
            }
        });
        imageButton8_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageButton8_2.setImageResource(R.drawable.btm_group16_b);
                sta=159;fin=168;
                extras.putInt("sta",sta);
                extras.putInt("fin",fin);
                extras.putStringArray("test_word",gettrl(helper,sta,fin));
                transfer();
            }
        });
        imageButton9_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageButton9_1.setImageResource(R.drawable.btm_group17_b);
                sta=170;fin=180;
                extras.putInt("sta",sta);
                extras.putInt("fin",fin);
                extras.putStringArray("test_word",gettrl(helper,sta,fin));
                transfer();
            }
        });
        imageButton9_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageButton9_2.setImageResource(R.drawable.btm_group18_b);
                sta=181;fin=191;
                extras.putInt("sta",sta);
                extras.putInt("fin",fin);
                extras.putStringArray("test_word",gettrl(helper,sta,fin));
                transfer();
            }
        });
    }

    public void transfer() {
        //x = (int) (Math.random() * 3);

        x = 2;

        extras = new Bundle();
        extras.putInt("total",sta - fin + 1);
        extras.putInt("count",1);
        extras.putInt("correct",0);
        extras.putInt("wrong",0);
        extras.putStringArray("org",org);
        extras.putStringArray("ext",ext);
        extras.putDoubleArray("begin",begin);
        extras.putDoubleArray("end",end);
        extras.putStringArray("img",img);

        switch (x){
            case 0:

                //send
                intent = new Intent(context , Test_pic.class);
                break;
            case 1:

                //send
                intent = new Intent(context , Test_sound.class);
                break;
            case 2:

                //send
                intent = new Intent(context , Test_word.class);
                break;
        }
        intent.putExtras(extras);
        startActivity(intent);
    }

    public String[] gettrl(DBHelper helper,int s,int f){

        //Cursor
        Cursor cursor = null;

        //分類單字總數
        int n = f-s+1;

        SQLiteDatabase db = helper.getWritableDatabase();
        cursor = db.query(Table_Name, new String[] {ID,BeginTime,EndTime,ORG,EXT,IMG,TRL}, null, null, null, null, null, null);
        //宣告要拿取的資料陣列
        begin = new double[n];
        end = new double[n];
        org = new String[n];
        ext = new String[n];
        img = new String[n];
        list_trl = new String[n];

        //計算個數 要拿n個單字
        int i = 0;
        //先把cursor移至分類的第一個單字
        cursor.move(s);
        //指針,存取
        if (cursor != null){
            while (cursor.moveToNext() && i < n) {
                //存入陣列
                begin[i] = cursor.getDouble(cursor.getColumnIndex(BeginTime));
                end[i] = cursor.getDouble(cursor.getColumnIndex(EndTime));
                org[i] = cursor.getString(cursor.getColumnIndex(ORG));
                ext[i] = cursor.getString(cursor.getColumnIndex(EXT));
                img[i] = cursor.getString(cursor.getColumnIndex(IMG));
                list_trl[i] = cursor.getString(cursor.getColumnIndex(TRL));
                i++;
            }
        }
        //關閉
        if (cursor != null){
            cursor.close();
            db.close();
            helper.close();
        }
        return list_trl;
    }
}
