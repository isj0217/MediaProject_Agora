package com.example.mediaproject_agora.src.main.fragments.fragment_agora.view_pager_in_frag_agora.frag_agora_department.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DepartmentResponse {

    @SerializedName("result")
    private List<DepartmentResult> departmentResults;

    @SerializedName("code")
    private int code;

    @SerializedName("message")
    private String message;

    @SerializedName("isSuccess")
    private boolean isSuccess;

    public DepartmentResponse(List<DepartmentResult> departmentResults, int code, String message, boolean isSuccess) {
        this.departmentResults = departmentResults;
        this.code = code;
        this.message = message;
        this.isSuccess = isSuccess;
    }

    public List<DepartmentResult> getDepartmentResults() {
        return departmentResults;
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

//    public class DepartmentResult {
//
//        @SerializedName("department_name")
//        private String department_name;
//
//        @SerializedName("status")
//        private int status;
//
//        @SerializedName("new")
//        private int is_new;
//
//        public String getDepartment_name() {
//            return department_name;
//        }
//
//        public int getStatus() {
//            return status;
//        }
//
//        public int getIs_new() {
//            return is_new;
//        }
//    }

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