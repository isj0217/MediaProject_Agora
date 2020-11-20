package com.example.mediaproject_agora.src.main.fragments.fragment_message;

public class MessageResult {

    String status;
    String content;
    String time;

    public MessageResult(String status, String content, String time) {
        this.status = status;
        this.content = content;
        this.time = time;
    }

    public String getStatus() {
        return status;
    }

    public String getContent() {
        return content;
    }

    public String getTime() {
        return time;
    }
}
