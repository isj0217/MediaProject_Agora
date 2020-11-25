package com.example.mediaproject_agora.src.main.interfaces;

import com.example.mediaproject_agora.src.main.fragments.fragment_message.MessageListResponse;
import com.example.mediaproject_agora.src.main.fragments.fragment_restaurant.RestaurantResponse;
import com.example.mediaproject_agora.src.main.models.DefaultResponse;
import com.example.mediaproject_agora.src.main.models.InPostCommentResponse;
import com.example.mediaproject_agora.src.main.models.InRestaurantPostCommentResponse;

public interface InRestaurantPostActivityView {

    void validateSuccess(String text);

    void validateFailure(String message);

    void getRestaurantPostSuccess(RestaurantResponse restaurantResponse);

    void getRestaurantCommentSuccess(InRestaurantPostCommentResponse inRestaurantPostCommentResponse);

}