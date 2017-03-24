package com.fanda.zeng.bookpratice.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

/**
 * Created by 曾凡达 on 2017/3/23.
 * des 数据库辅助类
 */

public class MyDatabaseHelper extends SQLiteOpenHelper{

    private Context context;

    public static final String CREATE_TABLE = "create table Book ("
            + "id integer primary key  autoincrement,"
            + "author text,"
            + "price real,"
            + "pagges integer,"
            + "name text)";

    public MyDatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
        Toast.makeText(context, "create table success", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
