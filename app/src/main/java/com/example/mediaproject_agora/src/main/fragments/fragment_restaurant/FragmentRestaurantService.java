package com.example.mediaproject_agora.src.main.fragments.fragment_restaurant;


import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.mediaproject_agora.src.ApplicationClass.X_ACCESS_TOKEN;
import static com.example.mediaproject_agora.src.ApplicationClass.getRetrofit;

public class FragmentRestaurantService {
    private final FragmentRestaurantView mFragmentRestaurantView;
    private HashMap<String, Object> mParams;

    public FragmentRestaurantService(FragmentRestaurantView mFragmentRestaurantView) {
        this.mFragmentRestaurantView = mFragmentRestaurantView;
    }

    public FragmentRestaurantService(FragmentRestaurantView mFragmentRestaurantView, HashMap<String, Object> mParams) {
        this.mFragmentRestaurantView = mFragmentRestaurantView;
        this.mParams = mParams;
    }

    void getRestaurantTotal(int filter) {
        final FragmentRestaurantRetrofitInterface fragmentRestaurantRetrofitInterface = getRetrofit().create(FragmentRestaurantRetrofitInterface.class);
        fragmentRestaurantRetrofitInterface.getRestaurantTotal(X_ACCESS_TOKEN, filter).enqueue(new Callback<RestaurantListResponse>() {
            @Override
            public void onResponse(Call<RestaurantListResponse> call, Response<RestaurantListResponse> response) {

                final RestaurantListResponse restaurantListResponse = response.body();
                if (restaurantListResponse == null) {
                    mFragmentRestaurantView.validateFailure(null);
                    return;
                }
                mFragmentRestaurantView.getRestaurantListSuccess(restaurantListResponse);
            }

            @Override
            public void onFailure(Call<RestaurantListResponse> call, Throwable t) {

                mFragmentRestaurantView.validateFailure(null);
            }
        });
    }

    void getCategorizedRestaurant(int filter, int category) {
        final FragmentRestaurantRetrofitInterface fragmentRestaurantRetrofitInterface = getRetrofit().create(FragmentRestaurantRetrofitInterface.class);
        fragmentRestaurantRetrofitInterface.getCategorizedRestaurant(X_ACCESS_TOKEN, filter, category).enqueue(new Callback<RestaurantListResponse>() {
            @Override
            public void onResponse(Call<RestaurantListResponse> call, Response<RestaurantListResponse> response) {

                final RestaurantListResponse restaurantListResponse = response.body();
                if (restaurantListResponse == null) {
                    mFragmentRestaurantView.validateFailure(null);
                    return;
                }
                mFragmentRestaurantView.getRestaurantListSuccess(restaurantListResponse);
            }

            @Override
            public void onFailure(Call<RestaurantListResponse> call, Throwable t) {

                mFragmentRestaurantView.validateFailure(null);
            }
        });
    }







//    void patchFavoriteDepartment(String department_name) {
//        final FragAgoraRetrofitInterface fragAgoraRetrofitInterface = getRetrofit().create(FragAgoraRetrofitInterface.class);
//        fragAgoraRetrofitInterface.patchFavoriteDepartment(X_ACCESS_TOKEN, department_name).enqueue(new Callback<AddFavoriteResponse>() {
//            @Override
//            public void onResponse(Call<AddFavoriteResponse> call, Response<AddFavoriteResponse> response) {
//
//                final AddFavoriteResponse addFavoriteResponse = response.body();
//                if (addFavoriteResponse == null) {
//                    mFragAgoraDepartmentView.validateFailure(null);
//                    return;
//                }
//                mFragAgoraDepartmentView.patchFavoriteDepartmentSuccess(addFavoriteResponse);
//            }
//
//            @Override
//            public void onFailure(Call<AddFavoriteResponse> call, Throwable t) {
//
//                mFragAgoraDepartmentView.validateFailure(null);
//            }
//        });
//    }


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
