package com.example.mediaproject_agora.src.main.fragments.fragment_mypage;

import com.example.mediaproject_agora.src.main.models.DefaultResponse;

public interface FragmentMyPageView {

    void validateSuccess(String text);

    void validateFailure(String message);

    void getMyPageSuccess(MyPageResponse myPageResponse);

    void changeNicknameSuccess(DefaultResponse defaultResponse);

}