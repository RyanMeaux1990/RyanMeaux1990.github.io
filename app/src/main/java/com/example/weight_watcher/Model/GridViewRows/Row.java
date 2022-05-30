package com.example.weight_watcher.Model.GridViewRows;
/*
DEFINES THE ROWS WITHIN THE MAIN SCROLL VIEW
 */
public class Row {
    public int ID;
    public String user;
    public String date;
    public String weightThatDay;
    public Double goalWeight;


    public Row(int Id,String user,String Weight, Double GoalWeight, String DateOfWeighIn){
        ID = Id;
        date=DateOfWeighIn;
        weightThatDay = Weight;
        goalWeight = GoalWeight;

    }
}
