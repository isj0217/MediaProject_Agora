package com.example.mediaproject_agora.src.main.fragments.fragment_mypage;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
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
import com.example.mediaproject_agora.src.main.DepartmentBoardActivity;
import com.example.mediaproject_agora.src.main.InPostActivity;
import com.example.mediaproject_agora.src.main.LikePostsActivity;
import com.example.mediaproject_agora.src.main.models.DefaultResponse;
import com.example.mediaproject_agora.src.sign_in.SignInActivity;

import java.io.IOException;
import java.net.URL;
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
    private LinearLayout ll_mypage_like_posts;
    private LinearLayout ll_mypage_log_out;

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
        ll_mypage_like_posts = viewGroup.findViewById(R.id.ll_mypage_like_posts);
        ll_mypage_log_out = viewGroup.findViewById(R.id.ll_mypage_log_out);
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
                        .setPositiveButton("수정", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {

                                // todo
                                // 서버에 수정요청하는 API 엮기

                                tryChangeNickname(et_mypage_nickname.getText().toString());

//                                tryPostDepartmentComment(department_board_idx, et_in_post_comment.getText().toString());
                            }
                        })
                        .setNegativeButton("취소", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        })
                        .setCancelable(false)
                        .show();
            }
        });


        ll_mypage_like_posts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), LikePostsActivity.class);
                startActivity(intent);
            }
        });


        ll_mypage_log_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(view.getContext()) // TestActivity 부분에는 현재 Activity의 이름 입력.
                        .setTitle("로그아웃")
                        .setMessage("로그아웃 하시겠습니까?")
                        .setPositiveButton("예", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {

                                Intent intent = new Intent(viewGroup.getContext(), SignInActivity.class);
                                startActivity(intent);
                                ((Activity) getContext()).finish();

                                Toast.makeText(getContext(), "로그아웃 처리가 완료되었습니다", Toast.LENGTH_SHORT).show();
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
//                Toast.makeText(viewGroup.getContext(), myPageResponse.getMessage(), Toast.LENGTH_SHORT).show();

                final String my_page_photo = myPageResponse.getMyPageResult().getUser_picture();
                iv_mypage_photo.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(my_page_photo));
                        startActivity(intent);
                    }
                });

                new DownloadPhotoTask().execute(my_page_photo);

                tv_mypage_nickname.setText(myPageResponse.getMyPageResult().getNickname());
                tv_mypage_user_name.setText(myPageResponse.getMyPageResult().getUser_name());
                tv_mypage_department_name.setText(myPageResponse.getMyPageResult().getDepartment_name());
                tv_mypage_student_id.setText(Integer.toString(myPageResponse.getMyPageResult().getUser_student_id()));

                break;
        }
    }

    private class DownloadPhotoTask extends AsyncTask<String, Void, Bitmap> {
        @Override
        protected Bitmap doInBackground(String... strings) {
            Bitmap bmp = null;
            try {
                String img_url = strings[0]; //url of the image
                URL url = new URL(img_url);
                bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
            return bmp;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }


        @Override
        protected void onPostExecute(Bitmap result) {
            // doInBackground 에서 받아온 total 값 사용 장소
            iv_mypage_photo.setImageBitmap(result);
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
