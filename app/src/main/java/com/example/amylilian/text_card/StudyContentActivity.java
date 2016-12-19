package com.example.amylilian.text_card;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import static com.example.amylilian.text_card.DBColumns.ID;
import static com.example.amylilian.text_card.DBColumns.TRL;
import static com.example.amylilian.text_card.DBColumns.Table_Name;
import static com.example.amylilian.text_card.DBHelper.DB_NAME;

public class StudyContentActivity extends AppCompatActivity {

    ImageButton sound_botton;
    ImageButton next_botton;
    TextView text1;

    //add intent
    private Context context;
    private Intent intent;
    private DBHelper dbHelper;

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

        //呼叫建構子(寫進DBHelper,context換成this)
        DBHelper helper = new DBHelper(this);
        SQLiteDatabase db = helper.getWritableDatabase();

        //Cursor
        Cursor cursor = null;

        //get value
        sound_botton = (ImageButton) findViewById(R.id.soundButton_studyfirst);
        next_botton = (ImageButton) findViewById(R.id.nextpageButton_studyfirst);
        text1 = (TextView) findViewById(R.id.text1View_studyfirst);

        //get temply text string
        String[] text_array = getResources().getStringArray(R.array.temp_text);

        //get total string[] long
        final int group_length = text_array.length;

        try{
            //cursor = db.query(Table_Name, new String[]{ID,TRL}, ID + "< 10" , null, null, null, ID);
            //cursor = db.query(Table_Name, null, ID + " < 10" , new String[]{ID,TRL}, null, null, ID);
            cursor = db.query(true, Table_Name,new String[]{ID,TRL}, ID + " < 10" ,  null, null, null,null,null);

            if (cursor != null){
                String id = cursor.getString(cursor.getColumnIndex(ID));
                String trl = cursor.getString(cursor.getColumnIndex(TRL));
                text1.setText(trl);
                Log.d(TAG, id + ":" + trl);
            }
        } finally {
            if (cursor != null){
                cursor.close();
                db.close();
                helper.close();
            }
        }
        //print first word
        //text1.setText(text_array[0]);

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
    //返回
    @Override
    public void onBackPressed() {
    }

}