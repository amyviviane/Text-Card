package com.example.amylilian.text_card;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;
import android.util.Log;

import static java.security.AccessController.getContext;

/**
 * Created by judy9 on 2016/11/29.
 */

public class DBHelper extends SQLiteOpenHelper {
    //"/data/data/com.example.amylilian.text_card/databases/"
    /* private static String DB_PATH = "/data" + Environment.getDataDirectory().getAbsolutePath() + "/"
        + getApplicationContext().getPackageName();*/

    // 資料庫路徑***
    public static final String DB_LOCATION ="/data/data/com.example.amylilian.text_card/database";
    // 資料庫名稱
    public static final String DB_NAME = "card.sql";
    // 資料庫版本，資料結構改變的時候要更改這個數字，通常是加一
    public static final int VERSION = 1;
    // 資料庫物件，固定的欄位變數
    private static SQLiteDatabase database;
    //context只有private***
    private Context context;

    private static final String TAG = "DBHelper";

    // 新簡單/一般版本建構子
    public DBHelper(Context context) {
        super(context, DB_NAME, null, 1);
        this.context = context;
    }
    // 建構子(可以修改成僅有Context context)
    /*public DBHelper(Context context, String name, CursorFactory factory,
                      int version) {
        super(context, name, factory, version);
        //DB_PATH = context.getDatabasePath(DB_NAME).getPath();
        Log.d(TAG,"路徑為"  + DB_PATH);
    }*/


    // 需要資料庫的元件呼叫這個方法，這個方法在一般的應用都不需要修改
    public static SQLiteDatabase getDatabase(Context context) {
        if (database == null || !database.isOpen()) {
            database = new DBHelper(context).getWritableDatabase();
        }
        return database;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {  //覆寫並實作
        String DATABASE_CREATE_TABLE =
                "create table newCard("
                        + "ID,"
                        + "BeginTime,"
                        + "EndTime,"
                        + "ORG,"
                        + "TRL,"
                        + "EXT,"
                        + "IMG"
                        + ")";
        db.execSQL(DATABASE_CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    //oldVersion=舊的資料庫版本；newVersion=新的資料庫版本
    db.execSQL("DROP TABLE IF EXISTS"); //刪除舊有的資料表
    onCreate(db);
}
    //12.12新增open及close
    public void opendatabase() {
        String DB_PATH = context.getDatabasePath(DB_NAME).getPath();
        if(database != null && database.isOpen()){
            return;
        }
        database = SQLiteDatabase.openDatabase(DB_PATH, null, SQLiteDatabase.OPEN_READWRITE);
    }
    public void closedatabase() {
        if(database != null){
            database.close();
        }
    }
    /*
    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        // TODO 每次成功打開數據庫後首先被執行
    }

    @Override
    public synchronized void close() {
        super.close();
    }*/
}
