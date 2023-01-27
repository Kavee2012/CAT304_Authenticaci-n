package com.example.auntentication;

public class Credentials {

    String id, password, platform;

    public Credentials(){}

    public Credentials(String id, String password, String platform) {
        this.id = id;
        this.password = password;
        this.platform = platform;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }
}
