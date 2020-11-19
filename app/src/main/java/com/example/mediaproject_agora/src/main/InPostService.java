package com.example.mediaproject_agora.src.main;

import com.example.mediaproject_agora.src.main.interfaces.InPostActivityView;
import com.example.mediaproject_agora.src.main.interfaces.InPostRetrofitInterface;
import com.example.mediaproject_agora.src.main.models.DefaultResponse;
import com.example.mediaproject_agora.src.main.models.InPostCommentResponse;
import com.example.mediaproject_agora.src.main.models.InPostPostResponse;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.mediaproject_agora.src.ApplicationClass.X_ACCESS_TOKEN;
import static com.example.mediaproject_agora.src.ApplicationClass.getRetrofit;

class InPostService {
    private final InPostActivityView mInPostActivityView;
    private HashMap<String, Object> mParams;

    public InPostService(InPostActivityView mInPostActivityView) {
        this.mInPostActivityView = mInPostActivityView;
    }

    InPostService(final InPostActivityView inPostActivityView, HashMap<String, Object> mParams) {
        this.mInPostActivityView = inPostActivityView;
        this.mParams = mParams;
    }


    void getSpecificDepartmentPost(int department_board_idx) {

        final InPostRetrofitInterface inPostRetrofitInterface = getRetrofit().create(InPostRetrofitInterface.class);
        inPostRetrofitInterface.getSpecificDepartmentPost(X_ACCESS_TOKEN, department_board_idx).enqueue(new Callback<InPostPostResponse>() {
            @Override
            public void onResponse(Call<InPostPostResponse> call, Response<InPostPostResponse> response) {

                final InPostPostResponse inPostPostResponse = response.body();
                if (inPostPostResponse == null) {
                    mInPostActivityView.validateFailure(null);
                    return;
                }
                mInPostActivityView.getDepartmentPostSuccess(inPostPostResponse);
            }

            @Override
            public void onFailure(Call<InPostPostResponse> call, Throwable t) {
                mInPostActivityView.validateFailure(null);
            }
        });
    }

    void getSpecificDepartmentComments(int department_board_idx) {

        final InPostRetrofitInterface inPostRetrofitInterface = getRetrofit().create(InPostRetrofitInterface.class);
        inPostRetrofitInterface.getSpecificDepartmentComment(X_ACCESS_TOKEN, department_board_idx).enqueue(new Callback<InPostCommentResponse>() {
            @Override
            public void onResponse(Call<InPostCommentResponse> call, Response<InPostCommentResponse> response) {

                final InPostCommentResponse inPostCommentResponse = response.body();
                if (inPostCommentResponse == null) {
                    mInPostActivityView.validateFailure(null);
                    return;
                }
                mInPostActivityView.getSpecificDepartmentCommentSuccess(inPostCommentResponse);
            }

            @Override
            public void onFailure(Call<InPostCommentResponse> call, Throwable t) {
                mInPostActivityView.validateFailure(null);
            }
        });
    }

    void postDepartmentComment(int department_board_idx) {

        final InPostRetrofitInterface inPostRetrofitInterface = getRetrofit().create(InPostRetrofitInterface.class);
        inPostRetrofitInterface.postDepartmentComment(X_ACCESS_TOKEN, department_board_idx, mParams).enqueue(new Callback<DefaultResponse>() {
            @Override
            public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {

                final DefaultResponse defaultResponse = response.body();
                if (defaultResponse == null) {
                    mInPostActivityView.validateFailure(null);
                    return;
                }
                mInPostActivityView.postDepartmentCommentSuccess(defaultResponse);
            }

            @Override
            public void onFailure(Call<DefaultResponse> call, Throwable t) {
                mInPostActivityView.validateFailure(null);
            }
        });
    }

    void patchThumbUpDepartmentPost(int department_board_idx) {

        final InPostRetrofitInterface inPostRetrofitInterface = getRetrofit().create(InPostRetrofitInterface.class);
        inPostRetrofitInterface.patchThumbUpDepartmentPost(X_ACCESS_TOKEN, department_board_idx).enqueue(new Callback<DefaultResponse>() {
            @Override
            public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {

                final DefaultResponse defaultResponse = response.body();
                if (defaultResponse == null) {
                    mInPostActivityView.validateFailure(null);
                    return;
                }
                mInPostActivityView.patchThumbUpDepartmentPostSuccess(defaultResponse);
            }

            @Override
            public void onFailure(Call<DefaultResponse> call, Throwable t) {
                mInPostActivityView.validateFailure(null);
            }
        });
    }


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

