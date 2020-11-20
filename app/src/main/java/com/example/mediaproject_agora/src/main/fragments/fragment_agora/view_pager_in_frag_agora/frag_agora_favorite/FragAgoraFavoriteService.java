package com.example.mediaproject_agora.src.main.fragments.fragment_agora.view_pager_in_frag_agora.frag_agora_favorite;


import com.example.mediaproject_agora.src.main.fragments.fragment_agora.FragmentAgoraRetrofitInterface;
import com.example.mediaproject_agora.src.main.models.FavoriteDepartmentResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.mediaproject_agora.src.ApplicationClass.X_ACCESS_TOKEN;
import static com.example.mediaproject_agora.src.ApplicationClass.getRetrofit;

class FragAgoraFavoriteService {
    private final FragAgoraFavoriteView mFragAgoraFavoriteView;
//    private HashMap<String, Object> mParams;

    public FragAgoraFavoriteService(final FragAgoraFavoriteView fragAgoraFavoriteView) {
        this.mFragAgoraFavoriteView = fragAgoraFavoriteView;
    }

//    FragAgoraDepartmentService(final FragAgoraDepartmentView fragAgoraDepartmentView, HashMap<String, Object> mParams) {
//        this.mFragAgoraDepartmentView = fragAgoraDepartmentView;
//        this.mParams = mParams;
//    }

    void getFavoriteDepartmentsList(){
        final FragmentAgoraRetrofitInterface fragmentAgoraRetrofitInterface = getRetrofit().create(FragmentAgoraRetrofitInterface.class);
        fragmentAgoraRetrofitInterface.getFavoriteDepartmentsList(X_ACCESS_TOKEN).enqueue(new Callback<FavoriteDepartmentResponse>() {
            @Override
            public void onResponse(Call<FavoriteDepartmentResponse> call, Response<FavoriteDepartmentResponse> response) {

                final FavoriteDepartmentResponse favoriteDepartmentResponse = response.body();
                if (favoriteDepartmentResponse == null) {
                    mFragAgoraFavoriteView.validateFailure(null);
                    return;
                }
                mFragAgoraFavoriteView.getFavoriteDepartmentListSuccess(favoriteDepartmentResponse);
            }

            @Override
            public void onFailure(Call<FavoriteDepartmentResponse> call, Throwable t) {

                mFragAgoraFavoriteView.validateFailure(null);
            }
        });
    }

//    void getDepartmentList() {
//        final FragAgoraDepartmentRetrofitInterface fragAgoraDepartmentRetrofitInterface = getRetrofit().create(FragAgoraDepartmentRetrofitInterface.class);
//        fragAgoraDepartmentRetrofitInterface.getDepartmentList(X_ACCESS_TOKEN).enqueue(new Callback<DepartmentResponse>() {
//            @Override
//           public void onResponse(Call<DepartmentResponse> call, Response<DepartmentResponse> response) {
//
//                final DepartmentResponse departmentResponse = response.body();
//                if (departmentResponse == null) {
//                    mFragAgoraDepartmentView.validateFailure(null);
//                    System.out.println("성공했는데 departmentResponse가 null");
//                    return;
//                }
//                mFragAgoraDepartmentView.getDepartmentListSuccess(departmentResponse);
//
//            }
//
//            @Override
//            public void onFailure(Call<DepartmentResponse> call, Throwable t) {
//                mFragAgoraDepartmentView.validateFailure(null);
//                System.out.println("아예 실패");
//            }
//        });
//    }
}
