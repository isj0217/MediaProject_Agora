package com.example.mediaproject_agora.src.sign_up.interfaces;

import com.example.mediaproject_agora.src.sign_up.models.SignUpResponse;

public interface SignUpActivityView {

    void validateSuccess(String text);

    void validateFailure(String message);

    void signUpSuccess(SignUpResponse signUpResponse);
}