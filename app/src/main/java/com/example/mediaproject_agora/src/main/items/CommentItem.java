package com.example.mediaproject_agora.src.main.items;

public class CommentItem {

    private int department_comment_idx;
    private String nickname;
    private String time;
    private String comment;

    public int getDepartment_comment_idx() {
        return department_comment_idx;
    }

    public void setDepartment_comment_idx(int department_comment_idx) {
        this.department_comment_idx = department_comment_idx;
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

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
