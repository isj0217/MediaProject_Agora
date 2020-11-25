package com.example.mediaproject_agora.src.main.models;

public class InRestaurantPostCommentResult {

    int comment_idx;
    String nickname;
    String time;
    String comment_content;

    public InRestaurantPostCommentResult(int comment_idx, String nickname, String time, String comment_content) {
        this.comment_idx = comment_idx;
        this.nickname = nickname;
        this.time = time;
        this.comment_content = comment_content;
    }

    public int getComment_idx() {
        return comment_idx;
    }

    public String getNickname() {
        return nickname;
    }

    public String getTime() {
        return time;
    }

    public String getComment_content() {
        return comment_content;
    }
}
