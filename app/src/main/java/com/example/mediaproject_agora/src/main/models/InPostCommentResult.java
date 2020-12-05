package com.example.mediaproject_agora.src.main.models;

public class InPostCommentResult {

    int is_mine;

    int department_comment_idx;
    String nickname;
    String time;
    String comment;

    public InPostCommentResult(int is_mine, int department_comment_idx, String nickname, String time, String comment) {
        this.is_mine = is_mine;
        this.department_comment_idx = department_comment_idx;
        this.nickname = nickname;
        this.time = time;
        this.comment = comment;
    }

    public int getIs_mine() {
        return is_mine;
    }

    public int getDepartment_comment_idx() {
        return department_comment_idx;
    }

    public String getNickname() {
        return nickname;
    }

    public String getTime() {
        return time;
    }

    public String getComment() {
        return comment;
    }
}
