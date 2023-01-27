package com.example.auntentication.model;

public class Users {
    private String  Username, Masterpassword;


    public Users(){

    }

    public Users(String Username, String Masterpassword) {
        this.Username = Username;
        this.Masterpassword = Masterpassword;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String Username) {
        this.Username = Username;
    }

    public String getMasterpassword() {
        return Masterpassword;
    }

    public void setMasterpassword(String Masterpassword) {
        this.Masterpassword = Masterpassword;
    }
}
