package com.example.weight_watcher.Model.User;

public class Weight {

    public String currentWeight;
    public Double goalWeight;
    public String initialWeight;

    public Weight(String current,String initWeight, Double goal){
        currentWeight = current;
        goalWeight = goal;
        initialWeight = initWeight;
    }


}
