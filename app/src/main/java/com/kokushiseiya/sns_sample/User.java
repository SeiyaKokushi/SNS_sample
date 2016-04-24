package com.kokushiseiya.sns_sample;

public class User {

    private String userId;
    private String userName;

    //空のコンストラクタ
    public User(){ }

    public User(String userId, String userName){
        this.userId = userId;
        this.userName = userName;
    }

    public String getUserId(){
        return this.userId;
    }

    public String getUserName(){
        return this.userName;
    }
}
