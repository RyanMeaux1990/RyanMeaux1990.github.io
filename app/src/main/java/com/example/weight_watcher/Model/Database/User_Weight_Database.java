package com.example.weight_watcher.Model.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.weight_watcher.Model.Database.Schemes.Weight_Database_Scheme;

public class User_Weight_Database extends SQLiteOpenHelper {
    public Weight_Database_Scheme scheme;
    public SQLiteDatabase readableDB;
    public SQLiteDatabase writableDataBase;


    public User_Weight_Database(Context context) {
        super(context, "users_new_weights", null, 1);

        scheme = new Weight_Database_Scheme();
    }
    public SQLiteDatabase getReadDatabase(){

        readableDB = getReadableDatabase();

        return readableDB;
    }
    public SQLiteDatabase getWritableDb(){
        writableDataBase = getWritableDatabase();
        return writableDataBase;
    }
    public void onCreate(SQLiteDatabase db) {


        db.execSQL("create table " + scheme.TABLE_NAME + " (" +
                scheme.COL_ID + "INTEGER PRIMARY KEY, " +
                scheme.COL_dateWeighed + " text, " +
                scheme.COL_currentUser + " text, " +
                scheme.COL_current_weight + " text, " +
                scheme.COL_goal_weight + " double, " +
                scheme.COL_WEIGHTCHANGE + " double, " +
                scheme.COL_initialWeight + " double )");

    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + scheme.TABLE_NAME);
        onCreate(db);
    }
}
