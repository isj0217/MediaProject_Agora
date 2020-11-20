package com.example.mediaproject_agora.src.main.fragments.fragment_message;

public class MessageRoomResult {

    int message_room_idx;
    int user_idx;
    String user_nickname;
    int message_idx;
    String content;
    String time;

    public MessageRoomResult(int message_room_idx, int user_idx, String user_nickname, int message_idx, String content, String time) {
        this.message_room_idx = message_room_idx;
        this.user_idx = user_idx;
        this.user_nickname = user_nickname;
        this.message_idx = message_idx;
        this.content = content;
        this.time = time;
    }

    public int getMessage_room_idx() {
        return message_room_idx;
    }

    public int getUser_idx() {
        return user_idx;
    }

    public String getUser_nickname() {
        return user_nickname;
    }

    public int getMessage_idx() {
        return message_idx;
    }

    public String getContent() {
        return content;
    }

    public String getTime() {
        return time;
    }
}
