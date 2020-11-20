package com.example.mediaproject_agora.src.main.items;

public class MessageRoomItem {

    int message_room_idx;
    int user_idx;
    String user_nickname;
    int message_idx;
    String content;
    String time;


    public int getMessage_room_idx() {
        return message_room_idx;
    }

    public void setMessage_room_idx(int message_room_idx) {
        this.message_room_idx = message_room_idx;
    }

    public int getUser_idx() {
        return user_idx;
    }

    public void setUser_idx(int user_idx) {
        this.user_idx = user_idx;
    }

    public String getUser_nickname() {
        return user_nickname;
    }

    public void setUser_nickname(String user_nickname) {
        this.user_nickname = user_nickname;
    }

    public int getMessage_idx() {
        return message_idx;
    }

    public void setMessage_idx(int message_idx) {
        this.message_idx = message_idx;
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
}
