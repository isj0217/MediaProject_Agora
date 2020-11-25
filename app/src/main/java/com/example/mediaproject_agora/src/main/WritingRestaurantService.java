package com.example.mediaproject_agora.src.main;

import com.example.mediaproject_agora.src.main.interfaces.WritingDepartmentActivityView;
import com.example.mediaproject_agora.src.main.interfaces.WritingRestaurantActivityView;
import com.example.mediaproject_agora.src.main.interfaces.WritingRetrofitInterface;
import com.example.mediaproject_agora.src.main.models.DefaultResponse;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.mediaproject_agora.src.ApplicationClass.X_ACCESS_TOKEN;
import static com.example.mediaproject_agora.src.ApplicationClass.getRetrofit;

class WritingRestaurantService {
    private final WritingRestaurantActivityView mWritingRestaurantActivityView;
    private HashMap<String, Object> mParams;

//    SignInService(final SignInActivityView signInActivityView) {
//        this.mSignInActivityView = signInActivityView;
//    }

    WritingRestaurantService(final WritingRestaurantActivityView writingRestaurantActivityView, HashMap<String, Object> mParams) {
        this.mWritingRestaurantActivityView = writingRestaurantActivityView;
        this.mParams = mParams;
    }

    void postRestaurantPost() {
        final WritingRetrofitInterface writingRetrofitInterface = getRetrofit().create(WritingRetrofitInterface.class);
        writingRetrofitInterface.postRestaurantPost(X_ACCESS_TOKEN, mParams).enqueue(new Callback<DefaultResponse>() {
            @Override
            public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {

                final DefaultResponse defaultResponse = response.body();
                if (defaultResponse == null) {
                    mWritingRestaurantActivityView.validateFailure(null);
                    return;
                }
                mWritingRestaurantActivityView.postRestaurantPostSuccess(defaultResponse);

            }

            @Override
            public void onFailure(Call<DefaultResponse> call, Throwable t) {
                mWritingRestaurantActivityView.validateFailure(null);
            }
        });
    }
}
