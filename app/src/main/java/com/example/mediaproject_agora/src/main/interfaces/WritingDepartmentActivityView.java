package com.example.mediaproject_agora.src.main.interfaces;

import com.example.mediaproject_agora.src.main.models.DefaultResponse;

public interface WritingDepartmentActivityView {

    void validateSuccess(String text);

    void validateFailure(String message);

    void postDepartmentPostSuccess(DefaultResponse defaultResponse);
}