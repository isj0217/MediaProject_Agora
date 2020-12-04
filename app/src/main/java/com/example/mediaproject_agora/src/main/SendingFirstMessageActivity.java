package com.example.mediaproject_agora.src.main;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;

import com.example.mediaproject_agora.R;
import com.example.mediaproject_agora.src.BaseActivity;
import com.example.mediaproject_agora.src.main.fragments.fragment_message.MessageListResponse;
import com.example.mediaproject_agora.src.main.interfaces.InMessageRoomActivityView;
import com.example.mediaproject_agora.src.main.interfaces.WritingDepartmentActivityView;
import com.example.mediaproject_agora.src.main.models.DefaultResponse;

import java.io.InputStream;
import java.util.HashMap;

public class SendingFirstMessageActivity extends BaseActivity implements InMessageRoomActivityView {

    private TextView tv_in_first_message_room_nickname;
    private EditText et_in_first_message_room_message;

    private String nickname;
    private int user_idx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_in_first_message_room);

        bindViews();

        nickname = getIntent().getExtras().getString("nickname", "상대방 닉네임 불러오기 오류");
        tv_in_first_message_room_nickname.setText(nickname);

        user_idx = getIntent().getExtras().getInt("user_idx");

        setClickListenersToViews();

    }

    public void bindViews() {
        tv_in_first_message_room_nickname = findViewById(R.id.tv_in_first_message_room_nickname);
        et_in_first_message_room_message = findViewById(R.id.et_in_first_message_room_message);
    }

    public void setClickListenersToViews() {

    }


    @Override
    public void onBackPressed() {

        new AlertDialog.Builder(SendingFirstMessageActivity.this) // TestActivity 부분에는 현재 Activity의 이름 입력.
                .setTitle("쪽지 보내기 취소")
                .setMessage("쪽지 보내기를 취소하시겠습니까?")
                .setPositiveButton("예", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                })
                .setNegativeButton("아니오", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                })
                .setCancelable(false)
                .show();
    }


    public void customOnClick(View view) {
        switch (view.getId()) {
            case R.id.iv_in_first_message_room_go_back:
                onBackPressed();
                break;
            case R.id.iv_in_first_message_room_send:
                trySendFirstMessage(user_idx, et_in_first_message_room_message.getText().toString());
                break;

        }

    }

    public void trySendFirstMessage(int user_idx, String message) {
        showProgressDialog();

        HashMap<String, Object> params = new HashMap<>();

        params.put("user_idx", user_idx);
        params.put("content", message);

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

    }

    @Override
    public void deleteMessageRoomSuccess(DefaultResponse defaultResponse) {

    }

    @Override
    public void sendMessageSuccess(DefaultResponse defaultResponse) {
        hideProgressDialog();

        switch (defaultResponse.getCode()) {
            case 100:
                showCustomToast(defaultResponse.getMessage());
                finish();
        }
    }


    // 이 밑으로는 템플릿

//    private void tryGetTest() {
//        showProgressDialog();
//
//        final MainService mainService = new MainService(this);
//        mainService.getTest();
//    }

//    @Override
//    public void validateSuccess(String text) {
//        hideProgressDialog();
////        mTvHelloWorld.setText(text);
//    }
//
//    @Override
//    public void validateFailure(@Nullable String message) {
//        hideProgressDialog();
//        showCustomToast(message == null || message.isEmpty() ? getString(R.string.network_error) : message);
//    }


}
