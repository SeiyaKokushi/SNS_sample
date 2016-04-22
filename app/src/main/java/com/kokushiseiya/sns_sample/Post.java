package com.kokushiseiya.sns_sample;

/**
 * Created by kokushiseiya on 16/04/18.
 */
public class Post {

    private User user;
    private String text; // リストに表示させる文章
    private int likeNum; // Like(ファボ)数

    private String timeStampKey;

    //空のコンストラクタ
    public Post(){ }

    //Postを作るとき
    public Post(User user, String text){
        this.user = user;
        this.text = text;

        this.likeNum = 0;
        this.timeStampKey = null;
    }

    //Postをロードするとき
    public Post(User user, String text, int likeNum, String timeStampKey){
        this.user = user;
        this.text = text;
        this.likeNum = likeNum;
        this.timeStampKey = timeStampKey;
    }

    public User getUser() {
        return this.user;
    }


    public String getText() {
        return this.text;
    }

    public int getLikeNum() {
        return this.likeNum;
    }

    public String getTimeStampKey(){
        return this.timeStampKey;
    }

    public void plusLike(){
        this.likeNum++;
    }

}
