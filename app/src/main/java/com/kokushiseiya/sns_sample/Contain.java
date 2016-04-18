package com.kokushiseiya.sns_sample;

/**
 * Created by kokushiseiya on 16/04/18.
 */
public class Contain {

    private String userName = ""; // リストに表示させるuser name
    private String userId = ""; // リストに表示させるuser id
    private String time = ""; // リストに表示させる時間
    private String text = ""; // リストに表示させる文章
    private int replyNum = 0; // Replyの数
    private int shareNum = 0; // Shareの数
    private int likeNum = 0; // Like(ファボ)数

    /**
     * userNameのセッター
     * @param userName リストに表示させるuser name
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * userNameのゲッター
     * @return リストに表示させるuser name
     */
    public String getUserName() {
        return this.userName;
    }

    /**
     * userIdのセッター
     * @param userId リストに表示させるuser id
     */
    public void setUserId(String userId) {
        this.userId = "@" + userId;
    }

    /**
     * userIdのゲッター
     * @return リストに表示させるuser id
     */
    public String getUserId() {
        return this.userId;
    }

    /**
     * timeのセッター
     * @param time
     */
    public void setTime(String time) {
        this.time = time;
    }

    /**
     * timeのゲッター
     * @return リストに表示させる時間
     */
    public String getTime() {
        return this.time;
    }

    /**
     * textのセッター
     * @param text リストに表示させる文章
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * textのゲッター
     * @return リストに表示させる文章
     */
    public String getText() {
        return this.text;
    }

    /**
     * replyNumのゲッター
     * @return reply数
     */
    public int getReplyNum() {
        return this.replyNum;
    }

    /**
     * shareNumのゲッター
     * @return share数
     */
    public int getShareNum() {
        return this.shareNum;
    }

    /**
     * likeNumのゲッター
     * @return Like(ファボ)数
     */
    public int getLikeNum() {
        return this.likeNum;
    }
}
