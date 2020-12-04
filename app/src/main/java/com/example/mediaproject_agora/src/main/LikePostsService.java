package com.example.mediaproject_agora.src.main;

import com.example.mediaproject_agora.src.main.interfaces.DepartmentBoardActivityView;
import com.example.mediaproject_agora.src.main.interfaces.DepartmentBoardRetrofitInterface;
import com.example.mediaproject_agora.src.main.interfaces.LikePostsActivityView;
import com.example.mediaproject_agora.src.main.interfaces.LikePostsRetrofitInterface;
import com.example.mediaproject_agora.src.main.models.DepartmentBoardResponse;
import com.example.mediaproject_agora.src.main.models.LikePostResponse;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.mediaproject_agora.src.ApplicationClass.X_ACCESS_TOKEN;
import static com.example.mediaproject_agora.src.ApplicationClass.getRetrofit;

class LikePostsService {
    private final LikePostsActivityView mLikePostActivityView;
    private HashMap<String, Object> mParams;

    public LikePostsService(LikePostsActivityView mLikePostActivityView) {
        this.mLikePostActivityView = mLikePostActivityView;
    }

    public LikePostsService(LikePostsActivityView mLikePostActivityView, HashMap<String, Object> mParams) {
        this.mLikePostActivityView = mLikePostActivityView;
        this.mParams = mParams;
    }

    void getLikePosts() {

        final LikePostsRetrofitInterface likePostsRetrofitInterface = getRetrofit().create(LikePostsRetrofitInterface.class);
        likePostsRetrofitInterface.getLikePosts(X_ACCESS_TOKEN).enqueue(new Callback<LikePostResponse>() {
            @Override
            public void onResponse(Call<LikePostResponse> call, Response<LikePostResponse> response) {

                final LikePostResponse likePostResponse = response.body();
                if (likePostResponse == null) {
                    mLikePostActivityView.validateFailure(null);
                    return;
                }
                mLikePostActivityView.getLikePostsSuccess(likePostResponse);
            }

            @Override
            public void onFailure(Call<LikePostResponse> call, Throwable t) {
                mLikePostActivityView.validateFailure(null);
            }
        });
    }

//    void getTest() {
//        final SpecificBoardRetrofitInterface specificBoardRetrofitInterface = getRetrofit().create(SpecificBoardRetrofitInterface.class);
//        specificBoardRetrofitInterface.getTest().enqueue(new Callback<DefaultResponse>() {
//            @Override
//            public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {
//                final DefaultResponse defaultResponse = response.body();
//                if (defaultResponse == null) {
//                    mSpecificBoardActivityView.validateFailure(null);
//                    return;
//                }
//
//                mSpecificBoardActivityView.validateSuccess(defaultResponse.getMessage());
//            }
//
//            @Override
//            public void onFailure(Call<DefaultResponse> call, Throwable t) {
//                mSpecificBoardActivityView.validateFailure(null);
//            }
//        });
//    }
}
