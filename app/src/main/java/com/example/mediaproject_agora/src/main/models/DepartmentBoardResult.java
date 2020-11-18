package com.example.mediaproject_agora.src.main.models;

public class DepartmentBoardResult {

    int department_board_idx;
    String title;
    String content;
    String nickname;
    String time;
    int photo_status;
    int like_num;
    int comment_num;

    public DepartmentBoardResult(int department_board_idx, String title, String content, String nickname, String time, int photo_status, int like_num, int comment_num) {
        this.department_board_idx = department_board_idx;
        this.title = title;
        this.content = content;
        this.nickname = nickname;
        this.time = time;
        this.photo_status = photo_status;
        this.like_num = like_num;
        this.comment_num = comment_num;
    }

    public int getDepartment_board_idx() {
        return department_board_idx;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getNickname() {
        return nickname;
    }

    public String getTime() {
        return time;
    }

    public int getPhoto_status() {
        return photo_status;
    }

    public int getLike_num() {
        return like_num;
    }

    public int getComment_num() {
        return comment_num;
    }
}
