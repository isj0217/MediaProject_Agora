package com.example.mediaproject_agora.src.main.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class InPostCommentResponse {

    @SerializedName("result")
    private List<InPostCommentResult> inPostCommentResults;

    @SerializedName("code")
    private int code;

    @SerializedName("message")
    private String message;

    @SerializedName("isSuccess")
    private boolean isSuccess;

    public InPostCommentResponse(List<InPostCommentResult> inPostCommentResults, int code, String message, boolean isSuccess) {
        this.inPostCommentResults = inPostCommentResults;
        this.code = code;
        this.message = message;
        this.isSuccess = isSuccess;
    }

    public List<InPostCommentResult> getInPostCommentResults() {
        return inPostCommentResults;
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