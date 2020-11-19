package com.example.mediaproject_agora.src.main.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FavoriteDepartmentResponse {

    @SerializedName("result")
    private List<FavoriteDepartmentResult> favoriteDepartmentResults;

    @SerializedName("code")
    private int code;

    @SerializedName("message")
    private String message;

    @SerializedName("isSuccess")
    private boolean isSuccess;

    public List<FavoriteDepartmentResult> getFavoriteDepartmentResults() {
        return favoriteDepartmentResults;
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