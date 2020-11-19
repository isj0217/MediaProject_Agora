package com.example.mediaproject_agora.src.main.models;

public class InPostCommentResult {

    int department_comment_idx;
    String nickname;
    String time;
    String comment;

    public InPostCommentResult(int department_board_idx, String nickname, String time, String comment) {
        this.department_comment_idx = department_board_idx;
        this.nickname = nickname;
        this.time = time;
        this.comment = comment;
    }

    public int getDepartment_board_idx() {
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
