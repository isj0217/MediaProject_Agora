package com.example.mediaproject_agora.src.main;

import com.example.mediaproject_agora.src.main.interfaces.MainActivityView;
import com.example.mediaproject_agora.src.main.interfaces.MainRetrofitInterface;
import com.example.mediaproject_agora.src.main.interfaces.SpecificBoardActivityView;
import com.example.mediaproject_agora.src.main.models.DefaultResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.mediaproject_agora.src.ApplicationClass.getRetrofit;

class SpecificBoardService {
    private final SpecificBoardActivityView mSpecificBoardActivityView;

    SpecificBoardService(final SpecificBoardActivityView specificBoardActivityView) {
        this.mSpecificBoardActivityView = specificBoardActivityView;
    }

    void getTest() {
        final MainRetrofitInterface mainRetrofitInterface = getRetrofit().create(MainRetrofitInterface.class);
        mainRetrofitInterface.getTest().enqueue(new Callback<DefaultResponse>() {
            @Override
            public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {
                final DefaultResponse defaultResponse = response.body();
                if (defaultResponse == null) {
                    mSpecificBoardActivityView.validateFailure(null);
                    return;
                }

                mSpecificBoardActivityView.validateSuccess(defaultResponse.getMessage());
            }

            @Override
            public void onFailure(Call<DefaultResponse> call, Throwable t) {
                mSpecificBoardActivityView.validateFailure(null);
            }
        });
    }
}
