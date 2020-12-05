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
import com.example.mediaproject_agora.src.main.fragments.fragment_message.FragmentMessage;
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
    private int user_idx;
    private String nickname;

    private ArrayList<MessageItem> m_message_item_list;
    private MessageListAdapter messageListAdapter;
    private RecyclerView rv_message_room_message_list;
    private LinearLayoutManager linear_layout_manager;

    private TextView tv_in_message_room_nickname;

    private ImageView iv_in_message_room_more, iv_in_message_room_send;
    private EditText et_in_message_room_message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_in_message_room);

        bindViews();

        loadSpecificMessageRoomInfos();

//        message_room_idx = getIntent().getExtras().getInt("message_room_idx", 0);
//        user_idx = getIntent().getExtras().getInt("user_idx", 0);
//        nickname = getIntent().getExtras().getString("nickname", "상대방 닉네임 불러오기 실패");
//        tv_in_message_room_nickname.setText(nickname);

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

        iv_in_message_room_more = findViewById(R.id.iv_in_message_room_more);
        iv_in_message_room_send = findViewById(R.id.iv_in_message_room_send);
        et_in_message_room_message = findViewById(R.id.et_in_message_room_message);
    }

    private void tryGetSpecificMessageRoom(int message_room_idx) {
        showProgressDialog();

        final InMessageRoomService inMessageRoomService = new InMessageRoomService(this);
        inMessageRoomService.getSpecificMessageRoom(message_room_idx);
    }

    private void trySendMessage(String content, int user_idx) {
        showProgressDialog();

        HashMap<String, Object> params = new HashMap<>();
        params.put("content", content);
        params.put("user_idx", user_idx);

        final InMessageRoomService inMessageRoomService = new InMessageRoomService(this, params);
        inMessageRoomService.sendMessage();
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

        switch (messageListResponse.getCode()) {
            case 100:
//                System.out.println("받아오는데 까지는 성공하셨군요");

                int length_of_message_history = messageListResponse.getMessageResults().size();

                for (int i = 0; i < length_of_message_history; i++) {
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
    public void deleteMessageRoomSuccess(DefaultResponse defaultResponse) {
        hideProgressDialog();

        switch (defaultResponse.getCode()){
            case 100:
                System.out.println("쪽지함 삭제 성공??");
                break;

            default:
                showCustomToast(defaultResponse.getMessage());
                break;
        }
    }

    @Override
    public void sendMessageSuccess(DefaultResponse defaultResponse) {
        hideProgressDialog();

        switch (defaultResponse.getCode()){
            case 100:
                System.out.println("쪽지 보내기 성공???");
                et_in_message_room_message.setText("");
                restartActivity(InMessageRoomActivity.this);
                break;

            default:
                showCustomToast(defaultResponse.getMessage());
                break;
        }
    }

    public void customOnClick(View view) {
        switch (view.getId()) {
            case R.id.iv_in_message_room_more:
                showPopUp(view);
                break;

            case R.id.iv_in_message_room_go_back:
                onBackPressed();
                break;

            case R.id.iv_in_message_room_send:
                trySendMessage(et_in_message_room_message.getText().toString(), user_idx);
                break;

            case R.id.iv_in_message_room_sync:
                restartActivity(InMessageRoomActivity.this);
                break;
        }
    }

    public void showPopUp(View v) {
        PopupMenu popupMenu = new PopupMenu(this, v);

        popupMenu.setOnMenuItemClickListener(this);

            popupMenu.inflate(R.menu.menu_in_message_room);
            popupMenu.show();
    }


    @Override
    public boolean onMenuItemClick(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.menu_in_message_room_delete_message_room:
                // todo
                // 쪽지방 삭제 API 엮기

                tryDeleteMessageRoom(message_room_idx);

//                Intent intent = new Intent(InMessageRoomActivity.this, FragmentMessage.class);
//                startActivity(intent);

                finish();

                break;
        }
        return false;
    }

    private void tryDeleteMessageRoom(int message_room_idx) {
        showProgressDialog();

        final InMessageRoomService inMessageRoomService = new InMessageRoomService(this);
        inMessageRoomService.deleteMessageRoom(message_room_idx);
    }

    private void restartActivity(Activity activity) {
        Intent intent = new Intent();
        intent.setClass(activity, activity.getClass());
        activity.startActivity(intent);
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        activity.finish();
    }

    public void loadSpecificMessageRoomInfos() {
        SharedPreferences sharedPreferences = getSharedPreferences("specific_message_room_infos", MODE_PRIVATE);
        nickname = sharedPreferences.getString("nickname", "SECTION 불러오기 실패");
        message_room_idx = sharedPreferences.getInt("message_room_idx", 0);
        user_idx = sharedPreferences.getInt("user_idx", 0);

        tv_in_message_room_nickname.setText(nickname);
    }

}
