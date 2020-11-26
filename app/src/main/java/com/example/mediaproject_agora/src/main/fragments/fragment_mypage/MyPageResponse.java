package com.example.mediaproject_agora.src.main.fragments.fragment_mypage;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MyPageResponse {

    public class MyPageResult {
        @SerializedName("user_picture")
        private String user_picture;

        @SerializedName("nickname")
        private String nickname;

        @SerializedName("user_name")
        private String user_name;

        @SerializedName("department_name")
        private String department_name;

        @SerializedName("user_student_id")
        private int user_student_id;


        public String getUser_picture() {
            return user_picture;
        }

        public String getNickname() {
            return nickname;
        }

        public String getUser_name() {
            return user_name;
        }

        public String getDepartment_name() {
            return department_name;
        }

        public int getUser_student_id() {
            return user_student_id;
        }
    }

    @SerializedName("result")
    private MyPageResult myPageResult;

    @SerializedName("code")
    private int code;

    @SerializedName("message")
    private String message;

    @SerializedName("isSuccess")
    private boolean isSuccess;

    public MyPageResult getMyPageResult() {
        return myPageResult;
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