package com.example.mediaproject_agora.src.sign_up.interfaces;

import com.example.mediaproject_agora.src.sign_up.models.SignUpResponse;

import java.util.HashMap;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;


public interface SignUpRetrofitInterface {

    @POST("/user")
    Call<SignUpResponse> signUpTest(@Body HashMap<String, Object> params);

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
