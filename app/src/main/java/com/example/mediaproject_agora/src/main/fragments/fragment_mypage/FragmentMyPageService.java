package com.example.mediaproject_agora.src.main.fragments.fragment_mypage;

import com.example.mediaproject_agora.src.main.fragments.fragment_mypage.FragmentMyPageRetrofitInterface;
import com.example.mediaproject_agora.src.main.fragments.fragment_mypage.FragmentMyPageView;
import com.example.mediaproject_agora.src.main.fragments.fragment_mypage.MyPageResponse;
import com.example.mediaproject_agora.src.main.models.DefaultResponse;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.mediaproject_agora.src.ApplicationClass.X_ACCESS_TOKEN;
import static com.example.mediaproject_agora.src.ApplicationClass.getRetrofit;

//Comment Adapter로부터의 접근을 위해 public 붙여줌
public class FragmentMyPageService {
    private final FragmentMyPageView mFragmentMyPageView;
    private HashMap<String, Object> mParams;

    public FragmentMyPageService(FragmentMyPageView mFragmentMyPageView) {
        this.mFragmentMyPageView = mFragmentMyPageView;
    }

    public FragmentMyPageService(FragmentMyPageView mFragmentMyPageView, HashMap<String, Object> mParams) {
        this.mFragmentMyPageView = mFragmentMyPageView;
        this.mParams = mParams;
    }


    void getMyPage() {

        final FragmentMyPageRetrofitInterface fragmentMyPageRetrofitInterface = getRetrofit().create(FragmentMyPageRetrofitInterface.class);
        fragmentMyPageRetrofitInterface.getMyPage(X_ACCESS_TOKEN).enqueue(new Callback<MyPageResponse>() {
            @Override
            public void onResponse(Call<MyPageResponse> call, Response<MyPageResponse> response) {

                final MyPageResponse myPageResponse = response.body();
                if (myPageResponse == null) {
                    mFragmentMyPageView.validateFailure(null);
                    return;
                }
                mFragmentMyPageView.getMyPageSuccess(myPageResponse);
            }

            @Override
            public void onFailure(Call<MyPageResponse> call, Throwable t) {
                mFragmentMyPageView.validateFailure(null);
            }
        });
    }

    void changeNickname() {

        final FragmentMyPageRetrofitInterface fragmentMyPageRetrofitInterface = getRetrofit().create(FragmentMyPageRetrofitInterface.class);
        fragmentMyPageRetrofitInterface.changeNickname(X_ACCESS_TOKEN, mParams).enqueue(new Callback<DefaultResponse>() {
            @Override
            public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {

                final DefaultResponse defaultResponse = response.body();
                if (defaultResponse == null) {
                    mFragmentMyPageView.validateFailure(null);
                    return;
                }
                mFragmentMyPageView.changeNicknameSuccess(defaultResponse);
            }

            @Override
            public void onFailure(Call<DefaultResponse> call, Throwable t) {
                mFragmentMyPageView.validateFailure(null);
            }
        });

    }



}


//    void getTest() {
//        final SpecificBoardRetrofitInterface specificBoardRetrofitInterface = getRetrofit().create(SpecificBoardRetrofitInterface.class);
//        specificBoardRetrofitInterface.getTest().enqueue(new Callback<DefaultResponse>() {
//            @Override
//            public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {
//                final DefaultResponse defaultResponse = response.body();
//                if (defaultResponse == null) {
//                    mSpecificBoardActivityView.validateFailure(null);
//                    return;
//                }
//
//                mSpecificBoardActivityView.validateSuccess(defaultResponse.getMessage());
//            }
//
//            @Override
//            public void onFailure(Call<DefaultResponse> call, Throwable t) {
//                mSpecificBoardActivityView.validateFailure(null);
//            }
//        });
//    }

