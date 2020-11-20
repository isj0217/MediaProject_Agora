package com.example.mediaproject_agora.src.main.fragments.fragment_message;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mediaproject_agora.R;
import com.example.mediaproject_agora.src.main.fragments.fragment_agora.DepartmentBoardAdapter;
import com.example.mediaproject_agora.src.main.fragments.fragment_agora.view_pager_in_frag_agora.frag_agora_department.models.DepartmentResponse;
import com.example.mediaproject_agora.src.main.items.DepartmentPostItem;
import com.example.mediaproject_agora.src.main.items.MessageRoomItem;

import java.util.ArrayList;

public class FragmentMessage extends Fragment implements FragmentMessageView{
    ViewGroup viewGroup;

    private ArrayList<MessageRoomItem> m_message_room_item_list;
    private MessageRoomAdapter messageRoomAdapter;
    private RecyclerView rv_message_box;
    private LinearLayoutManager linear_layout_manager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewGroup = (ViewGroup) inflater.inflate(R.layout.frag_message, container, false);

        rv_message_box = viewGroup.findViewById(R.id.rv_message_box_message_list);

        linear_layout_manager = new LinearLayoutManager(viewGroup.getContext());
        rv_message_box.setLayoutManager(linear_layout_manager);

        m_message_room_item_list = new ArrayList<>();
        messageRoomAdapter = new MessageRoomAdapter(m_message_room_item_list);
        rv_message_box.setAdapter(messageRoomAdapter);

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
                loadMessageRooms(messageRoomResponse);
        }
    }

    public void loadMessageRooms(MessageRoomResponse messageRoomResponse){
        switch (messageRoomResponse.getCode()){
            case 100:
                int num_of_message_rooms = messageRoomResponse.getMessageRoomResults().size();

                for (int i = 0; i < num_of_message_rooms; i++){
                    MessageRoomItem messageRoomItem = new MessageRoomItem();

                    messageRoomItem.setMessage_room_idx(messageRoomResponse.getMessageRoomResults().get(i).getMessage_room_idx());
                    messageRoomItem.setUser_idx(messageRoomResponse.getMessageRoomResults().get(i).getUser_idx());
                    messageRoomItem.setUser_nickname(messageRoomResponse.getMessageRoomResults().get(i).getUser_nickname());
                    messageRoomItem.setMessage_idx(messageRoomResponse.getMessageRoomResults().get(i).getMessage_idx());
                    messageRoomItem.setContent(messageRoomResponse.getMessageRoomResults().get(i).getContent());
                    messageRoomItem.setTime(messageRoomResponse.getMessageRoomResults().get(i).getTime());

                    m_message_room_item_list.add(messageRoomItem);
                }

                break;

            default:
                break;
        }
    }
}
