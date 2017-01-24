
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
import java.util.ArrayList;

import static com.example.amylilian.text_card.DBColumns.ORG;
import static com.example.amylilian.text_card.DBColumns.TRL;
import static com.example.amylilian.text_card.DBColumns.Table_Name;

public class StudyActivity extends AppCompatActivity {

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

    //add intent
    private Context context;
    private Intent intent;

    //put value into Bundle
    final Bundle extras = new Bundle();

    //ArrayList
    private ArrayList<String> list_trl = new ArrayList<>();

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
        final DBHelper helper = new DBHelper(this);

        //人體
        imageButton1_1 = (ImageButton) findViewById(R.id.imageButton1_1);
        imageButton1_2 = (ImageButton) findViewById(R.id.imageButton1_2);
        //動物
        imageButton2_1 = (ImageButton) findViewById(R.id.imageButton2_1);
        imageButton2_2 = (ImageButton) findViewById(R.id.imageButton2_2);
        imageButton2_3 = (ImageButton) findViewById(R.id.imageButton2_3);
        //鳥類
        imageButton3_1 = (ImageButton) findViewById(R.id.imageButton3_1);
        imageButton3_2 = (ImageButton) findViewById(R.id.imageButton3_2);
        //昆蟲
        imageButton4_1 = (ImageButton) findViewById(R.id.imageButton4_1);
        imageButton4_2 = (ImageButton) findViewById(R.id.imageButton4_2);
        //水中生物
        imageButton5_1 = (ImageButton) findViewById(R.id.imageButton5_1);
        imageButton5_2 = (ImageButton) findViewById(R.id.imageButton5_2);
        //植物
        imageButton6_1 = (ImageButton) findViewById(R.id.imageButton6_1);
        imageButton6_2 = (ImageButton) findViewById(R.id.imageButton6_2);
        //蔬菜
        imageButton7 = (ImageButton) findViewById(R.id.imageButton7);
        //水果
        imageButton8_1 = (ImageButton) findViewById(R.id.imageButton8_1);
        imageButton8_2 = (ImageButton) findViewById(R.id.imageButton8_2);
        //食品
        imageButton9_1 = (ImageButton) findViewById(R.id.imageButton9_1);
        imageButton9_2 = (ImageButton) findViewById(R.id.imageButton9_2);

