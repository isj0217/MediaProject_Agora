package com.example.mediaproject_agora.src.main.interfaces;

import com.example.mediaproject_agora.src.main.models.DefaultResponse;
import com.example.mediaproject_agora.src.main.models.InPostCommentResponse;
import com.example.mediaproject_agora.src.main.models.InPostPostResponse;

public interface InPostActivityView {

    void validateSuccess(String text);

    void validateFailure(String message);

    void getDepartmentPostSuccess(InPostPostResponse inPostPostResponse);

    void getSpecificDepartmentCommentSuccess(InPostCommentResponse inPostCommentResponse);

    void postDepartmentCommentSuccess(DefaultResponse defaultResponse);

    void patchThumbUpDepartmentPostSuccess(DefaultResponse defaultResponse);

    void deleteDepartmentPostSuccess(DefaultResponse defaultResponse);
}