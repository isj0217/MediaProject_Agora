package com.example.mediaproject_agora.src.main;

import com.example.mediaproject_agora.src.main.interfaces.WritingDepartmentActivityView;
import com.example.mediaproject_agora.src.main.interfaces.WritingRetrofitInterface;
import com.example.mediaproject_agora.src.main.models.DefaultResponse;


import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.mediaproject_agora.src.ApplicationClass.X_ACCESS_TOKEN;
import static com.example.mediaproject_agora.src.ApplicationClass.getRetrofit;

class WritingDepartmentService {
    private final WritingDepartmentActivityView mWritingDepartmentActivityView;
    private HashMap<String, Object> mParams;

//    SignInService(final SignInActivityView signInActivityView) {
//        this.mSignInActivityView = signInActivityView;
//    }

    WritingDepartmentService(final WritingDepartmentActivityView writingDepartmentActivityView, HashMap<String, Object> mParams) {
        this.mWritingDepartmentActivityView = writingDepartmentActivityView;
        this.mParams = mParams;
    }

    void postDepartmentPost() {
        final WritingRetrofitInterface writingRetrofitInterface = getRetrofit().create(WritingRetrofitInterface.class);
        writingRetrofitInterface.postDepartmentPost(X_ACCESS_TOKEN, mParams).enqueue(new Callback<DefaultResponse>() {
            @Override
            public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {

                final DefaultResponse defaultResponse = response.body();
                if (defaultResponse == null) {
                    mWritingDepartmentActivityView.validateFailure(null);
                    return;
                }
                mWritingDepartmentActivityView.postDepartmentPostSuccess(defaultResponse);

            }

            @Override
            public void onFailure(Call<DefaultResponse> call, Throwable t) {
                mWritingDepartmentActivityView.validateFailure(null);
            }
        });
    }
}
