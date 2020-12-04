package com.example.mediaproject_agora.src.main.items;

public class LikePostItem {

    private int department_board_idx;

    private String type;

    private String title;
    private String content;
    private String nickname;
    private String time;
    private int photo_status;
    private int like_num;
    private int comment_num;


    public int getDepartment_board_idx() {
        return department_board_idx;
    }

    public void setDepartment_board_idx(int department_board_idx) {
        this.department_board_idx = department_board_idx;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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

    public int getPhoto_status() {
        return photo_status;
    }

    public void setPhoto_status(int photo_status) {
        this.photo_status = photo_status;
    }

    public int getLike_num() {
        return like_num;
    }

    public void setLike_num(int like_num) {
        this.like_num = like_num;
    }

    public int getComment_num() {
        return comment_num;
    }

    public void setComment_num(int comment_num) {
        this.comment_num = comment_num;
    }
}
