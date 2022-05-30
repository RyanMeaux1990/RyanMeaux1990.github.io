package com.example.weight_watcher.Model.SharedPref;
//DEFINES THE SHARED PREFERENCES USED

import android.content.Context;
import android.content.SharedPreferences;

import com.example.weight_watcher.R;

public class Preferences {
    private SharedPreferences sp;
    private SharedPreferences.Editor editor;
    public Preference_Layout layout;

    public Preferences(Context context){
        this.sp = context.getSharedPreferences(String.valueOf(R.string.userPreference), Context.MODE_PRIVATE);
        this.editor = sp.edit();
        this.layout = new Preference_Layout();
    }

    public void putString(String name, String value){
        this.sp.edit().putString(name,value).commit();
    }


    public String getString(String name){
      return this.sp.getString("User", null);

    }




}
