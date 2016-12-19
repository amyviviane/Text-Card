package com.example.amylilian.text_card;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import com.example.amylilian.text_card.DBColumns;


import static com.example.amylilian.text_card.DBColumns.BeginTime;
import static com.example.amylilian.text_card.DBColumns.EXT;
import static com.example.amylilian.text_card.DBColumns.EndTime;
import static com.example.amylilian.text_card.DBColumns.ID;
import static com.example.amylilian.text_card.DBColumns.IMG;
import static com.example.amylilian.text_card.DBColumns.ORG;
import static com.example.amylilian.text_card.DBColumns.TRL;
import static com.example.amylilian.text_card.DBColumns.Table_Name;


public class DBActivity extends AppCompatActivity {

    private DBHelper dbhelper = null;
    private TextView result = null;
    static final String db_name="card.sql";    // 資料庫名稱
    static final String tb_name="sentence";        // 資料表名稱
    SQLiteDatabase db;    //資料庫

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_db);

        result = (TextView) findViewById(R.id.text_result);

        getCursor();

        openDatabase();
        show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        closeDatabase();
    }

    private void openDatabase(){
        dbhelper = new DBHelper(this);  //待確認
    }
    private void closeDatabase(){
        dbhelper.close();
    }

    //資料指標，要查詢某一筆紀錄必須將Cursor指標指到它
    private Cursor getCursor(){
        SQLiteDatabase db = dbhelper.getReadableDatabase();
        String[] columns = {ID, BeginTime, EndTime, ORG, TRL, EXT, IMG};
        Cursor cursor = db.query(Table_Name, columns, null, null, null, null, null);
        startManagingCursor(cursor);
        return cursor;
    }

    private void show(){
        Cursor cursor = getCursor();
        StringBuilder resultData = new StringBuilder("RESULT: \n");

        cursor.moveToFirst();
        String txt = cursor.getString(0);
        //result.setText(txt);
        while(cursor.moveToNext()){
            String id = cursor.getString(0);
            String begintime = cursor.getString(1);
            String endtime = cursor.getString(2);
            String org = cursor.getString(3);
            String trl = cursor.getString(4);
            String ext = cursor.getString(5);
            String img = cursor.getString(6);

            resultData.append(id).append(": ");
            resultData.append(begintime).append(": ");
            resultData.append(endtime).append(": ");
            resultData.append(org).append(": ");
            resultData.append(trl).append(": ");
            resultData.append(ext).append(": ");
            resultData.append(img).append(": ");
            resultData.append("\n");
        }
        result.setText(resultData);
    }

}
