package com.example.mediaproject_agora.src.main.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class InRestaurantPostCommentResponse {

    @SerializedName("result")
    private List<InRestaurantPostCommentResult> inRestaurantPostCommentResults;

    @SerializedName("code")
    private int code;

    @SerializedName("message")
    private String message;

    @SerializedName("isSuccess")
    private boolean isSuccess;

    public InRestaurantPostCommentResponse(List<InRestaurantPostCommentResult> inRestaurantPostCommentResults, int code, String message, boolean isSuccess) {
        this.inRestaurantPostCommentResults = inRestaurantPostCommentResults;
        this.code = code;
        this.message = message;
        this.isSuccess = isSuccess;
    }

    public List<InRestaurantPostCommentResult> getInRestaurantPostCommentResults() {
        return inRestaurantPostCommentResults;
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