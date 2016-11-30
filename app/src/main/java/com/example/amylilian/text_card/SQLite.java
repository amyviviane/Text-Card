package com.example.amylilian.text_card;

import android.content.Context;
import android.content.res.AssetManager;
import android.database.sqlite.SQLiteDatabase;
import android.database.Cursor;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.ArrayList;
/**
 * Created by judy9 on 2016/11/29.
 */

public class SQLite {
    private String DB_PATH;
    private final String DB_NAME="card.sql";
    private final Context context;
    private SQLiteDatabase myDataBase;

    public SQLite(Context context){
        this.context= context;
        this.DB_PATH=this.context.getFilesDir().getAbsolutePath() +"/";
        if(checkDbExists()==false)
            try {
                this.CopyDB();
            } catch (IOException e) {
                e.printStackTrace();
            }
    }

    private Boolean checkDbExists(){
        return new File(this.DB_PATH+this.DB_NAME).exists();
    }

    public void CopyDB() throws IOException {
        AssetManager assetManager = this.context.getAssets();
        InputStream in = assetManager.open(this.DB_NAME);
        OutputStream out = new FileOutputStream(this.DB_PATH+this.DB_NAME);
        byte[] buffer = new byte[1024];
        int read;
        while((read = in.read(buffer)) != -1){
            out.write(buffer, 0, read);
        }
        in.close();
        out.flush();
        out.close();
    }

    public void OpenDB() throws SQLException {
        this.myDataBase = SQLiteDatabase.openDatabase(this.DB_PATH+this.DB_NAME
                , null,SQLiteDatabase.OPEN_READWRITE);
    }

    public void CloseDB() {
        if(myDataBase != null)
            myDataBase.close();

    }

    public Cursor Select(){
        Cursor cursor = this.myDataBase.rawQuery("SELECT * FROM table_name"
                ,null);
        cursor.moveToFirst();
        return cursor;
    }

    public void runSQL(String sql){
        this.myDataBase.execSQL(sql);
    }
}
