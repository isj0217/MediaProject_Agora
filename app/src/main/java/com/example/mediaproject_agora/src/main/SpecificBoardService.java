package com.example.mediaproject_agora.src.main;

import com.example.mediaproject_agora.src.main.interfaces.MainActivityView;
import com.example.mediaproject_agora.src.main.interfaces.MainRetrofitInterface;
import com.example.mediaproject_agora.src.main.interfaces.SpecificBoardActivityView;
import com.example.mediaproject_agora.src.main.interfaces.SpecificBoardRetrofitInterface;
import com.example.mediaproject_agora.src.main.models.DefaultResponse;
import com.example.mediaproject_agora.src.main.models.MainResponse;
import com.example.mediaproject_agora.src.main.models.SpecificBoardResponse;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.mediaproject_agora.src.ApplicationClass.X_ACCESS_TOKEN;
import static com.example.mediaproject_agora.src.ApplicationClass.getRetrofit;

class SpecificBoardService {
    private final SpecificBoardActivityView mSpecificBoardActivityView;
    private HashMap<String, Object> mParams;

//    SpecificBoardService(final SpecificBoardActivityView specificBoardActivityView) {
//        this.mSpecificBoardActivityView = specificBoardActivityView;
//    }

    SpecificBoardService(final SpecificBoardActivityView specificBoardActivityView, HashMap<String, Object> mParams) {
        this.mSpecificBoardActivityView = specificBoardActivityView;
        this.mParams = mParams;
    }


    void getSpecificDepartmentBoard(String department_name) {
        final SpecificBoardRetrofitInterface specificBoardRetrofitInterface = getRetrofit().create(SpecificBoardRetrofitInterface.class);
        specificBoardRetrofitInterface.getSpecificDepartmentBoardTest(X_ACCESS_TOKEN, department_name).enqueue(new Callback<SpecificBoardResponse>() {
            @Override
            public void onResponse(Call<SpecificBoardResponse> call, Response<SpecificBoardResponse> response) {

                final SpecificBoardResponse specificBoardResponse = response.body();
                if (specificBoardResponse == null) {
                    mSpecificBoardActivityView.validateFailure(null);
                    return;
                }
                mSpecificBoardActivityView.getSpecificDepartmentBoardSuccess(specificBoardResponse);

//                mSignInActivityView.validateSuccess(signInResponse.getMessage());
            }

            @Override
            public void onFailure(Call<SpecificBoardResponse> call, Throwable t) {
                mSpecificBoardActivityView.validateFailure(null);
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
