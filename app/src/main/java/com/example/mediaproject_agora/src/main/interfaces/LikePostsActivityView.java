package com.example.mediaproject_agora.src.main.interfaces;

import com.example.mediaproject_agora.src.main.models.DepartmentBoardResponse;
import com.example.mediaproject_agora.src.main.models.LikePostResponse;

public interface LikePostsActivityView {

    void validateSuccess(String text);

    void validateFailure(String message);

    void getLikePostsSuccess(LikePostResponse likePostResponse);
}