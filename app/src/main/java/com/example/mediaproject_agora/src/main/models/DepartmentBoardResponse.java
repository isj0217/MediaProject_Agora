package com.example.mediaproject_agora.src.main.models;

import com.example.mediaproject_agora.src.main.fragments.fragment_agora.view_pager_in_frag_agora.frag_agora_department.models.DepartmentResult;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DepartmentBoardResponse {

    @SerializedName("result")
    private List<DepartmentBoardResult> departmentBoardResults;

    @SerializedName("code")
    private int code;

    @SerializedName("message")
    private String message;

    @SerializedName("isSuccess")
    private boolean isSuccess;

    public DepartmentBoardResponse(List<DepartmentBoardResult> departmentBoardResults, int code, String message, boolean isSuccess) {
        this.departmentBoardResults = departmentBoardResults;
        this.code = code;
        this.message = message;
        this.isSuccess = isSuccess;
    }

    public List<DepartmentBoardResult> getDepartmentBoardResults() {
        return departmentBoardResults;
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

    //    public class SignInInf {
//        @SerializedName("유저ID")
//        private String userID;
//
//        @SerializedName("닉네임")
//        private String soft;
//
//        @SerializedName("대학교")
//        private String univName;
//    }

}