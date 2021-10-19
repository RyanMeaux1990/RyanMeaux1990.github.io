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



    public Weight_Database_Scheme(){
         TABLE_NAME = "users_new_weights";
         COL_ID = "_id";
         COL_currentUser = "email" ;
         COL_dateWeighed = "date_of_weight";
         COL_goal_weight = "goal_weight";
         COL_current_weight = "current_weight";
         COL_initialWeight = "initial_weight";
         COL_WEIGHTCHANGE = "weight_change";
    }
}
