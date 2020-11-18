package com.example.mediaproject_agora.src.main;

import com.example.mediaproject_agora.src.main.interfaces.DepartmentBoardActivityView;
import com.example.mediaproject_agora.src.main.interfaces.DepartmentBoardRetrofitInterface;
import com.example.mediaproject_agora.src.main.interfaces.InPostActivityView;
import com.example.mediaproject_agora.src.main.interfaces.InPostRetrofitInterface;
import com.example.mediaproject_agora.src.main.models.DepartmentBoardResponse;
import com.example.mediaproject_agora.src.main.models.InPostResponse;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.mediaproject_agora.src.ApplicationClass.X_ACCESS_TOKEN;
import static com.example.mediaproject_agora.src.ApplicationClass.getRetrofit;

class InPostService {
    private final InPostActivityView mInPostActivityView;
    private HashMap<String, Object> mParams;

//    SpecificBoardService(final SpecificBoardActivityView specificBoardActivityView) {
//        this.mSpecificBoardActivityView = specificBoardActivityView;
//    }

    InPostService(final InPostActivityView inPostActivityView, HashMap<String, Object> mParams) {
        this.mInPostActivityView = inPostActivityView;
        this.mParams = mParams;
    }


    void getSpecificDepartmentPost(int department_board_idx) {

        final InPostRetrofitInterface inPostRetrofitInterface = getRetrofit().create(InPostRetrofitInterface.class);
        inPostRetrofitInterface.getSpecificDepartmentPost(X_ACCESS_TOKEN, department_board_idx).enqueue(new Callback<InPostResponse>() {
            @Override
            public void onResponse(Call<InPostResponse> call, Response<InPostResponse> response) {

                final InPostResponse inPostResponse = response.body();
                if (inPostResponse == null) {
                    mInPostActivityView.validateFailure(null);
                    return;
                }
                mInPostActivityView.getDepartmentPostSuccess(inPostResponse);
            }

            @Override
            public void onFailure(Call<InPostResponse> call, Throwable t) {
                mInPostActivityView.validateFailure(null);
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
