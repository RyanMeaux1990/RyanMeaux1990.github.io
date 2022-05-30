package com.example.weight_watcher.Model.Database;
/*
The Users Database
 */
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import com.example.weight_watcher.Model.Database.Schemes.User_Database_Scheme;

public class Users extends SQLiteOpenHelper {

    public User_Database_Scheme scheme = new User_Database_Scheme();

    public SQLiteDatabase writableDataBase;

    public SQLiteDatabase readableDatabase;

    public Users(Context context) {

        super(context, "the_app_db", null, 1);

    }

    public void getReadDatabase(){

        readableDatabase = getReadableDatabase();
    }

    public void getWritableDb(){
        writableDataBase = getWritableDatabase();
    }

    public void onCreate(SQLiteDatabase db) {
        Log.v("On Create", "On create");

        db.execSQL("create table " + scheme.TABLE_NAME + " (" +
                scheme.COL_ID + " INTEGER PRIMARY KEY, " +
                scheme.COL_FIRSTNAME + " TEXT, " +
                scheme.COL_LASTNAME + " TEXT, " +
                scheme.COL_EMAIL + " TEXT, " +
                scheme.COL_PASSWORD + " TEXT, " +
                scheme.COL_PHONE_NUMBER + " TEXT)");

    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("drop table if exists " + scheme.TABLE_NAME);
        onCreate(db);
    }
    
}