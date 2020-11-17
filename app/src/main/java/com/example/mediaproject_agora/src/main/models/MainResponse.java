package com.example.mediaproject_agora.src.main.models;

import com.google.gson.annotations.SerializedName;

public class MainResponse {

    public class MainResult {

        @SerializedName("title")
        private String title;

        @SerializedName("content")
        private String content;

        @SerializedName("nickname")
        private String nickname;

        @SerializedName("time")
        private String time;

        @SerializedName("photo_status")
        private String photo_status;

        @SerializedName("like_num")
        private String like_num;

        @SerializedName("comment_num")
        private String comment_num;

        public String getTitle() {
            return title;
        }

        public String getContent() {
            return content;
        }

        public String getNickname() {
            return nickname;
        }

        public String getTime() {
            return time;
        }

        public String getPhoto_status() {
            return photo_status;
        }

        public String getLike_num() {
            return like_num;
        }

        public String getComment_num() {
            return comment_num;
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
    private MainResult MainResult;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public MainResponse.MainResult getMainResult() {
        return MainResult;
    }
}