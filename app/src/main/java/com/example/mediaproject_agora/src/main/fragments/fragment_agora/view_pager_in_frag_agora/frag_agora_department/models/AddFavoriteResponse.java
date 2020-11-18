package com.example.mediaproject_agora.src.main.fragments.fragment_agora.view_pager_in_frag_agora.frag_agora_department.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AddFavoriteResponse {

    public class AddFavoriteResult {
        @SerializedName("department_name")
        private String department_name;

        @SerializedName("status")
        private int status;

        public String getDepartment_name() {
            return department_name;
        }

        public int getStatus() {
            return status;
        }
    }

    @SerializedName("result")
    private AddFavoriteResult addFavoriteResult;

    @SerializedName("code")
    private int code;

    @SerializedName("message")
    private String message;

    @SerializedName("isSuccess")
    private boolean isSuccess;

    public AddFavoriteResult getAddFavoriteResult() {
        return addFavoriteResult;
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