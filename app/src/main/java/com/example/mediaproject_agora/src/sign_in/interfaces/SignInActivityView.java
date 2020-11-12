package com.example.mediaproject_agora.src.sign_in.interfaces;

import com.example.mediaproject_agora.src.sign_in.models.SignInResponse;

public interface SignInActivityView {

    void validateSuccess(String text);

    void validateFailure(String message);

    void signInSuccess(SignInResponse signInResponse);

}