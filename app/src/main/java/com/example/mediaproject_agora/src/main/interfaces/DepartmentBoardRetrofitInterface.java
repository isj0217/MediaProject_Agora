package com.example.mediaproject_agora.src.main.interfaces;

import com.example.mediaproject_agora.src.main.models.DefaultResponse;
import com.example.mediaproject_agora.src.main.models.DepartmentBoardResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface DepartmentBoardRetrofitInterface {
    //    @GET("/test")

    @GET("/department/{department_name}")
    Call<DepartmentBoardResponse> getSpecificDepartmentBoardTest(@Header("x-access-token") String accessToken,
                                                                 @Path("department_name") String department_name);

    @GET("/department_board")
    Call<DepartmentBoardResponse> getFilteredDepartmentBoard(@Header("x-access-token") String accessToken,
                                                             @Query("department_name") String department_name,
                                                             @Query("title") String title);

//    @GET("/jwt")
//    Call<DefaultResponse> getTest();
//
//    @GET("/test/{number}")
//    Call<DefaultResponse> getTestPathAndQuery(
//            @Path("number") int number,
//            @Query("content") final String content
//    );
//
//    @POST("/test")
//    Call<DefaultResponse> postTest(@Body RequestBody params);
}
