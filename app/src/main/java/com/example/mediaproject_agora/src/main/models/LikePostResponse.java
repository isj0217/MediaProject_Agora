package com.example.mediaproject_agora.src.main.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class LikePostResponse {

    @SerializedName("result")
    private List<LikePostResult> likePostResults;

    @SerializedName("code")
    private int code;

    @SerializedName("message")
    private String message;

    @SerializedName("isSuccess")
    private boolean isSuccess;

    public LikePostResponse(List<LikePostResult> likePostResults, int code, String message, boolean isSuccess) {
        this.likePostResults = likePostResults;
        this.code = code;
        this.message = message;
        this.isSuccess = isSuccess;
    }

    public List<LikePostResult> getLikePostResults() {
        return likePostResults;
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