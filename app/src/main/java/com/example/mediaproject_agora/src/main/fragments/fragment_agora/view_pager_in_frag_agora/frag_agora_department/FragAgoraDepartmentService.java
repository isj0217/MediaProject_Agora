package com.example.mediaproject_agora.src.main.fragments.fragment_agora.view_pager_in_frag_agora.frag_agora_department;


import com.example.mediaproject_agora.src.main.fragments.fragment_agora.view_pager_in_frag_agora.frag_agora_department.models.DepartmentResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.mediaproject_agora.src.ApplicationClass.X_ACCESS_TOKEN;
import static com.example.mediaproject_agora.src.ApplicationClass.getRetrofit;

class FragAgoraDepartmentService {
    private final FragAgoraDepartmentView mFragAgoraDepartmentView;
//    private HashMap<String, Object> mParams;

    FragAgoraDepartmentService(final FragAgoraDepartmentView fragAgoraDepartmentView) {
        this.mFragAgoraDepartmentView = fragAgoraDepartmentView;
    }

//    FragAgoraDepartmentService(final FragAgoraDepartmentView fragAgoraDepartmentView, HashMap<String, Object> mParams) {
//        this.mFragAgoraDepartmentView = fragAgoraDepartmentView;
//        this.mParams = mParams;
//    }

    void getDepartmentList() {
        final FragAgoraDepartmentRetrofitInterface fragAgoraDepartmentRetrofitInterface = getRetrofit().create(FragAgoraDepartmentRetrofitInterface.class);
        fragAgoraDepartmentRetrofitInterface.getDepartmentList(X_ACCESS_TOKEN).enqueue(new Callback<DepartmentResponse>() {
            @Override
            public void onResponse(Call<DepartmentResponse> call, Response<DepartmentResponse> response) {

                System.out.println("제발 여기로 좀 들어와");

                final DepartmentResponse departmentResponse = response.body();
                if (departmentResponse == null) {
                    mFragAgoraDepartmentView.validateFailure(null);
                    return;
                }
                mFragAgoraDepartmentView.getDepartmentListSuccess(departmentResponse);
            }

            @Override
            public void onFailure(Call<DepartmentResponse> call, Throwable t) {

                System.out.println("여기 가지 말고");
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
