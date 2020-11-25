package com.example.mediaproject_agora.src.main.interfaces;

import com.example.mediaproject_agora.src.main.fragments.fragment_message.MessageListResponse;
import com.example.mediaproject_agora.src.main.fragments.fragment_restaurant.RestaurantResponse;
import com.example.mediaproject_agora.src.main.models.DefaultResponse;

public interface InRestaurantPostActivityView {

    void validateSuccess(String text);

    void validateFailure(String message);

    void getRestaurantPostSuccess(RestaurantResponse restaurantResponse);

}