package com.example.mediaproject_agora.src.main.items;

public class RestaurantCommentItem {

    private int is_mine;

    private int comment_idx;
    private String nickname;
    private String time;
    private String comment_content;

    public int getIs_mine() {
        return is_mine;
    }

    public void setIs_mine(int is_mine) {
        this.is_mine = is_mine;
    }

    public int getComment_idx() {
        return comment_idx;
    }

    public void setComment_idx(int comment_idx) {
        this.comment_idx = comment_idx;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getComment_content() {
        return comment_content;
    }

    public void setComment_content(String comment_content) {
        this.comment_content = comment_content;
    }
}
