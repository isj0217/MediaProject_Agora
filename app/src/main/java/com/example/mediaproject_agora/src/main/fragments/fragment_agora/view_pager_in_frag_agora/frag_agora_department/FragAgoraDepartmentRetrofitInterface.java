package com.example.mediaproject_agora.src.main.fragments.fragment_agora.view_pager_in_frag_agora.frag_agora_department;

import com.example.mediaproject_agora.src.main.fragments.fragment_agora.view_pager_in_frag_agora.frag_agora_department.models.AddFavoriteResponse;
import com.example.mediaproject_agora.src.main.fragments.fragment_agora.view_pager_in_frag_agora.frag_agora_department.models.DepartmentResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.PATCH;
import retrofit2.http.Path;

public interface FragAgoraDepartmentRetrofitInterface {
    //    @GET("/test")

    @GET("/department")
    Call<DepartmentResponse> getDepartmentList(@Header("x-access-token")String accessToken);

    @PATCH("/department/{department_name}/like")
    Call<AddFavoriteResponse>patchFavoriteDepartment(@Header("x-access-token") String accessToken,
                                                     @Path("department_name") String department_name);

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
