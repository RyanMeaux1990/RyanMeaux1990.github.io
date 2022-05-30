package com.example.weight_watcher.Model.Measurements;
/*
DEFINES THE MEASUREMENTS FOR EACH USER
USES THE WEIGHT CLASS
 */
import com.example.weight_watcher.Model.User.Weight;

public class Measurements {
    public double neck;
    public double bicep;
    public double chest;
    public double waist;
    public double leg;
    public Weight weight;

    //DEFAULT Initializer
    public Measurements(){
        this.neck = 0.0;
        this.bicep = 0.0;
        this.chest = 0.0;
        this.waist = 0.0;
        this.leg = 0.0;
        this.weight = new Weight("0","0",0.0);
    }

    //INITIALIZER USING THE WEIGHT CLASS
    public Measurements(double Neck,double Bicep,double Chest, double Waist, Double Leg, Weight Weight){
        neck = Neck;
        bicep = Bicep;
        waist = Waist;
        leg = Leg;
        weight = Weight;
    }
}
