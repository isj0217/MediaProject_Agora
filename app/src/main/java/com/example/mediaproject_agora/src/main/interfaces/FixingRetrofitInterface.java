package com.example.mediaproject_agora.src.main.interfaces;

import com.example.mediaproject_agora.src.main.fragments.fragment_agora.view_pager_in_frag_agora.frag_agora_department.models.AddFavoriteResponse;
import com.example.mediaproject_agora.src.main.fragments.fragment_agora.view_pager_in_frag_agora.frag_agora_department.models.DepartmentResponse;
import com.example.mediaproject_agora.src.main.models.DefaultResponse;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface FixingRetrofitInterface {
    //    @GET("/test")

    // 학과별 게시판의 게시물 수정
    @PATCH("/department-board-idx/{department_board_idx}/patch")
    Call<DefaultResponse> patchDepartmentPost(@Header("x-access-token") String accessToken,
                                              @Path("department_board_idx") int department_board_idx,
                                              @Body HashMap<String, Object> params);

    // 아주 맛집 추천 게시물 수정
    @PATCH("/tastehouse/{tastehouse_idx}")
    Call<DefaultResponse> patchRestaurantPost(@Header("x-access-token") String accessToken,
                                              @Path("tastehouse_idx") int tastehouse_idx,
                                              @Body HashMap<String, Object> params);



//
//
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
