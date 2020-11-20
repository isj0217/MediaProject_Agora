package com.example.mediaproject_agora.src.main.fragments.fragment_message;


import com.example.mediaproject_agora.src.main.fragments.fragment_agora.view_pager_in_frag_agora.frag_agora_department.models.AddFavoriteResponse;
import com.example.mediaproject_agora.src.main.fragments.fragment_agora.view_pager_in_frag_agora.frag_agora_department.models.DepartmentResponse;

public interface FragmentMessageView {

    void validateSuccess(String text);

    void validateFailure(String message);

    void getMessageRoomListSuccess(MessageRoomResponse messageRoomResponse);
}