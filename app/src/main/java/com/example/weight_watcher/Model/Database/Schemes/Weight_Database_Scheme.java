package com.example.weight_watcher.Model.Database.Schemes;

public class Weight_Database_Scheme {
    public String COL_ID;
    public String TABLE_NAME;
    public String COL_currentUser;
    public String COL_dateWeighed;
    public String COL_initialWeight;
    public String COL_goal_weight;
    public String COL_current_weight;
    public String COL_WEIGHTCHANGE;
    public String COL_NECK_MEASUREMENT;
    public String COL_CHEST_MEASUREMENT;
    public String COL_BICEP_MEASUREMENT;
    public String COL_WAIST_MEASUREMENT;
    public String COL_LEG_MEASUREMENT;
    public double DB_VERSION;




    public Weight_Database_Scheme(){
         DB_VERSION = 2.0;
         TABLE_NAME = "users_all_weights";
         COL_ID = "_id";
         COL_currentUser = "email" ;
         COL_dateWeighed = "date_of_weight";
         COL_goal_weight = "goal_weight";
         COL_current_weight = "current_weight";
         COL_initialWeight = "initial_weight";
         COL_WEIGHTCHANGE = "weight_change";
         COL_NECK_MEASUREMENT = "neck_measurment";
         COL_CHEST_MEASUREMENT = "chest_measurement";
         COL_BICEP_MEASUREMENT = "bicep_measurement";
         COL_WAIST_MEASUREMENT = "waist_measurement";
         COL_LEG_MEASUREMENT = "leg_measurement";
    }
}
