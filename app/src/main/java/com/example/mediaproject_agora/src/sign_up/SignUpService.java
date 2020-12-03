package com.example.mediaproject_agora.src.sign_up;

import com.example.mediaproject_agora.src.sign_up.interfaces.SignUpActivityView;
import com.example.mediaproject_agora.src.sign_up.interfaces.SignUpRetrofitInterface;
import com.example.mediaproject_agora.src.sign_up.models.SignUpResponse;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.mediaproject_agora.src.ApplicationClass.getRetrofit;

class SignUpService {
    private final SignUpActivityView mSignUpActivityView;
    private HashMap<String, Object> mParams;

//    SignUpService(final SignUpActivityView signUpActivityView) {
//        this.mSignUpActivityView = signUpActivityView;
//    }

    SignUpService(final SignUpActivityView signUpActivityView, HashMap<String, Object> mParams) {
        this.mSignUpActivityView = signUpActivityView;
        this.mParams = mParams;
    }

    void postSignUp() {
        final SignUpRetrofitInterface signUpRetrofitInterface = getRetrofit().create(SignUpRetrofitInterface.class);
        signUpRetrofitInterface.signUpTest(mParams).enqueue(new Callback<SignUpResponse>() {
            @Override
            public void onResponse(Call<SignUpResponse> call, Response<SignUpResponse> response) {

                System.out.println("111111");

                final SignUpResponse signUpResponse = response.body();
                if (signUpResponse == null) {
                    System.out.println("222222");

                    mSignUpActivityView.validateFailure(null);
                    return;
                }
                mSignUpActivityView.signUpSuccess(signUpResponse);
                System.out.println("333333");


            }

            @Override
            public void onFailure(Call<SignUpResponse> call, Throwable t) {
                mSignUpActivityView.validateFailure(null);
                System.out.println("444444");

            }
        });
    }
}
