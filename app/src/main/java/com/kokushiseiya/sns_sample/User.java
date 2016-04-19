package com.kokushiseiya.sns_sample;

/**
 * Created by shunsuke on 16/04/18.
 */
public class User {

    private String userId;
    private String userName;

    //空のコンストラクタ
    public User(){ }

    public User(String userId, String userName){
        this.userId = "@" + userId;
        this.userName = userName;
    }

    public String getUserId(){
        return this.userId;
    }

    public String getUserName(){
        return this.userName;
    }
}
