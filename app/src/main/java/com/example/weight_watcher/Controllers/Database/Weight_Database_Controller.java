package com.example.weight_watcher.Controllers.Database;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.example.weight_watcher.Model.Database.Cursors.Users_Weight_DB_Results;
import com.example.weight_watcher.Model.Database.Schemes.Weight_Database_Scheme;
import com.example.weight_watcher.Model.Database.User_Weight_Database;
import com.example.weight_watcher.Model.GridViewRows.Row;
import com.example.weight_watcher.Model.User.User;
import com.example.weight_watcher.Model.User.Weight;
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


    public Weight_Database_Controller(Context context){
        weightDatabase = new User_Weight_Database(context);
        readable = weightDatabase.getReadDatabase();
        writable = weightDatabase.getWritableDb();
        scheme = new Weight_Database_Scheme();
        values = new ContentValues();
        sp = context.getSharedPreferences(String.valueOf(R.string.userPreference),Context.MODE_PRIVATE);
        email = sp.getString("User",null);

    }
    public double calculateWeightDifference(double current,double start){
        return start - current;

    }
    public long addNewWeight(User currentUser, Weight weight){
        String timeStamp = new SimpleDateFormat("MMddyyyy").format(Calendar.getInstance().getTime());
        Float change = Float.valueOf(currentUser.weight.initialWeight) - Float.valueOf(weight.currentWeight);
        values.put(scheme.COL_currentUser, currentUser.userCredentials.username);
        values.put(scheme.COL_initialWeight,currentUser.weight.initialWeight);
        values.put(scheme.COL_dateWeighed,timeStamp);
        values.put(scheme.COL_goal_weight, currentUser.weight.goalWeight);
        values.put(scheme.COL_current_weight,weight.currentWeight);
        values.put(scheme.COL_WEIGHTCHANGE,change);

        return writable.insert(scheme.TABLE_NAME,null,values);

    }
    public Long addNewUser(User user){
        ContentValues content = new ContentValues();
        String timeStamp = new SimpleDateFormat("MMddyyyy").format(Calendar.getInstance().getTime());
        Double change = calculateWeightDifference(user.weight.goalWeight,Float.valueOf(user.weight.currentWeight));
        content.put(scheme.COL_currentUser, user.userCredentials.username);
        content.put(scheme.COL_dateWeighed,timeStamp);
        content.put(scheme.COL_goal_weight, user.weight.goalWeight);
        content.put(scheme.COL_current_weight,user.weight.currentWeight);
        content.put(scheme.COL_initialWeight,user.weight.currentWeight);
        content.put(scheme.COL_WEIGHTCHANGE,change);
        return writable.insert(scheme.TABLE_NAME,null,content);




    }
    public Boolean checkForUserInDatabase(){
        Boolean isInDataBase = false;
        String newEmail = email;
        try {


            Cursor cursor = writable.rawQuery("Select * from " + scheme.TABLE_NAME + " where " + scheme.COL_currentUser + " = ?", new String[]{newEmail});
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
    public void getUser() {

        Cursor cursor = writable.rawQuery("Select * from users_new_weights where " + scheme.COL_currentUser + " = ?", new String[]{email});

        if(cursor.getCount() >0) {
            results = new Users_Weight_DB_Results(cursor);

        }




    }
    public Row[] getAllUserWeights(String email){
        Cursor newCursor = null;
try {
    newCursor = writable.rawQuery("Select * from users_new_weights where " + scheme.COL_currentUser + " = ?", new String[]{email});

    Row[] rows = new Row[newCursor.getCount()];

    for (int i = 0; i < newCursor.getCount(); ++i){
        newCursor.moveToNext();
        int userIndex = newCursor.getColumnIndex(scheme.COL_currentUser);
        String user = newCursor.getString(userIndex);
        int id = newCursor.getPosition();
        String date = newCursor.getString(1);
        String weight = newCursor.getString(3);
        Double goal = newCursor.getDouble(4);
        Row newRow = new Row(id,user,weight,goal,date);
        rows[i] = newRow;


    }
    newCursor.close();
    return rows;


} catch (Exception e) {
    return rows;
}



    }
    public int deleteSelectedRow(Row selectedRow){
        String date = selectedRow.date;
        String weight = String.valueOf(selectedRow.weightThatDay);
        double goal = selectedRow.goalWeight;


        Integer deleted = writable.delete(scheme.TABLE_NAME,""+scheme.COL_dateWeighed+" = ? and "+scheme.COL_current_weight+" = ?",new String[]{date,weight});
        return deleted;
    }
    public long updateSelectedRow(Row selectedRow, String updatedDate, Double updatedWeight){

       values.put(scheme.COL_dateWeighed, updatedDate);
       values.put(scheme.COL_current_weight,updatedWeight);

       int updated = writable.update(scheme.TABLE_NAME,values,""+scheme.COL_dateWeighed+" = ? and "+scheme.COL_current_weight+" = ?",new String[]{selectedRow.date, String.valueOf(selectedRow.weightThatDay)});



        return updated;
    }
}
