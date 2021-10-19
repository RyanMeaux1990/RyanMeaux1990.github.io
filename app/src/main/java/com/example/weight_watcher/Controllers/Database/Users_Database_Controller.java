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

        long newUserId = writable.insert(usersDatabase.scheme.TABLE_NAME,null,values);
        return newUserId;
    }

    public Boolean checkAuthentication(String userName, String password) {
        Cursor cursor = writable.rawQuery("Select * from application_users where email = ? and password =?", new String[]{userName, password});
        if (cursor.getCount() > 0) {
            cursor.close();
            return true;
        } else {
            cursor.close();
            return false;
        }

    }

    public Boolean checkForUserInDatabase(String userName){
        Cursor cursor = writable.rawQuery("Select * from application_users where email = ?", new String[] {userName});
        if(cursor.getCount() > 0){
            cursor.close();
            return true;
        }else {
            cursor.close();
            return false;
        }

    }

    public void findAuthenticatedUser(){
        Log.v(this.email,"Find user");

        try {
            Cursor cursor = writable.rawQuery("Select * from application_users where email = ?", new String[] {this.email});
            results = new Users_Database_Results(cursor);
            cursor.close();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

}