        context = this;
        imageButton1_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageButton1_1.setImageResource(R.drawable.btm_group01_b);
                //傳入開頭跟結尾ID
                sta=1;fin=12;
                extras.putInt("sta",sta);
                extras.putInt("fin",fin);
                extras.putStringArray("text",gettrl("type1_1",helper));
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
                extras.putStringArray("text",gettrl("type1_2",helper));
                transfer();
            }
        });
        imageButton2_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageButton2_1.setImageResource(R.drawable.btm_group03_b);
                sta=26;fin=36;
                extras.putInt("sta",sta);
                extras.putInt("fin",fin);
                extras.putStringArray("text",gettrl("type2_1",helper));
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
                extras.putStringArray("text",gettrl("type2_2",helper));
                transfer();
            }
        });
        imageButton2_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageButton2_3.setImageResource(R.drawable.btm_group05_b);
                sta=47;fin=57;
                extras.putInt("sta",sta);
                extras.putInt("fin",fin);
                extras.putStringArray("text",gettrl("type2_3",helper));
                transfer();
            }
        });
        imageButton3_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageButton3_1.setImageResource(R.drawable.btm_group06_b);
                sta=58;fin=65;
                extras.putInt("sta",sta);
                extras.putInt("fin",fin);
                extras.putStringArray("text",gettrl("type3_1",helper));
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
                extras.putStringArray("text",gettrl("type3_2",helper));
                transfer();
            }
        });
        imageButton4_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageButton4_1.setImageResource(R.drawable.btm_group08_b);
                sta=75;fin=84;
                extras.putInt("sta",sta);
                extras.putInt("fin",fin);
                extras.putStringArray("text",gettrl("type4_1",helper));
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
                extras.putStringArray("text",gettrl("type4_2",helper));
                transfer();
            }
        });
        imageButton5_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageButton5_1.setImageResource(R.drawable.btm_group10_b);
                sta=96;fin=105;
                extras.putInt("sta",sta);
                extras.putInt("fin",fin);
                extras.putStringArray("text",gettrl("type5_1",helper));
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
                extras.putStringArray("text",gettrl("type5_2",helper));
                transfer();
            }
        });
        imageButton6_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageButton6_1.setImageResource(R.drawable.btm_group12_b);
                sta=116;fin=125;
                extras.putInt("sta",sta);
                extras.putInt("fin",fin);
                extras.putStringArray("text",gettrl("type6_1",helper));
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
                extras.putStringArray("text",gettrl("type6_2",helper));
                transfer();
            }
        });
        imageButton7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageButton7.setImageResource(R.drawable.btm_group14_b);
                sta=136;fin=149;
                extras.putInt("sta",sta);
                extras.putInt("fin",fin);
                extras.putStringArray("text",gettrl("type7",helper));
                transfer();
            }
        });
        imageButton8_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageButton8_1.setImageResource(R.drawable.btm_group15_b);
                sta=150;fin=158;
                extras.putInt("sta",sta);
                extras.putInt("fin",fin);
                extras.putStringArray("text",gettrl("type8_1",helper));
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
                extras.putStringArray("text",gettrl("type8_2",helper));
                transfer();
            }
        });
        imageButton9_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageButton9_1.setImageResource(R.drawable.btm_group17_b);
                sta=169;fin=180;
                extras.putInt("sta",sta);
                extras.putInt("fin",fin);
                extras.putStringArray("text",gettrl("type9_1",helper));
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
                extras.putStringArray("text",gettrl("type9_2",helper));
                transfer();
            }
        });

    }
    public void transfer() {
        intent = new Intent(context , StudyContentActivity.class);
        intent.putExtras(extras);
        startActivity(intent);
    }

    public String[] gettrl(String type,DBHelper helper){

        //Cursor
        Cursor cursor = null;

        SQLiteDatabase db = helper.getWritableDatabase();
        //trl
        cursor = db.query(Table_Name, new String[] {TRL}, null, null, null, null, null, null);

        if (cursor != null){
            while (cursor.moveToNext()) {
                String trl = cursor.getString(cursor.getColumnIndex(TRL));
                list_trl.add(trl);
            }
        }
        if (cursor != null){
            cursor.close();
            db.close();
            helper.close();
        }

        //copy to many arratlist
        String[] AllTrl = list_trl.toArray(new String[0]);

        switch (type){
            case "type1_1":
                String[] type1_1 = new String[12];
                System.arraycopy(AllTrl,1,type1_1,0,type1_1.length);
                return type1_1;
            case "type1_2":
                String[] type1_2 = new String[12];
                System.arraycopy(AllTrl,13,type1_2,0,type1_2.length);
                return type1_2;
            case "type2_1":
                String[] type2_1 = new String[11];
                System.arraycopy(AllTrl,26,type2_1,0,type2_1.length);
                return  type2_1;
            case "type2_2":
                String[] type2_2 = new String[10];
                System.arraycopy(AllTrl,37,type2_2,0,type2_2.length);
                return  type2_2;
            case "type2_3":
                String[] type2_3 = new String[10];
                System.arraycopy(AllTrl,47,type2_3,0,type2_3.length);
                return  type2_3;
            case "type3_1":
                String[] type3_1 = new String[8];
                System.arraycopy(AllTrl,58,type3_1,0,type3_1.length);
                return  type3_1;
            case "type3_2":
                String[] type3_2 = new String[8];
                System.arraycopy(AllTrl,66,type3_2,0,type3_2.length);
                return  type3_2;
            case "type4_1":
                String[] type4_1 = new String[10];
                System.arraycopy(AllTrl,75,type4_1,0,type4_1.length);
                return type4_1;
            case "type4_2":
                String[] type4_2 = new String[10];
                System.arraycopy(AllTrl,85,type4_2,0,type4_2.length);
                return type4_2;
            case "type5_1":
                String[] type5_1 = new String[10];
                System.arraycopy(AllTrl,96,type5_1,0,type5_1.length);
                return type5_1;
            case "type5_2":
                String[] type5_2 = new String[9];
                System.arraycopy(AllTrl,106,type5_2,0,type5_2.length);
                return type5_2;
            case "type6_1":
                String[] type6_1 = new String[10];
                System.arraycopy(AllTrl,116,type6_1,0,type6_1.length);
                return type6_1;
            case "type6_2":
                String[] type6_2 = new String[9];
                System.arraycopy(AllTrl,126,type6_2,0,type6_2.length);
                return type6_2;
            case "type7":
                String[] type7 = new String[13];
                System.arraycopy(AllTrl,136,type7,0,type7.length);
                return type7;
            case "type8_1":
                String[] type8_1 = new String[9];
                System.arraycopy(AllTrl,150,type8_1,0,type8_1.length);
                return type8_1;
            case "type8_2":
                String[] type8_2 = new String[9];
                System.arraycopy(AllTrl,159,type8_2,0,type8_2.length);
                return type8_2;
            case "type9_1":
                String[] type9_1 = new String[11];
                System.arraycopy(AllTrl,169,type9_1,0,type9_1.length);
                return type9_1;
            case "type9_2":
                String[] type9_2 = new String[11];
                System.arraycopy(AllTrl,181,type9_2,0,type9_2.length);
                return type9_2;
            default:
                return null;
        }

    }
}
