package com.example.mediaproject_agora.src.main.interfaces;

import com.example.mediaproject_agora.src.main.fragments.fragment_restaurant.RestaurantResponse;
import com.example.mediaproject_agora.src.main.models.DefaultResponse;
import com.example.mediaproject_agora.src.main.models.InPostCommentResponse;
import com.example.mediaproject_agora.src.main.models.InPostPostResponse;
import com.example.mediaproject_agora.src.main.models.InRestaurantPostCommentResponse;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface InRestaurantPostRetrofitInterface {
    //    @GET("/test")


    // 아주 맛집에서 특정 게시물 조회
    @GET("/tastehouse/{tastehouse_idx}")
    Call<RestaurantResponse> getSpecificRestaurantPost(@Header("x-access-token") String accessToken,
                                                       @Path("tastehouse_idx") int tastehouse_idx);

    // 아주 맛집에서 특정 게시물의 댓글 조회
    @GET("/tastehouse/{tastehouse_idx}/comment")
    Call<InRestaurantPostCommentResponse> getSpecificRestaurantComment(@Header("x-access-token") String accessToken,
                                                                       @Path("tastehouse_idx") int tastehouse_idx);

    // 아주 맛집에서 특정 게시물에 댓글 달기
    @POST("/tastehouse/{tastehouse_idx}/comment")
    Call<DefaultResponse> postRestaurantComment(@Header("x-access-token") String accessToken,
                                                @Path("tastehouse_idx") int tastehouse_idx,
                                                @Body HashMap<String, Object> params);




    // 학과별 게시판에서 특정 게시물 조회
    @GET("/department-board-idx/{department_board_idx}")
    Call<InPostPostResponse> getSpecificDepartmentPost(@Header("x-access-token") String accessToken,
                                                       @Path("department_board_idx") int department_board_idx);

    // 학과별 게시판에서 특정 게시물의 댓글들 조회
    @GET("/department-board-idx/{department_board_idx}/comment")
    Call<InPostCommentResponse> getSpecificDepartmentComment(@Header("x-access-token") String accessToken,
                                                             @Path("department_board_idx") int department_board_idx);

    // 학과별 게시판에서 댓글 달기
    @POST("/department-board-idx/{department_board_idx}/comment")
    Call<DefaultResponse> postDepartmentComment(@Header("x-access-token") String accessToken,
                                                @Path("department_board_idx") int department_board_idx,
                                                @Body HashMap<String, Object> params);

    // 학과별 게시판에서 특정 게시물 좋아요 혹은 좋아요 취소
    @PATCH("/department-board-idx/{department_board_idx}/like")
    Call<DefaultResponse> patchThumbUpDepartmentPost(@Header("x-access-token") String accessToken,
                                                     @Path("department_board_idx") int department_board_idx);

    // 학과별 게시판에서 특정 게시물 삭제
    @DELETE("/department-board-idx/{department_board_idx}/delete")
    Call<DefaultResponse> deleteDepartmentPost(@Header("x-access-token") String accessToken,
                                               @Path("department_board_idx") int department_board_idx);

    // 학과별 게시판에서 특정 댓글 삭제
    @DELETE("/department-comment-idx/{department_comment_idx}/delete")
    Call<DefaultResponse> deleteDepartmentComment(@Header("x-access-token") String accessToken,
                                                  @Path("department_comment_idx") int department_comment_idx);




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
