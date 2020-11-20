package com.example.mediaproject_agora.src.main.fragments.fragment_message;

import com.example.mediaproject_agora.src.main.fragments.fragment_agora.view_pager_in_frag_agora.frag_agora_department.models.DepartmentResult;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MessageRoomResponse {

    @SerializedName("result")
    private List<MessageRoomResult> messageRoomResults;

    @SerializedName("code")
    private int code;

    @SerializedName("message")
    private String message;

    @SerializedName("isSuccess")
    private boolean isSuccess;


    public List<MessageRoomResult> getMessageRoomResults() {
        return messageRoomResults;
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