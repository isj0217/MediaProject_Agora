package com.example.mediaproject_agora.src.sign_in;

import com.example.mediaproject_agora.src.sign_in.interfaces.SignInActivityView;
import com.example.mediaproject_agora.src.sign_in.interfaces.SignInRetrofitInterface;
import com.example.mediaproject_agora.src.main.models.DefaultResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.mediaproject_agora.src.ApplicationClass.getRetrofit;

class SignInService {
    private final SignInActivityView mSignInActivityView;

    SignInService(final SignInActivityView signInActivityView) {
        this.mSignInActivityView = signInActivityView;
    }

    void getTest() {
        final SignInRetrofitInterface signInRetrofitInterface = getRetrofit().create(SignInRetrofitInterface.class);
        signInRetrofitInterface.getTest().enqueue(new Callback<DefaultResponse>() {
            @Override
            public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {
                final DefaultResponse defaultResponse = response.body();
                if (defaultResponse == null) {
                    mSignInActivityView.validateFailure(null);
                    return;
                }

                mSignInActivityView.validateSuccess(defaultResponse.getMessage());
            }

            @Override
            public void onFailure(Call<DefaultResponse> call, Throwable t) {
                mSignInActivityView.validateFailure(null);
            }
        });
    }
}
