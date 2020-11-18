package com.example.mediaproject_agora.src.main.interfaces;

import com.example.mediaproject_agora.src.main.models.DefaultResponse;
import com.example.mediaproject_agora.src.main.models.InPostResponse;

public interface InPostActivityView {

    void validateSuccess(String text);

    void validateFailure(String message);

    void getDepartmentPostSuccess(InPostResponse inPostResponse);
}