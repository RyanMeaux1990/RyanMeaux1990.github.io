package com.example.weight_watcher.Model.Database.Cursors;
/*
Returned results of the Weight Database
 */

import android.database.Cursor;
import android.util.Log;

public class Users_Weight_DB_Results {
    private Cursor cursor;
    public String email;
    public String date;
    public String initialWeight;
    public Double goalWeight;
    public String currentWeight;
    public Double weightChange;
    public String[] columnHeaders;
    public Double neck;
    public Double chest;
    public Double bicep;
    public Double waist;
    public Double leg;

    //Initializer
    public Users_Weight_DB_Results(Cursor currentCursor){
        cursor = currentCursor;
        if(cursor.isLast() == false){
            cursor.moveToLast();
        }
        int bis = currentCursor.getColumnIndex("biceps");

         getResult();

    }

    public Users_Weight_DB_Results(Cursor the_cursor, Boolean isLast){

        if(isLast == false){
            cursor = the_cursor;
            getResult();
        }
    }

    //Returns an array of the colomn headers
    public String[] getColHeaders(){
        columnHeaders = cursor.getColumnNames();
        return columnHeaders;
    }

    //Returns a col thats a string
    private String getStringColumn(int colIndex){
        return cursor.getString(colIndex);

    }

    //Returns a col that is a double
    private Double getDoubleCol(int colIndex){
        return cursor.getDouble(colIndex);
    }

    //Returns a full database Result
    public void getResult(){

        date = cursor.getString(1);
        email = cursor.getString(2);
        currentWeight = cursor.getString(3);
        initialWeight = cursor.getString(4);
        goalWeight = cursor.getDouble(5);
        weightChange = cursor.getDouble(6);
        neck = cursor.getDouble(7);
        chest = cursor.getDouble(9);
        bicep = cursor.getDouble(8);
        waist = cursor.getDouble(10);
        leg = cursor.getDouble(11);

        Log.v("Results",String.valueOf(initialWeight));

    }

    //Returns the last entry within the Cursor
    public void getLastUserEntry() {

        if(cursor.isLast() == false) {
            getResult();


        }else {
            cursor.close();
        }

    }


}
