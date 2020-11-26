package com.example.mediaproject_agora.src.main;

import com.example.mediaproject_agora.src.main.interfaces.FixingDepartmentActivityView;
import com.example.mediaproject_agora.src.main.interfaces.FixingRestaurantActivityView;
import com.example.mediaproject_agora.src.main.interfaces.FixingRetrofitInterface;
import com.example.mediaproject_agora.src.main.models.DefaultResponse;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.mediaproject_agora.src.ApplicationClass.X_ACCESS_TOKEN;
import static com.example.mediaproject_agora.src.ApplicationClass.getRetrofit;

class FixingRestaurantService {
    private final FixingRestaurantActivityView mFixingRestaurantActivityView;
    private HashMap<String, Object> mParams;

    public FixingRestaurantService(FixingRestaurantActivityView mFixingRestaurantActivityView) {
        this.mFixingRestaurantActivityView = mFixingRestaurantActivityView;
    }

    public FixingRestaurantService(FixingRestaurantActivityView mFixingRestaurantActivityView, HashMap<String, Object> mParams) {
        this.mFixingRestaurantActivityView = mFixingRestaurantActivityView;
        this.mParams = mParams;
    }

    void patchRestaurantPost(int idx_of_restaurant_post_we_are_fixing) {
        final FixingRetrofitInterface fixingRetrofitInterface = getRetrofit().create(FixingRetrofitInterface.class);
        fixingRetrofitInterface.patchRestaurantPost(X_ACCESS_TOKEN, idx_of_restaurant_post_we_are_fixing, mParams).enqueue(new Callback<DefaultResponse>() {
            @Override
            public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {

                final DefaultResponse defaultResponse = response.body();
                if (defaultResponse == null) {
                    mFixingRestaurantActivityView.validateFailure(null);
                    return;
                }
                mFixingRestaurantActivityView.patchRestaurantPostSuccess(defaultResponse);
            }

            @Override
            public void onFailure(Call<DefaultResponse> call, Throwable t) {
                mFixingRestaurantActivityView.validateFailure(null);
            }
        });
    }
}
