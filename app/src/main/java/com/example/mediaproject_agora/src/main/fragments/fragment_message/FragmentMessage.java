package com.example.mediaproject_agora.src.main.fragments.fragment_message;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mediaproject_agora.R;
import com.example.mediaproject_agora.src.main.items.MessageRoomItem;

import java.util.ArrayList;

import static android.view.View.GONE;

public class FragmentMessage extends Fragment implements FragmentMessageView{
    ViewGroup viewGroup;

    private ArrayList<MessageRoomItem> m_message_room_item_list;
    private MessageRoomAdapter messageRoomAdapter;
    private RecyclerView rv_message_box;
    private LinearLayoutManager linear_layout_manager;

    private LinearLayout ll_message_empty;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewGroup = (ViewGroup) inflater.inflate(R.layout.frag_message, container, false);

        ll_message_empty = viewGroup.findViewById(R.id.ll_message_empty);

        rv_message_box = viewGroup.findViewById(R.id.rv_message_box_message_list);

        linear_layout_manager = new LinearLayoutManager(viewGroup.getContext());
        rv_message_box.setLayoutManager(linear_layout_manager);

        m_message_room_item_list = new ArrayList<>();
        messageRoomAdapter = new MessageRoomAdapter(m_message_room_item_list);
        rv_message_box.setAdapter(messageRoomAdapter);

        tryGetMessageList();

        return viewGroup;
    }


    private void tryGetMessageList() {

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

                if (num_of_message_rooms == 0) {
                    ll_message_empty.setVisibility(View.VISIBLE);
                } else {
                    ll_message_empty.setVisibility(GONE);

                    for (int i = 0; i < num_of_message_rooms; i++) {
                        MessageRoomItem messageRoomItem = new MessageRoomItem();

                        messageRoomItem.setMessage_room_idx(messageRoomResponse.getMessageRoomResults().get(i).getMessage_room_idx());
                        messageRoomItem.setUser_idx(messageRoomResponse.getMessageRoomResults().get(i).getUser_idx());
                        messageRoomItem.setUser_nickname(messageRoomResponse.getMessageRoomResults().get(i).getUser_nickname());
                        messageRoomItem.setMessage_idx(messageRoomResponse.getMessageRoomResults().get(i).getMessage_idx());
                        messageRoomItem.setContent(messageRoomResponse.getMessageRoomResults().get(i).getContent());
                        messageRoomItem.setTime(messageRoomResponse.getMessageRoomResults().get(i).getTime());

                        m_message_room_item_list.add(messageRoomItem);
                    }
                    messageRoomAdapter.notifyDataSetChanged();
                }
                break;

            default:
                break;
        }
    }
}
