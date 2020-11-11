package com.example.mediaproject_agora.src.main.items;

public class PostItem {

    private int content_index;

    private String title;
    private String content;
    private String time;
    private String writer;
    private int like_num;
    private int comment_num;

    public int getContent_index() {
        return content_index;
    }

    public void setContent_index(int content_index) {
        this.content_index = content_index;
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

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
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
