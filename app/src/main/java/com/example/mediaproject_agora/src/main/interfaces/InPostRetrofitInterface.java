package com.example.mediaproject_agora.src.main.interfaces;

import com.example.mediaproject_agora.src.main.models.InPostPostResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;

public interface InPostRetrofitInterface {
    //    @GET("/test")

    @GET("/department-board-idx/{department_board_idx}")
    Call<InPostPostResponse> getSpecificDepartmentPost(@Header("x-access-token") String accessToken,
                                                       @Path("department_board_idx") int department_board_idx);

//    @POST("/department")
//    Call<DefaultResponse> postDepartmentPost(@Header("x-access-token") String accessToken,
//                                             @Body HashMap<String, Object> params);
//
//    @GET("/department")
//    Call<DepartmentResponse> getDepartmentList(@Header("x-access-token") String accessToken);
//
//    @PATCH("/department/{department_name}/like")
//    Call<AddFavoriteResponse>patchFavoriteDepartment(@Header("x-access-token") String accessToken,
//                                                     @Path("department_name") String department_name);



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
