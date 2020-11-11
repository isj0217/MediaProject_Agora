package com.example.mediaproject_agora.src.sign_up.interfaces;

import com.example.mediaproject_agora.src.sign_up.models.SignUpResponse;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface SignUpRetrofitInterface {
    //    @GET("/test")
    @GET("/jwt")
    Call<SignUpResponse> getTest();

    @GET("/test/{number}")
    Call<SignUpResponse> getTestPathAndQuery(
            @Path("number") int number,
            @Query("content") final String content
    );

    @POST("/test")
    Call<SignUpResponse> postTest(@Body RequestBody params);
}
