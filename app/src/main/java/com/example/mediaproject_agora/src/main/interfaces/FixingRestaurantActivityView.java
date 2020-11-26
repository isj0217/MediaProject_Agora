package com.example.mediaproject_agora.src.main.interfaces;

import com.example.mediaproject_agora.src.main.models.DefaultResponse;

public interface FixingRestaurantActivityView {

    void validateSuccess(String text);

    void validateFailure(String message);

    void patchRestaurantPostSuccess(DefaultResponse defaultResponse);

}