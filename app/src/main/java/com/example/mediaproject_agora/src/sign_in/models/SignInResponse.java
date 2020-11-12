package com.example.mediaproject_agora.src.sign_in.models;

import com.google.gson.annotations.SerializedName;

public class SignInResponse {

    public class SignInResult {
        @SerializedName("jwt")
        private String jwt;

        @SerializedName("server_id")
        private int server_id;

        @SerializedName("server_name")
        private String server_name;

        public String getJwt() {
            return jwt;
        }

        public int getServer_id() {
            return server_id;
        }

        public String getServer_name() {
            return server_name;
        }
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


    @SerializedName("code")
    private int code;

    @SerializedName("message")
    private String message;

    @SerializedName("isSuccess")
    private boolean isSuccess;

    @SerializedName("result")
    private SignInResult signInResult;


    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public boolean getIsSuccess() {
        return isSuccess;
    }

    public SignInResult getSignInResult() {
        return signInResult;
    }
}