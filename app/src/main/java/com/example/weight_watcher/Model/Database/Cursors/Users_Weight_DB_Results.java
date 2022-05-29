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
    public Double neck;
    public Double chest;
    public Double bicep;
    public Double waist;
    public Double leg;


    public Users_Weight_DB_Results(Cursor currentCursor){
        cursor = currentCursor;
        if(cursor.isLast() == false){
            cursor.moveToLast();
        }

        //getColHeaders();
         getResult();

    }
    public String[] getColHeaders(){
        columnHeaders = cursor.getColumnNames();
        return columnHeaders;
    }

    private String getStringColumn(int colIndex){
        return cursor.getString(colIndex);

    }

    private Double getDoubleCol(int colIndex){
        return cursor.getDouble(colIndex);
    }

    public void getResult(){
        /*
        V/Columns: date_of_weight
        V/Columns: email
        V/Columns: current_weight
        V/Columns: initial_weight
        V/Columns: goal_weight
        V/Columns: weight_change
        V/Columns: neck_measurment
        V/Columns: bicep_measurement
        V/Columns: chest_measurement
        V/Columns: waist_measurement
        V/Columns: leg_measurement
        */



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
        //leg = cursor.getDouble(11);

    }



    public void getLastUserEntry() {

        if(cursor.isLast() == false) {
            getResult();


        }else {
            cursor.close();
        }

    }


}
