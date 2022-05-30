package com.example.weight_watcher.Model.Database.Cursors;
/*
Results that are returned from the Users Database
 */
import android.database.Cursor;

public class Users_Database_Results {
    Cursor cursor;
    public String firstName;
    public String lastName;
    public String email;
    public String password;
    public String phoneNumber;


    //Initializer
    public Users_Database_Results(Cursor currentCursor) {
        cursor = currentCursor;
        getLastUserEntry();

    }

    //Gets the last entry
    public void getLastUserEntry() {

        if(cursor.isLast() == false) {
            cursor.moveToNext();
            firstName = cursor.getString(1);
            lastName = cursor.getString(2);
            email = cursor.getString(3);
            password = cursor.getString(4);
            phoneNumber = cursor.getString(5);

        }else {
            cursor.close();
        }

    }

}
