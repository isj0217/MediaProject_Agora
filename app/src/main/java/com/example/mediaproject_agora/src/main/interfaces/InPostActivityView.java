package com.example.mediaproject_agora.src.main.interfaces;

import com.example.mediaproject_agora.src.main.models.InPostPostResponse;

public interface InPostActivityView {

    void validateSuccess(String text);

    void validateFailure(String message);

    void getDepartmentPostSuccess(InPostPostResponse inPostPostResponse);
}