package com.example.mediaproject_agora.src.main.fragments.fragment_restaurant;

import com.example.mediaproject_agora.src.main.fragments.fragment_message.MessageResult;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RestaurantListResponse {

    @SerializedName("result")
    private List<RestaurantResult> restaurantResults;

    @SerializedName("code")
    private int code;

    @SerializedName("message")
    private String message;

    @SerializedName("isSuccess")
    private boolean isSuccess;


    public List<RestaurantResult> getRestaurantResults() {
        return restaurantResults;
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