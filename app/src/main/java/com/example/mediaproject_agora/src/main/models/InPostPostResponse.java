package com.example.mediaproject_agora.src.main.models;

import com.example.mediaproject_agora.src.main.fragments.fragment_agora.view_pager_in_frag_agora.frag_agora_department.models.AddFavoriteResponse;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class InPostPostResponse {

    public class InPostPostResult {
        @SerializedName("user_idx")
        private int user_idx;

        @SerializedName("user_picture")
        private String user_picture;

        @SerializedName("department_board_idx")
        private int department_board_idx;

        @SerializedName("title")
        private String title;

        @SerializedName("content")
        private String content;

        @SerializedName("nickname")
        private String nickname;

        @SerializedName("time")
        private String time;

        @SerializedName("photo")
        private String photo;

        @SerializedName("like_num")
        private int like_num;

        @SerializedName("comment_num")
        private int comment_num;

        @SerializedName("like_status")
        private int like_status;

        @SerializedName("is_mine")
        private int is_mine;


        public int getUser_idx() {
            return user_idx;
        }

        public String getUser_picture() {
            return user_picture;
        }

        public int getDepartment_board_idx() {
            return department_board_idx;
        }

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

        public String getPhoto() {
            return photo;
        }

        public int getLike_num() {
            return like_num;
        }

        public int getComment_num() {
            return comment_num;
        }

        public int getLike_status() {
            return like_status;
        }

        public int getIs_mine() {
            return is_mine;
        }
    }

    @SerializedName("result")
    private InPostPostResult inPostPostResult;

    @SerializedName("code")
    private int code;

    @SerializedName("message")
    private String message;

    @SerializedName("isSuccess")
    private boolean isSuccess;

    public InPostPostResult getInPostPostResult() {
        return inPostPostResult;
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