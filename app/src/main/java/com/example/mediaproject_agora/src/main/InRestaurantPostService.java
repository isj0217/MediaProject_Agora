package com.example.mediaproject_agora.src.main;

import com.example.mediaproject_agora.src.main.fragments.fragment_restaurant.RestaurantResponse;
import com.example.mediaproject_agora.src.main.interfaces.InPostActivityView;
import com.example.mediaproject_agora.src.main.interfaces.InPostRetrofitInterface;
import com.example.mediaproject_agora.src.main.interfaces.InRestaurantPostActivityView;
import com.example.mediaproject_agora.src.main.interfaces.InRestaurantPostRetrofitInterface;
import com.example.mediaproject_agora.src.main.models.DefaultResponse;
import com.example.mediaproject_agora.src.main.models.InPostCommentResponse;
import com.example.mediaproject_agora.src.main.models.InPostPostResponse;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.mediaproject_agora.src.ApplicationClass.X_ACCESS_TOKEN;
import static com.example.mediaproject_agora.src.ApplicationClass.getRetrofit;

//Comment Adapter로부터의 접근을 위해 public 붙여줌
public class InRestaurantPostService {
    private final InRestaurantPostActivityView mInRestaurantPostActivityView;
    private HashMap<String, Object> mParams;

    public InRestaurantPostService(InRestaurantPostActivityView mInRestaurantPostActivityView) {
        this.mInRestaurantPostActivityView = mInRestaurantPostActivityView;
    }

    public InRestaurantPostService(InRestaurantPostActivityView mInRestaurantPostActivityView, HashMap<String, Object> mParams) {
        this.mInRestaurantPostActivityView = mInRestaurantPostActivityView;
        this.mParams = mParams;
    }

    void getSpecificRestaurantPost(int restaurant_post_idx) {

        final InRestaurantPostRetrofitInterface inRestaurantPostRetrofitInterface = getRetrofit().create(InRestaurantPostRetrofitInterface.class);
        inRestaurantPostRetrofitInterface.getSpecificRestaurantPost(X_ACCESS_TOKEN, restaurant_post_idx).enqueue(new Callback<RestaurantResponse>() {
            @Override
            public void onResponse(Call<RestaurantResponse> call, Response<RestaurantResponse> response) {

                final RestaurantResponse restaurantResponse = response.body();
                if (restaurantResponse == null) {
                    mInRestaurantPostActivityView.validateFailure(null);
                    return;
                }
                mInRestaurantPostActivityView.getRestaurantPostSuccess(restaurantResponse);
            }

            @Override
            public void onFailure(Call<RestaurantResponse> call, Throwable t) {
                mInRestaurantPostActivityView.validateFailure(null);
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

