package com.example.mediaproject_agora.src.main;

import com.example.mediaproject_agora.src.main.interfaces.FixingDepartmentActivityView;
import com.example.mediaproject_agora.src.main.interfaces.FixingRetrofitInterface;
import com.example.mediaproject_agora.src.main.interfaces.WritingDepartmentActivityView;
import com.example.mediaproject_agora.src.main.interfaces.WritingRetrofitInterface;
import com.example.mediaproject_agora.src.main.models.DefaultResponse;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.mediaproject_agora.src.ApplicationClass.X_ACCESS_TOKEN;
import static com.example.mediaproject_agora.src.ApplicationClass.getRetrofit;

class FixingDepartmentService {
    private final FixingDepartmentActivityView mFixingDepartmentActivityView;
    private HashMap<String, Object> mParams;

//    SignInService(final SignInActivityView signInActivityView) {
//        this.mSignInActivityView = signInActivityView;
//    }

    FixingDepartmentService(final FixingDepartmentActivityView fixingDepartmentActivityView, HashMap<String, Object> mParams) {
        this.mFixingDepartmentActivityView = fixingDepartmentActivityView;
        this.mParams = mParams;
    }

    void patchDepartmentPost(int idx_of_post_we_are_fixing) {
        final FixingRetrofitInterface fixingRetrofitInterface = getRetrofit().create(FixingRetrofitInterface.class);
        fixingRetrofitInterface.patchDepartmentPost(X_ACCESS_TOKEN, idx_of_post_we_are_fixing, mParams).enqueue(new Callback<DefaultResponse>() {
            @Override
            public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {

                final DefaultResponse defaultResponse = response.body();
                if (defaultResponse == null) {
                    mFixingDepartmentActivityView.validateFailure(null);
                    return;
                }
                mFixingDepartmentActivityView.patchDepartmentPostSuccess(defaultResponse);
            }

            @Override
            public void onFailure(Call<DefaultResponse> call, Throwable t) {
                mFixingDepartmentActivityView.validateFailure(null);
            }
        });
    }
}
