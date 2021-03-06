package com.krp.android.demogooglegson;

/**
 * Created by purushottam.kumar on 11/26/2015.
 */
public class User {
    private String username;
    private String password;
    private String type;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.type = "defaultType";
    }

    public User(String username, String password, String userType) {
        this.username = username;
        this.password = password;
        this.type = userType;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", type=" + type +
                '}';
    }
}
