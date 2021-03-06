package com.example.mediaproject_agora.src.main;

import com.example.mediaproject_agora.src.main.fragments.fragment_restaurant.RestaurantResponse;
import com.example.mediaproject_agora.src.main.interfaces.InPostActivityView;
import com.example.mediaproject_agora.src.main.interfaces.InPostRetrofitInterface;
import com.example.mediaproject_agora.src.main.interfaces.InRestaurantPostActivityView;
import com.example.mediaproject_agora.src.main.interfaces.InRestaurantPostRetrofitInterface;
import com.example.mediaproject_agora.src.main.models.DefaultResponse;
import com.example.mediaproject_agora.src.main.models.InPostCommentResponse;
import com.example.mediaproject_agora.src.main.models.InPostPostResponse;
import com.example.mediaproject_agora.src.main.models.InRestaurantPostCommentResponse;

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

    void getSpecificRestaurantComment(int restaurant_post_idx) {

        final InRestaurantPostRetrofitInterface inRestaurantPostRetrofitInterface = getRetrofit().create(InRestaurantPostRetrofitInterface.class);
        inRestaurantPostRetrofitInterface.getSpecificRestaurantComment(X_ACCESS_TOKEN, restaurant_post_idx).enqueue(new Callback<InRestaurantPostCommentResponse>() {
            @Override
            public void onResponse(Call<InRestaurantPostCommentResponse> call, Response<InRestaurantPostCommentResponse> response) {

                final InRestaurantPostCommentResponse inRestaurantPostCommentResponse = response.body();
                if (inRestaurantPostCommentResponse == null) {
                    mInRestaurantPostActivityView.validateFailure(null);
                    return;
                }
                mInRestaurantPostActivityView.getRestaurantCommentSuccess(inRestaurantPostCommentResponse);
            }

            @Override
            public void onFailure(Call<InRestaurantPostCommentResponse> call, Throwable t) {
                mInRestaurantPostActivityView.validateFailure(null);
            }
        });
    }

    void postRestaurantComment(int restaurant_idx) {

        final InRestaurantPostRetrofitInterface inRestaurantPostRetrofitInterface = getRetrofit().create(InRestaurantPostRetrofitInterface.class);
        inRestaurantPostRetrofitInterface.postRestaurantComment(X_ACCESS_TOKEN, restaurant_idx, mParams).enqueue(new Callback<DefaultResponse>() {
            @Override
            public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {

                final DefaultResponse defaultResponse = response.body();
                if (defaultResponse == null) {
                    mInRestaurantPostActivityView.validateFailure(null);
                    return;
                }
                mInRestaurantPostActivityView.postRestaurantCommentSuccess(defaultResponse);
            }

            @Override
            public void onFailure(Call<DefaultResponse> call, Throwable t) {
                mInRestaurantPostActivityView.validateFailure(null);
            }
        });

    }

    // RestaurantCommentAdapter로부터의 접근을 위해 public 붙여줌
    public void deleteRestaurantComment(int restaurant_comment_idx) {
        final InRestaurantPostRetrofitInterface inRestaurantPostRetrofitInterface = getRetrofit().create(InRestaurantPostRetrofitInterface.class);
        inRestaurantPostRetrofitInterface.deleteRestaurantComment(X_ACCESS_TOKEN, restaurant_comment_idx).enqueue(new Callback<DefaultResponse>() {
            @Override
            public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {

                final DefaultResponse defaultResponse = response.body();
                if (defaultResponse == null) {
                    mInRestaurantPostActivityView.validateFailure(null);
                    return;
                }
                mInRestaurantPostActivityView.deleteRestaurantCommentSuccess(defaultResponse);
            }

            @Override
            public void onFailure(Call<DefaultResponse> call, Throwable t) {
                mInRestaurantPostActivityView.validateFailure(null);
            }
        });
    }

    void deleteRestaurantPost(int restaurant_idx) {
        final InRestaurantPostRetrofitInterface inRestaurantPostRetrofitInterface = getRetrofit().create(InRestaurantPostRetrofitInterface.class);
        inRestaurantPostRetrofitInterface.deleteRestaurantPost(X_ACCESS_TOKEN, restaurant_idx).enqueue(new Callback<DefaultResponse>() {
            @Override
            public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {

                final DefaultResponse defaultResponse = response.body();
                if (defaultResponse == null) {
                    mInRestaurantPostActivityView.validateFailure(null);
                    return;
                }
                mInRestaurantPostActivityView.deleteRestaurantPostSuccess(defaultResponse);
            }

            @Override
            public void onFailure(Call<DefaultResponse> call, Throwable t) {
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

