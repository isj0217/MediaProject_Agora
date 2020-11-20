package com.example.mediaproject_agora.src.main.fragments.fragment_message;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MessageListResponse {

    @SerializedName("result")
    private List<MessageResult> messageResults;

    @SerializedName("code")
    private int code;

    @SerializedName("message")
    private String message;

    @SerializedName("isSuccess")
    private boolean isSuccess;


    public List<MessageResult> getMessageResults() {
        return messageResults;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public boolean isSuccess() {
        return isSuccess;
    }
}