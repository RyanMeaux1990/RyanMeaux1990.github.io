package com.example.weight_watcher.Model.Database.Cursors;

import android.database.Cursor;

public class Users_Weight_DB_Results {
    private Cursor cursor;
    public String email;
    public String date;
    public String initialWeight;
    public Double goalWeight;
    public String currentWeight;
    public Double weightChange;
    public String[] columnHeaders;


    public Users_Weight_DB_Results(Cursor currentCursor){
        cursor = currentCursor;
        cursor.moveToLast();
        getColHeaders();
        getResult();

    }
    private void getColHeaders(){
        columnHeaders = cursor.getColumnNames();
    }

    private String getStringColumn(int colIndex){
        return cursor.getString(colIndex);

    }

    private Double getDoubleCol(int colIndex){
        return cursor.getDouble(colIndex);
    }

    public void getResult(){

        date = cursor.getString(1);
        email = cursor.getString(2);
        currentWeight = cursor.getString(3);
        goalWeight = cursor.getDouble(4);
        weightChange = cursor.getDouble(5);
        weightChange = cursor.getDouble(6);





    }


}
