package com.example.mediaproject_agora.src.main.fragments.fragment_agora.view_pager_in_frag_agora.frag_agora_department;


import com.example.mediaproject_agora.src.main.fragments.fragment_agora.FragAgoraRetrofitInterface;
import com.example.mediaproject_agora.src.main.fragments.fragment_agora.view_pager_in_frag_agora.frag_agora_department.models.AddFavoriteResponse;
import com.example.mediaproject_agora.src.main.fragments.fragment_agora.view_pager_in_frag_agora.frag_agora_department.models.DepartmentResponse;
import com.example.mediaproject_agora.src.main.models.FavoriteDepartmentResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.mediaproject_agora.src.ApplicationClass.X_ACCESS_TOKEN;
import static com.example.mediaproject_agora.src.ApplicationClass.getRetrofit;

public class FragAgoraDepartmentService {
    private final FragAgoraDepartmentView mFragAgoraDepartmentView;
//    private HashMap<String, Object> mParams;

    public FragAgoraDepartmentService(final FragAgoraDepartmentView fragAgoraDepartmentView) {
        this.mFragAgoraDepartmentView = fragAgoraDepartmentView;
    }

//    FragAgoraDepartmentService(final FragAgoraDepartmentView fragAgoraDepartmentView, HashMap<String, Object> mParams) {
//        this.mFragAgoraDepartmentView = fragAgoraDepartmentView;
//        this.mParams = mParams;
//    }

    void getDepartmentList() {
        final FragAgoraRetrofitInterface fragAgoraRetrofitInterface = getRetrofit().create(FragAgoraRetrofitInterface.class);
        fragAgoraRetrofitInterface.getDepartmentList(X_ACCESS_TOKEN).enqueue(new Callback<DepartmentResponse>() {
            @Override
            public void onResponse(Call<DepartmentResponse> call, Response<DepartmentResponse> response) {

                final DepartmentResponse departmentResponse = response.body();
                if (departmentResponse == null) {
                    mFragAgoraDepartmentView.validateFailure(null);
                    return;
                }
                mFragAgoraDepartmentView.getDepartmentListSuccess(departmentResponse);
            }

            @Override
            public void onFailure(Call<DepartmentResponse> call, Throwable t) {

                mFragAgoraDepartmentView.validateFailure(null);
            }
        });
    }

    void patchFavoriteDepartment(String department_name) {
        final FragAgoraRetrofitInterface fragAgoraRetrofitInterface = getRetrofit().create(FragAgoraRetrofitInterface.class);
        fragAgoraRetrofitInterface.patchFavoriteDepartment(X_ACCESS_TOKEN, department_name).enqueue(new Callback<AddFavoriteResponse>() {
            @Override
            public void onResponse(Call<AddFavoriteResponse> call, Response<AddFavoriteResponse> response) {

                final AddFavoriteResponse addFavoriteResponse = response.body();
                if (addFavoriteResponse == null) {
                    mFragAgoraDepartmentView.validateFailure(null);
                    return;
                }
                mFragAgoraDepartmentView.patchFavoriteDepartmentSuccess(addFavoriteResponse);
            }

            @Override
            public void onFailure(Call<AddFavoriteResponse> call, Throwable t) {

                mFragAgoraDepartmentView.validateFailure(null);
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
