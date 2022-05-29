package com.example.weight_watcher.Controllers.Database;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.weight_watcher.Model.Database.Cursors.Users_Database_Results;
import com.example.weight_watcher.Model.Database.Schemes.User_Database_Scheme;
import com.example.weight_watcher.Model.Database.Users;
import com.example.weight_watcher.Model.User.User;
import com.example.weight_watcher.R;

public class Users_Database_Controller {
    public Users usersDatabase;
    public SQLiteDatabase writable;
    public SQLiteDatabase readable;
    public ContentValues values;
    public User_Database_Scheme scheme;
    public Users_Database_Results results;
    private Context pageContext;
    public String email;
    public String currentUser;
    public String phoneNumber;

    public Users_Database_Controller(Context context){
        usersDatabase = new Users(context);
        usersDatabase.getReadDatabase();
        usersDatabase.getWritableDb();
        writable = usersDatabase.writableDataBase;
        readable = usersDatabase.readableDatabase;
        scheme = new User_Database_Scheme();
        values = new ContentValues();
        pageContext = context;
        SharedPreferences sp = context.getSharedPreferences(String.valueOf(R.string.userPreference),Context.MODE_PRIVATE);
        email = sp.getString("User",null);
    }

    public long addNewUserToDataBase(User currentUser){
        values.put(usersDatabase.scheme.COL_FIRSTNAME, currentUser.firstName);
        values.put(usersDatabase.scheme.COL_LASTNAME, currentUser.lastName);
        values.put(usersDatabase.scheme.COL_EMAIL, currentUser.userCredentials.username);
        values.put(usersDatabase.scheme.COL_PASSWORD, currentUser.userCredentials.password);
        values.put(usersDatabase.scheme.COL_PHONE_NUMBER,currentUser.phoneNumber);

        long newUserId = writable.insert(usersDatabase.scheme.TABLE_NAME,null,values);
        return newUserId;
    }

    public Boolean checkAuthentication(String userName, String password) {
        Cursor cursor = writable.rawQuery("Select * from "+ usersDatabase.scheme.TABLE_NAME +" where email = ? and password =?", new String[]{userName, password});
        if (cursor.getCount() > 0) {
            cursor.close();
            return true;
        } else {
            cursor.close();
            return false;
        }

    }

    public Boolean checkForUserInDatabase(String userName){
        Cursor cursor = writable.rawQuery("Select * from "+ usersDatabase.scheme.TABLE_NAME + " where email = ?", new String[] {userName});
        Log.v("USer", cursor.getColumnName(3));
        if(cursor.getCount() > 0){
            cursor.close();
            return true;
        }else {
            cursor.close();
            return false;
        }

    }

    public Users_Database_Results findAuthenticatedUser(){


        try {
            Cursor cursor = writable.rawQuery("Select * from "+ usersDatabase.scheme.TABLE_NAME +" where email = ?", new String[] {this.email});
            results = new Users_Database_Results(cursor);
            cursor.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return results;
    }

    public String findUsersPhoneNumber(String theEmail){

        try {
            Cursor cursor = writable.rawQuery("Select * from "+ usersDatabase.scheme.TABLE_NAME +" where email = ?", new String[] {theEmail});
            results = new Users_Database_Results(cursor);
            phoneNumber = results.phoneNumber;
            Log.v("Number",results.phoneNumber);
            cursor.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return phoneNumber;
    }
    public Users_Database_Results GetUser(String Users_Email){
        try {
            Cursor cursor = writable.rawQuery("Select * from "+ usersDatabase.scheme.TABLE_NAME +" where email = ?", new String[] {Users_Email});
            results = new Users_Database_Results(cursor);
            cursor.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return results;
    }

}
