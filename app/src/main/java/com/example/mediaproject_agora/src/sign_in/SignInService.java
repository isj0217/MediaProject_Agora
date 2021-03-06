package com.example.mediaproject_agora.src.sign_in;

import com.example.mediaproject_agora.src.sign_in.interfaces.SignInActivityView;
import com.example.mediaproject_agora.src.sign_in.interfaces.SignInRetrofitInterface;
import com.example.mediaproject_agora.src.sign_in.models.SignInResponse;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.mediaproject_agora.src.ApplicationClass.getRetrofit;

class SignInService {
    private final SignInActivityView mSignInActivityView;
    private HashMap<String, Object> mParams;

//    SignInService(final SignInActivityView signInActivityView) {
//        this.mSignInActivityView = signInActivityView;
//    }

    SignInService(final SignInActivityView signInActivityView, HashMap<String, Object> mParams) {
        this.mSignInActivityView = signInActivityView;
        this.mParams = mParams;
    }

    void postSignIn() {
        final SignInRetrofitInterface signInRetrofitInterface = getRetrofit().create(SignInRetrofitInterface.class);
        signInRetrofitInterface.signInTest(mParams).enqueue(new Callback<SignInResponse>() {
            @Override
            public void onResponse(Call<SignInResponse> call, Response<SignInResponse> response) {

                final SignInResponse signInResponse = response.body();
                if (signInResponse == null) {
                    mSignInActivityView.validateFailure(null);
                    return;
                }
                mSignInActivityView.signInSuccess(signInResponse);

//                mSignInActivityView.validateSuccess(signInResponse.getMessage());
            }

            @Override
            public void onFailure(Call<SignInResponse> call, Throwable t) {
                mSignInActivityView.validateFailure(null);
            }
        });
    }
}
