package com.example.mediaproject_agora.src.main.fragments.fragment_restaurant;


import com.example.mediaproject_agora.src.main.fragments.fragment_message.MessageRoomResponse;

public interface FragmentRestaurantView {

    void validateSuccess(String text);

    void validateFailure(String message);

    void getRestaurantTotalSuccess(RestaurantListResponse restaurantListResponse);
}