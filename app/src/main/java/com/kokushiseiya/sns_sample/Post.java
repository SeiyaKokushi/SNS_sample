package com.kokushiseiya.sns_sample;

/**
 * Created by kokushiseiya on 16/04/18.
 */
public class Post {

    private User user;
    private String text; // リストに表示させる文章
    private int likeNum; // Like(ファボ)数

    //空のコンストラクタ
    public Post(){ }

    //Postを作るとき
    public Post(User user, String text){
        this.user = user;
        this.text = text;

        this.likeNum = 0;
    }

    //Postをロードするとき
    public Post(User user, String text, int likeNum){
        this.user = user;
        this.text = text;
        this.likeNum = likeNum;
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

    //like数を変化させるメソッド
    public void plusLike(){
        this.likeNum++;
    }

}
