package com.example.mediaproject_agora.src.main.fragments.fragment_message;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.mediaproject_agora.R;

public class FragmentMessage extends Fragment implements FragmentMessageView{
    ViewGroup viewGroup;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewGroup = (ViewGroup) inflater.inflate(R.layout.frag_message, container, false);

        trygetMessageList();

        return viewGroup;
    }

    private void trygetMessageList() {

        final FragmentMessageService fragmentMessageService = new FragmentMessageService(this);
        fragmentMessageService.getMessageRoomList();
    }

    @Override
    public void validateSuccess(String text) {

    }

    @Override
    public void validateFailure(String message) {

    }

    @Override
    public void getMessageRoomListSuccess(MessageRoomResponse messageRoomResponse) {
        switch (messageRoomResponse.getCode()){
            default:
                System.out.println(messageRoomResponse.getMessage());
        }
    }
}
