
package com.example.amylilian.text_card;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import android.database.sqlite.SQLiteDatabase;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

import static com.example.amylilian.text_card.DBColumns.ID;
import static com.example.amylilian.text_card.DBColumns.TRL;
import static com.example.amylilian.text_card.DBColumns.Table_Name;
import static com.example.amylilian.text_card.DBHelper.DB_NAME;

public class StudyActivity extends AppCompatActivity {

    //add imagebutton
    ImageButton imageButton1_1;
    ImageButton imageButton1_2;

    //add intent
    private Context context;
    private Intent intent;

    //ArrayList
    private ArrayList<String> list = new ArrayList<>();

    private DBHelper dbHelper;
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
        setContentView(R.layout.activity_study);

        //呼叫建構子(寫進DBHelper,context換成this)
        DBHelper helper = new DBHelper(this);
        SQLiteDatabase db = helper.getWritableDatabase();

        //Cursor
        Cursor cursor = null;

        cursor = db.query(Table_Name,new String[]{ID,TRL}, ID + " < ?" ,  new String[]{Integer.toString(10)}, null, null, ID);

        if (cursor != null) {
            if (cursor.moveToFirst()) {
                String trl = cursor.getString(cursor.getColumnIndex(TRL));
                while (!cursor.isAfterLast()) {
                    list.add(trl);
                    cursor.moveToNext();
                }
            }
        }
        if (cursor != null){
            cursor.close();
            db.close();
            helper.close();
        }
        //put value into Bundle
        final Bundle extras = new Bundle();
        //extras.putSerializable("TRLList",list);

        //copy to many arratlist
        String[] AllTrl = list.toArray(new String[0]);
        final String[] type1_1 = new String[12];
        System.arraycopy(AllTrl,1,type1_1,0,12);
        final String[] type1_2 = new String[12];
        System.arraycopy(AllTrl,1,type1_2,0,12);
/*
        String[] type2_1 = new String[11];
        String[] type2_2 = new String[10];
        String[] type2_3 = new String[10];

        String[] type3_1 = new String[8];
        String[] type3_2 = new String[8];

        String[] type4_1 = new String[10];
        String[] type4_2 = new String[10];

        String[] type5_1 = new String[10];
        String[] type5_2 = new String[9];

        String[] type6_1 = new String[10];
        String[] type6_2 = new String[9];

        String[] type7 = new String[13];

        String[] type8_1 = new String[9];
        String[] type8_2 = new String[9];

        String[] type9_1 = new String[11];
        String[] type9_2 = new String[11];
*/
        imageButton1_1 = (ImageButton) findViewById(R.id.imageButton1_1);
        imageButton1_2 = (ImageButton) findViewById(R.id.imageButton1_2);

        context = this;
        imageButton1_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageButton1_1.setImageResource(R.drawable.btm_group01_b);

                extras.putStringArray("text",type1_1);
                intent = new Intent(context , StudyContentActivity.class);
                intent.putExtras(extras);
                startActivity(intent);
            }
        });
        imageButton1_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageButton1_2.setImageResource(R.drawable.btm_group02_b);

                intent = new Intent(context , StudyContentActivity.class);
                intent.putExtras(extras);
                startActivity(intent);
            }
        });

    }
}
