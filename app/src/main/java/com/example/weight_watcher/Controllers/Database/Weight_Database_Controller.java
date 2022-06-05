package com.example.weight_watcher.Controllers.Database;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.weight_watcher.Model.Database.Cursors.Users_Weight_DB_Results;
import com.example.weight_watcher.Model.Database.Schemes.Weight_Database_Scheme;
import com.example.weight_watcher.Model.Database.User_Weight_Database;
import com.example.weight_watcher.Model.GridViewRows.Row;
import com.example.weight_watcher.Model.Measurements.Measurements;
import com.example.weight_watcher.Model.User.User;
import com.example.weight_watcher.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Weight_Database_Controller {
    public User_Weight_Database weightDatabase;
    public SQLiteDatabase writable;
    public SQLiteDatabase readable;
    public ContentValues values;
    public Weight_Database_Scheme scheme;
    public String weightChange;
    public String[] columnHeads;
    public Users_Weight_DB_Results results;
    public Row[] rows;
    private String email;
    private SharedPreferences sp;
    Measurements default_measurements = new Measurements();

    public Weight_Database_Controller(Context context){
        weightDatabase = new User_Weight_Database(context);

        readable = weightDatabase.getReadDatabase();
        writable = weightDatabase.getWritableDb();
        scheme = new Weight_Database_Scheme();
        values = new ContentValues();
        sp = context.getSharedPreferences(String.valueOf(R.string.userPreference),Context.MODE_PRIVATE);





    }
    public double calculateWeightDifference(double current,double start){
        return start - current;

    }
    public long addNewWeight(User currentUser, Measurements measurements){
        ContentValues newWeightContent = new ContentValues();
        String timeStamp = new SimpleDateFormat("MMddyyyy").format(Calendar.getInstance().getTime());
        Float change = Float.valueOf(currentUser.weight.initialWeight) - Float.valueOf(String.valueOf(currentUser.weight.currentWeight));
        newWeightContent.put(scheme.COL_currentUser, currentUser.userCredentials.username);
        newWeightContent.put(scheme.COL_initialWeight,currentUser.weight.initialWeight);
        newWeightContent.put(scheme.COL_dateWeighed,timeStamp);
        newWeightContent.put(scheme.COL_goal_weight, currentUser.weight.goalWeight);
        newWeightContent.put(scheme.COL_current_weight,measurements.weight.currentWeight);
        newWeightContent.put(scheme.COL_WEIGHTCHANGE,change);
        newWeightContent.put(scheme.COL_NECK_MEASUREMENT,measurements.neck);
        newWeightContent.put(scheme.COL_BICEP_MEASUREMENT,measurements.bicep);
        newWeightContent.put(scheme.COL_CHEST_MEASUREMENT,measurements.chest);
        newWeightContent.put(scheme.COL_WAIST_MEASUREMENT,measurements.waist);
        newWeightContent.put(scheme.COL_LEG_MEASUREMENT,measurements.leg);

        long number = writable.insert(scheme.TABLE_NAME,null,newWeightContent);
        Log.v("Insert #", String.valueOf(number));
        return number;
    }
    //Adds a new user to the Weight Tracking DB and returns the number
    public Long addNewUser(User user, Measurements measurements){
        ContentValues content = new ContentValues();
        String timeStamp = new SimpleDateFormat("MMddyyyy").format(Calendar.getInstance().getTime());
        Double change = calculateWeightDifference(user.weight.goalWeight,Float.valueOf(user.weight.currentWeight));
        content.put(scheme.COL_currentUser, user.userCredentials.username);
        content.put(scheme.COL_dateWeighed,timeStamp);
        content.put(scheme.COL_goal_weight, user.weight.goalWeight);
        content.put(scheme.COL_current_weight,user.weight.currentWeight);
        content.put(scheme.COL_initialWeight,user.weight.currentWeight);
        content.put(scheme.COL_WEIGHTCHANGE,change);
        content.putNull(scheme.COL_NECK_MEASUREMENT);
        content.putNull(scheme.COL_BICEP_MEASUREMENT);
        content.putNull(scheme.COL_WAIST_MEASUREMENT);
        content.putNull(scheme.COL_CHEST_MEASUREMENT);
        content.putNull(scheme.COL_LEG_MEASUREMENT);


        return writable.insert(scheme.TABLE_NAME,null,content);




    }

    //Returns True if user is in the Weight DB
    public Boolean checkForUserInDatabase(String userEmail){
        Boolean isInDataBase = false;

        try {


            Cursor cursor = writable.rawQuery("Select * from " + scheme.TABLE_NAME + " where " + scheme.COL_currentUser + " = ?", new String[]{userEmail});
            if (cursor.getCount() > 0) {
                isInDataBase = true;
            } else {
                isInDataBase = false;
            }
            return isInDataBase;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    //Gets the user from the DB
    public void getUser(String theEmail) {
        Log.v("Cursor Get User",theEmail);
        Cursor cursor = writable.rawQuery("Select * from " + scheme.TABLE_NAME + " where " + scheme.COL_currentUser + " = ?", new String[]{theEmail});
        Log.v("Length",String.valueOf(cursor.getCount()));
        if(cursor.getCount() >0) {
            results = new Users_Weight_DB_Results(cursor);
        }




    }

    //Returns all the db Results in an Array
    public Row[] getAllUserWeights(String email){
        Cursor newCursor = null;
try {
    newCursor = writable.rawQuery("Select * from " + scheme.TABLE_NAME + " where " + scheme.COL_currentUser + " = ?", new String[]{email});


    Row[] rows = new Row[newCursor.getCount()];

    for (int i = 0; i < newCursor.getCount(); ++i){
        newCursor.moveToNext();
        int userIndex = newCursor.getColumnIndex(scheme.COL_currentUser);
        String user = newCursor.getString(userIndex);
        int id = newCursor.getPosition();
        String date = newCursor.getString(1);
        String weight = newCursor.getString(3);
        Double goal = newCursor.getDouble(5);
        Row newRow = new Row(id,user,weight,goal,date);
        rows[i] = newRow;


    }
    newCursor.close();
    return rows;


} catch (Exception e) {
    return rows;
}



    }

    //Deletes the selected row on the Grid View
    public int deleteSelectedRow(Row selectedRow){
        String date = selectedRow.date;
        String weight = String.valueOf(selectedRow.weightThatDay);
        double goal = selectedRow.goalWeight;


        return writable.delete(scheme.TABLE_NAME,""+scheme.COL_dateWeighed+" = ? and "+scheme.COL_current_weight+" = ?",new String[]{date,weight});
    }

    //Updates A Row On the Grid View
    public long updateSelectedRow(Row selectedRow, String updatedDate, Double updatedWeight){

       values.put(scheme.COL_dateWeighed, updatedDate);
       values.put(scheme.COL_current_weight,updatedWeight);


        return writable.update(scheme.TABLE_NAME,values,""+scheme.COL_dateWeighed+" = ? and "+scheme.COL_current_weight+" = ?",new String[]{selectedRow.date, String.valueOf(selectedRow.weightThatDay)});
    }

    //Return the last entry
    public Users_Weight_DB_Results getLastEntry(String Useremail) {
        Cursor newCursor = writable.rawQuery("Select * from " + scheme.TABLE_NAME + " where " + scheme.COL_currentUser + " = ?", new String[]{Useremail});
        return new Users_Weight_DB_Results(newCursor);
    }

    public Cursor GetEntry(String Useremail) {
        return writable.rawQuery("Select * from " + scheme.TABLE_NAME + " where " + scheme.COL_currentUser + " = ?", new String[]{Useremail});
    }
    //Get all the users weights and return them as an array
    public void getUsersWeights(String email) {

        Cursor cursor = writable.rawQuery("Select * from " + scheme.TABLE_NAME + " where " + scheme.COL_currentUser + " = ?", new String[]{email});
        String[] columns = cursor.getColumnNames();

        if (cursor.getCount() > 0) {
            results = new Users_Weight_DB_Results(cursor);

        }


    }

    //Get all columns for spinner
    public String[] getColumnHeaders(){
        Cursor cursor = writable.rawQuery("Select * from " + scheme.TABLE_NAME + " where " + scheme.COL_currentUser + " = ?", new String[]{email});
        cursor.close();
        return cursor.getColumnNames();
    }

    public Users_Weight_DB_Results[] GetChartData(String theUsersEmail){
        Cursor cursor = writable.rawQuery("Select * from " + scheme.TABLE_NAME + " where " + scheme.COL_currentUser + " = ?", new String[]{theUsersEmail});
        Users_Weight_DB_Results[] results = new Users_Weight_DB_Results[cursor.getCount()];

        cursor.moveToFirst();
        for(int i = 0; i < cursor.getCount(); ++i){

                results[i] = new Users_Weight_DB_Results(cursor,cursor.isLast());


        }
        return results;
    }



}
