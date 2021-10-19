package com.example.weight_watcher.Model.Database.Schemes;

public class User_Database_Scheme {
    public Integer VERSION;
    public String TABLE_NAME = "application_users";
    public String COL_ID;
    public String COL_FIRSTNAME;
    public String COL_LASTNAME;
    public String COL_EMAIL;
    public String COL_PASSWORD;
    public String primary;
    public String TEXT;
public User_Database_Scheme() {


   VERSION = 1;
   COL_ID = "_id";
   COL_FIRSTNAME = "first_name";
   COL_LASTNAME = "last_name";
   COL_EMAIL = "email";
   COL_PASSWORD = "password";
   primary = "INTEGER PRIMARY KEY, ";
   TEXT = " text, ";
}
}
