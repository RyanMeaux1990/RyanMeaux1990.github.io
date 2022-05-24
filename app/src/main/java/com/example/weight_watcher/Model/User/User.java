package com.example.weight_watcher.Model.User;

import com.example.weight_watcher.Model.User.Credentials.Credential;

public class User {
    public String firstName;
    public Credential userCredentials;
    public String lastName;
    public Weight weight;
    public String phoneNumber;
    public User(String fName, String lName, String Uemail, String Upassword, String currentWeight, Double UgoalWeight, String PhoneNumber) {
        firstName = fName;
        lastName = lName;
        phoneNumber = PhoneNumber;
        userCredentials = new Credential(Uemail, Upassword);
        weight = new Weight(currentWeight, currentWeight, UgoalWeight);
    }

    public Credential returnUserCredentials() {
        return userCredentials;
    }

    public Weight getWeight() {
        return weight;
    }
}
