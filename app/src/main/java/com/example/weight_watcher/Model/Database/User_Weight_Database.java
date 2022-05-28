package com.example.weight_watcher.Model.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.weight_watcher.Model.Database.Schemes.Weight_Database_Scheme;

public class User_Weight_Database extends SQLiteOpenHelper {
    public Weight_Database_Scheme scheme = new Weight_Database_Scheme();
    public SQLiteDatabase readableDB;
    public SQLiteDatabase writableDataBase;

    public User_Weight_Database(Context context) {
        super(context, "users_all_weights", null, 1);

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

        db.execSQL("CREATE TABLE " + scheme.TABLE_NAME + " (" +
                scheme.COL_ID + " INTEGER PRIMARY KEY," +
                scheme.COL_dateWeighed + " TEXT," +
                scheme.COL_currentUser + " TEXT," +
                scheme.COL_current_weight + " TEXT," +
                scheme.COL_initialWeight + " TEXT," +
                scheme.COL_goal_weight + " DOUBLE," +
                scheme.COL_WEIGHTCHANGE + " DOUBLE," +
                scheme.COL_NECK_MEASUREMENT + " DOUBLE," +
                scheme.COL_BICEP_MEASUREMENT + " DOUBLE," +
                scheme.COL_CHEST_MEASUREMENT + " DOUBLE," +
                scheme.COL_WAIST_MEASUREMENT + " DOUBLE," +
                scheme.COL_LEG_MEASUREMENT + " DOUBLE)");


    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + scheme.TABLE_NAME);
        onCreate(db);
    }

    public void addNewColumn(SQLiteDatabase database){
        Log.v("On Upgrade", "On Upgrade");
        database.execSQL("ALTER TABLE " + scheme.TABLE_NAME +" ADD COLUMN " + scheme.COL_NECK_MEASUREMENT);
        database.execSQL("ALTER TABLE " + scheme.TABLE_NAME +" ADD COLUMN " + scheme.COL_BICEP_MEASUREMENT);
        database.execSQL("ALTER TABLE " + scheme.TABLE_NAME +" ADD COLUMN " + scheme.COL_CHEST_MEASUREMENT);
        database.execSQL("ALTER TABLE " + scheme.TABLE_NAME +" ADD COLUMN " + scheme.COL_WAIST_MEASUREMENT);
        database.execSQL("ALTER TABLE " + scheme.TABLE_NAME +" ADD COLUMN " + scheme.COL_LEG_MEASUREMENT);
    }

}
