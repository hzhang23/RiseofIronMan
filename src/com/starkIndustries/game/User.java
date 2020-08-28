package com.starkIndustries.game;

public class User {
  private String userId;
     //Ctor
    public User(String userId) {
        this.userId = userId;
    }

    //getter and setter
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
