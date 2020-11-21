package com.example.mediaproject_agora.src.main.interfaces;

import com.example.mediaproject_agora.src.main.fragments.fragment_message.MessageListResponse;
import com.example.mediaproject_agora.src.main.models.DefaultResponse;
import com.example.mediaproject_agora.src.main.models.InPostCommentResponse;
import com.example.mediaproject_agora.src.main.models.InPostPostResponse;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface InMessageRoomRetrofitInterface {
    //    @GET("/test")

    // 쪽지 방 안에서 과거에 오고간 메시지들 조회
    @GET("/message-room-idx/{message_room_idx}")
    Call<MessageListResponse> getSpecificMessageRoom(@Header("x-access-token") String accessToken,
                                                     @Path("message_room_idx") int message_room_idx);

    // 특정 쪽지방 삭제
    @DELETE("/message-room-idx/{message_room_idx}/delete")
    Call<DefaultResponse> deleteMessageRoom(@Header("x-access-token") String accessToken,
                                            @Path("message_room_idx") int message_room_idx);

    // 쪽지 보내기
    @POST("/message")
    Call<DefaultResponse> sendMessage(@Header("x-access-token") String accessToken,
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
