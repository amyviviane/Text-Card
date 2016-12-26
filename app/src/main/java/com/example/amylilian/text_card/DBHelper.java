package com.example.amylilian.text_card;

import android.content.Context;
import android.content.res.AssetManager;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.net.URLDecoder;
import java.util.Scanner;

import static java.security.AccessController.getContext;

/**
 * Created by judy9 on 2016/11/29.
 */

public class DBHelper extends SQLiteOpenHelper {

    // 資料庫名稱
    public static final String DB_NAME = "card.db";
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

        File f = new File(context.getFilesDir().getAbsolutePath() + this.DB_NAME);
        if (f.exists()) {
            database = SQLiteDatabase.openOrCreateDatabase(context.getFilesDir().getAbsolutePath() + this.DB_NAME, null);
        } else {
            CopyDB();
            database = SQLiteDatabase.openOrCreateDatabase(context.getFilesDir().getAbsolutePath() + this.DB_NAME, null);
        }

    }

    @Override
    public void onCreate(SQLiteDatabase db) {  //覆寫並實作
        CopyDB();
        database = SQLiteDatabase.openOrCreateDatabase(context.getFilesDir().getAbsolutePath() + this.DB_NAME, null);
    }

    public void CopyDB() {
        InputStream in = null;
        OutputStream out = null;
        try {
            in = this.context.getAssets().open(this.DB_NAME);
            out = new FileOutputStream(context.getFilesDir().getAbsolutePath()
                    + this.DB_NAME);
            byte[] buffer = new byte[1024];
            int read;
            while((read = in.read(buffer)) != -1){
                out.write(buffer, 0, read);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (in != null) {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (out != null) {
            try {
                out.flush();
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    //oldVersion=舊的資料庫版本；newVersion=新的資料庫版本
        db.execSQL("DROP TABLE IF EXISTS"); //刪除舊有的資料表
        onCreate(db);
    }

    @Override
    public SQLiteDatabase getWritableDatabase() {
        if (database == null) {
            System.out.println("database is null");
        }
        return database;
    }
}
