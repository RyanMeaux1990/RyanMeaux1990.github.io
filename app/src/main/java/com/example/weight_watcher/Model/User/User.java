package com.example.weight_watcher.Model.User;
/*
DEFINES EACH INDIVIDUAL USER
USES CREDENTIAL CLASS AND
WEIGHT CLASS
 */
import com.example.weight_watcher.Model.User.Credentials.Credential;

public class User {

    public String firstName;
    public Credential userCredentials;
    public String lastName;
    public Weight weight;
    public String phoneNumber;

    //Default Initializer
    public User(String fName, String lName, String Uemail, String Upassword, String currentWeight, Double UgoalWeight, String PhoneNumber) {
        firstName = fName;
        lastName = lName;
        phoneNumber = PhoneNumber;
        userCredentials = new Credential(Uemail, Upassword);
        weight = new Weight(currentWeight, currentWeight, UgoalWeight);
    }

    //Returns the users Credentials
    public Credential returnUserCredentials() {
        return userCredentials;
    }

    //Returns the users weight values
    public Weight getWeight() {
        return weight;
    }
}
