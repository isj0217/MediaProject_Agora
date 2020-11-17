package com.example.mediaproject_agora.src.main;

import android.widget.Toast;

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


//    void getDepartmentList() {
//        final MainRetrofitInterface mainRetrofitInterface = getRetrofit().create(MainRetrofitInterface.class);
//        mainRetrofitInterface.getDepartmentList().enqueue(new Callback<MainResponse>() {
//            @Override
//            public void onResponse(Call<MainResponse> call, Response<MainResponse> response) {
//                final MainResponse mainResponse = response.body();
//                if (mainResponse == null) {
//                    mMainActivityView.validateFailure(null);
//                    return;
//                }
//                mMainActivityView.getDepartmentListSuccess(mainResponse);
//            }
//
//            @Override
//            public void onFailure(Call<MainResponse> call, Throwable t) {
//                mMainActivityView.validateFailure(null);
//            }
//        });
//    }
}
