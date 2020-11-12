package com.example.mediaproject_agora.src.sign_in.interfaces;

import com.example.mediaproject_agora.src.sign_in.models.SignInResponse;

import java.util.HashMap;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface SignInRetrofitInterface {

    @POST("/login")
    Call<SignInResponse> signInTest(@Body HashMap<String, Object> params);


    //    @GET("/test")
//    @GET("/jwt")
//    Call<SignInResponse> getTest();
//
//    @GET("/test/{number}")
//    Call<SignInResponse> getTestPathAndQuery(
//            @Path("number") int number,
//            @Query("content") final String content
//    );
//
//    @POST("/test")
//    Call<SignInResponse> postTest(@Body RequestBody params);







}
