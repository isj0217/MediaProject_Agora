package com.example.mediaproject_agora.src.main;

import com.example.mediaproject_agora.src.main.interfaces.MainActivityView;
import com.example.mediaproject_agora.src.main.interfaces.MainRetrofitInterface;
import com.example.mediaproject_agora.src.main.models.DefaultResponse;
import com.example.mediaproject_agora.src.main.models.MainResponse;
import com.example.mediaproject_agora.src.sign_in.interfaces.SignInActivityView;
import com.example.mediaproject_agora.src.sign_in.interfaces.SignInRetrofitInterface;
import com.example.mediaproject_agora.src.sign_in.models.SignInResponse;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.mediaproject_agora.src.ApplicationClass.getRetrofit;

class MainService {
    private final MainActivityView mMainActivityView;
    private HashMap<String, Object> mParams;

    MainService(final MainActivityView mainActivityView) {
        this.mMainActivityView = mainActivityView;
    }

    MainService(final MainActivityView mainActivityView, HashMap<String, Object> mParams) {
        this.mMainActivityView = mainActivityView;
        this.mParams = mParams;
    }





    void getTest() {
        final MainRetrofitInterface mainRetrofitInterface = getRetrofit().create(MainRetrofitInterface.class);
        mainRetrofitInterface.getTest().enqueue(new Callback<DefaultResponse>() {
            @Override
            public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {
                final DefaultResponse defaultResponse = response.body();
                if (defaultResponse == null) {
                    mMainActivityView.validateFailure(null);
                    return;
                }

                mMainActivityView.validateSuccess(defaultResponse.getMessage());
            }

            @Override
            public void onFailure(Call<DefaultResponse> call, Throwable t) {
                mMainActivityView.validateFailure(null);
            }
        });
    }
}
