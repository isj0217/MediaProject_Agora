package com.example.mediaproject_agora.src.main.fragments.fragment_mypage;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.example.mediaproject_agora.R;
import com.example.mediaproject_agora.src.main.models.DefaultResponse;

import java.util.HashMap;

public class FragmentMyPage extends Fragment implements FragmentMyPageView {
    ViewGroup viewGroup;

    private ImageView iv_mypage_photo;
    private TextView tv_mypage_nickname;
    private TextView tv_mypage_user_name;
    private TextView tv_mypage_department_name;
    private TextView tv_mypage_student_id;
    private LinearLayout ll_mypage_change_nickname;
    private LinearLayout ll_mypage_change_profile_photo;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewGroup = (ViewGroup) inflater.inflate(R.layout.frag_mypage, container, false);

        bindViews();

        setClickListeners();

        tryGetMyPage();


        return viewGroup;
    }

    public void bindViews() {
        iv_mypage_photo = viewGroup.findViewById(R.id.iv_mypage_photo);
        tv_mypage_nickname = viewGroup.findViewById(R.id.tv_mypage_nickname);
        tv_mypage_user_name = viewGroup.findViewById(R.id.tv_mypage_user_name);
        tv_mypage_department_name = viewGroup.findViewById(R.id.tv_mypage_department_name);
        tv_mypage_student_id = viewGroup.findViewById(R.id.tv_mypage_student_id);
        ll_mypage_change_nickname = viewGroup.findViewById(R.id.ll_mypage_change_nickname);
        ll_mypage_change_profile_photo = viewGroup.findViewById(R.id.ll_mypage_change_profile_photo);
    }

    public void setClickListeners() {
        ll_mypage_change_nickname.setOnClickListener(new View.OnClickListener() {

            final EditText et_mypage_nickname = new EditText(viewGroup.getContext());
            @Override
            public void onClick(View view) {

                new AlertDialog.Builder(view.getContext())
                        .setTitle("닉네임 수정")
                        .setMessage("변경하려는 닉네임을 입력해주세요.")
                        .setView(et_mypage_nickname)
                        .setPositiveButton("수정하기", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {

                                // todo
                                // 서버에 수정요청하는 API 엮기

                                tryChangeNickname(et_mypage_nickname.getText().toString());

//                                tryPostDepartmentComment(department_board_idx, et_in_post_comment.getText().toString());
                            }
                        })
                        .setCancelable(true)
                        .show();
            }
        });
    }

    private void tryGetMyPage() {

        final FragmentMyPageService fragmentMyPageService = new FragmentMyPageService(this);
        fragmentMyPageService.getMyPage();
    }

    private void tryChangeNickname(String nickname) {

        HashMap<String, Object> params = new HashMap<>();

        params.put("nickname", nickname);

        final FragmentMyPageService fragmentMyPageService = new FragmentMyPageService(this, params);
        fragmentMyPageService.changeNickname();
    }


    @Override
    public void validateSuccess(String text) {

    }

    @Override
    public void validateFailure(String message) {

    }

    @Override
    public void getMyPageSuccess(MyPageResponse myPageResponse) {
        switch (myPageResponse.getCode()) {
            default:
                Toast.makeText(viewGroup.getContext(), myPageResponse.getMessage(), Toast.LENGTH_SHORT).show();

                tv_mypage_nickname.setText(myPageResponse.getMyPageResult().getNickname());
                tv_mypage_user_name.setText(myPageResponse.getMyPageResult().getUser_name());
                tv_mypage_department_name.setText(myPageResponse.getMyPageResult().getDepartment_name());
                tv_mypage_student_id.setText(Integer.toString(myPageResponse.getMyPageResult().getUser_student_id()));

                break;
        }
    }

    @Override
    public void changeNicknameSuccess(DefaultResponse defaultResponse) {
        switch (defaultResponse.getCode()) {
            default:
                Toast.makeText(viewGroup.getContext(), defaultResponse.getMessage(), Toast.LENGTH_SHORT).show();
                tryGetMyPage();
                break;
        }
    }
}
