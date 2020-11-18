package com.example.mediaproject_agora.src.main.models;

import com.example.mediaproject_agora.src.main.fragments.fragment_agora.view_pager_in_frag_agora.frag_agora_department.models.AddFavoriteResponse;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class InPostResponse {

//    @SerializedName("result")
//    private InPostPostResult inPostPostResult;


    @SerializedName("code")
    private int code;

    @SerializedName("message")
    private String message;

    @SerializedName("isSuccess")
    private boolean isSuccess;



}