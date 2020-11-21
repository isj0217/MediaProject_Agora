package com.example.mediaproject_agora.src.main.interfaces;

import com.example.mediaproject_agora.src.main.fragments.fragment_message.MessageListResponse;
import com.example.mediaproject_agora.src.main.models.DefaultResponse;
import com.example.mediaproject_agora.src.main.models.InPostCommentResponse;
import com.example.mediaproject_agora.src.main.models.InPostPostResponse;

public interface InMessageRoomActivityView {

    void validateSuccess(String text);

    void validateFailure(String message);

    void getSpecificMessageRoomSuccess(MessageListResponse messageListResponse);

    void deleteMessageRoomSuccess(DefaultResponse defaultResponse);
}