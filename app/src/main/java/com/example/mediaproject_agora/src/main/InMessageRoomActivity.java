package com.example.mediaproject_agora.src.main;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mediaproject_agora.R;
import com.example.mediaproject_agora.src.BaseActivity;
import com.example.mediaproject_agora.src.main.fragments.fragment_message.MessageListAdapter;
import com.example.mediaproject_agora.src.main.fragments.fragment_message.MessageListResponse;
import com.example.mediaproject_agora.src.main.interfaces.InMessageRoomActivityView;
import com.example.mediaproject_agora.src.main.interfaces.InPostActivityView;
import com.example.mediaproject_agora.src.main.items.CommentItem;
import com.example.mediaproject_agora.src.main.items.MessageItem;
import com.example.mediaproject_agora.src.main.models.CommentAdapter;
import com.example.mediaproject_agora.src.main.models.DefaultResponse;
import com.example.mediaproject_agora.src.main.models.InPostCommentResponse;
import com.example.mediaproject_agora.src.main.models.InPostPostResponse;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;


public class InMessageRoomActivity extends BaseActivity implements InMessageRoomActivityView, PopupMenu.OnMenuItemClickListener {


    private int message_room_idx;
    private String nickname;

    private ArrayList<MessageItem> m_message_item_list;
    private MessageListAdapter messageListAdapter;
    private RecyclerView rv_message_room_message_list;
    private LinearLayoutManager linear_layout_manager;

    private TextView tv_in_message_room_nickname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_in_message_room);

        bindViews();

        message_room_idx = getIntent().getExtras().getInt("message_room_idx", 0);
        nickname = getIntent().getExtras().getString("nickname", "상대방 닉네임 불러오기 실패");
        tv_in_message_room_nickname.setText(nickname);

        linear_layout_manager = new LinearLayoutManager(getApplicationContext());
        rv_message_room_message_list.setLayoutManager(linear_layout_manager);

        m_message_item_list = new ArrayList<>();
        messageListAdapter = new MessageListAdapter(m_message_item_list);
        rv_message_room_message_list.setAdapter(messageListAdapter);




        tryGetSpecificMessageRoom(message_room_idx);
    }

    public void bindViews() {
        tv_in_message_room_nickname = findViewById(R.id.tv_in_message_room_nickname);

        rv_message_room_message_list = findViewById(R.id.rv_message_room_message_list);
    }

    private void tryGetSpecificMessageRoom(int message_room_idx) {
        showProgressDialog();

        final InMessageRoomService inMessageRoomService = new InMessageRoomService(this);
        inMessageRoomService.getSpecificMessageRoom(message_room_idx);
    }

    @Override
    public void validateSuccess(String text) {

    }

    @Override
    public void validateFailure(String message) {

    }

    @Override
    public void getSpecificMessageRoomSuccess(MessageListResponse messageListResponse) {
        hideProgressDialog();

        switch (messageListResponse.getCode()){
            case 100:
                System.out.println("받아오는데 까지는 성공하셨군요");

                int length_of_message_history = messageListResponse.getMessageResults().size();

                for (int i = 0; i < length_of_message_history; i++){
                    MessageItem messageItem = new MessageItem();

                    messageItem.setStatus(messageListResponse.getMessageResults().get(i).getStatus());
                    messageItem.setContent(messageListResponse.getMessageResults().get(i).getContent());
                    messageItem.setTime(messageListResponse.getMessageResults().get(i).getTime());

                    m_message_item_list.add(messageItem);
                }
                messageListAdapter.notifyDataSetChanged();
                break;

            default:
                break;
        }
    }

    @Override
    public boolean onMenuItemClick(MenuItem menuItem) {
        return false;
    }

}
