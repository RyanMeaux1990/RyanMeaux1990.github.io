package com.example.weight_watcher.Model.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.weight_watcher.Model.Database.Schemes.User_Database_Scheme;

public class Users extends SQLiteOpenHelper {
    public User_Database_Scheme scheme = new User_Database_Scheme();
    public SQLiteDatabase writableDataBase;
    public SQLiteDatabase readableDatabase;

    public Users(Context context) {

        super(context, "application_users", null, 1);
    }
    public void getReadDatabase(){

        readableDatabase = getReadableDatabase();
    }
    public void getWritableDb(){
        writableDataBase = getWritableDatabase();
    }
    public void onCreate(SQLiteDatabase db) {


        db.execSQL("create table " + scheme.TABLE_NAME + " (" +
                scheme.COL_ID + "INTEGER PRIMARY KEY, " +
                scheme.COL_FIRSTNAME + " text, " +
                scheme.COL_LASTNAME + " text, " +
                scheme.COL_EMAIL + " text, " +
                scheme.COL_PASSWORD + " text)");

    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + scheme.TABLE_NAME);
        onCreate(db);
    }



}