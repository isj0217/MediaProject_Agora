package com.example.mediaproject_agora.src.main.interfaces;

import com.example.mediaproject_agora.src.main.models.DepartmentBoardResponse;
import com.example.mediaproject_agora.src.main.models.LikePostResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;

public interface LikePostsRetrofitInterface {

    @GET("/mypage/like")
    Call<LikePostResponse> getLikePosts(@Header("x-access-token") String accessToken);

}
