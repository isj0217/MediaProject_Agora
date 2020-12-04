package com.example.mediaproject_agora.src.main;

import com.example.mediaproject_agora.src.main.interfaces.DepartmentBoardActivityView;
import com.example.mediaproject_agora.src.main.interfaces.DepartmentBoardRetrofitInterface;
import com.example.mediaproject_agora.src.main.models.DepartmentBoardResponse;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.mediaproject_agora.src.ApplicationClass.X_ACCESS_TOKEN;
import static com.example.mediaproject_agora.src.ApplicationClass.getRetrofit;

class DepartmentBoardService {
    private final DepartmentBoardActivityView mDepartmentBoardActivityView;
    private HashMap<String, Object> mParams;

    public DepartmentBoardService(DepartmentBoardActivityView mDepartmentBoardActivityView) {
        this.mDepartmentBoardActivityView = mDepartmentBoardActivityView;
    }

    DepartmentBoardService(final DepartmentBoardActivityView departmentBoardActivityView, HashMap<String, Object> mParams) {
        this.mDepartmentBoardActivityView = departmentBoardActivityView;
        this.mParams = mParams;
    }


    void getSpecificDepartmentBoard(String department_name) {

//        System.out.println("보내려는 dept name: " + department_name);
        final DepartmentBoardRetrofitInterface departmentBoardRetrofitInterface = getRetrofit().create(DepartmentBoardRetrofitInterface.class);
        departmentBoardRetrofitInterface.getSpecificDepartmentBoardTest(X_ACCESS_TOKEN, department_name).enqueue(new Callback<DepartmentBoardResponse>() {
            @Override
            public void onResponse(Call<DepartmentBoardResponse> call, Response<DepartmentBoardResponse> response) {

                final DepartmentBoardResponse departmentBoardResponse = response.body();
                if (departmentBoardResponse == null) {
                    mDepartmentBoardActivityView.validateFailure(null);
                    return;
                }
                mDepartmentBoardActivityView.getSpecificDepartmentBoardSuccess(departmentBoardResponse);
            }

            @Override
            public void onFailure(Call<DepartmentBoardResponse> call, Throwable t) {
                mDepartmentBoardActivityView.validateFailure(null);
            }
        });
    }


    // 검색기능 구현
    void getFilteredDepartmentPost(String department_name, String text) {
        final DepartmentBoardRetrofitInterface departmentBoardRetrofitInterface = getRetrofit().create(DepartmentBoardRetrofitInterface.class);
        departmentBoardRetrofitInterface.getFilteredDepartmentBoard(X_ACCESS_TOKEN, department_name, text).enqueue(new Callback<DepartmentBoardResponse>() {
            @Override
            public void onResponse(Call<DepartmentBoardResponse> call, Response<DepartmentBoardResponse> response) {

                final DepartmentBoardResponse departmentBoardResponse = response.body();
                if (departmentBoardResponse == null) {
                    mDepartmentBoardActivityView.validateFailure(null);
                    return;
                }
                mDepartmentBoardActivityView.getFilteredDepartmentPostSuccess(departmentBoardResponse);
            }

            @Override
            public void onFailure(Call<DepartmentBoardResponse> call, Throwable t) {
                mDepartmentBoardActivityView.validateFailure(null);
            }
        });
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
}
