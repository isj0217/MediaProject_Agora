package com.example.mediaproject_agora.src.main;

import android.content.Intent;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mediaproject_agora.R;
import com.example.mediaproject_agora.src.BaseActivity;
import com.example.mediaproject_agora.src.main.interfaces.InPostActivityView;
import com.example.mediaproject_agora.src.main.models.InPostPostResponse;


public class InPostActivity extends BaseActivity implements InPostActivityView {

//    private ArrayList<CommentItem> m_comment_item_list;

//    private CommentAdapter comment_adapter;

    private CheckBox chk_in_post_anonymous;

    private RecyclerView rv_in_post_comment;
    private LinearLayoutManager linear_layout_manager;

    private TextView tv_in_post_user_idx, tv_in_post_department_board_idx, tv_in_post_nickname, tv_in_post_time, tv_in_post_title,
            tv_in_post_content, tv_in_post_like_num, tv_in_post_comment_num, tv_in_post_scrap_num;
    private ImageView iv_in_post_user_picture, iv_in_post_photo;

    private EditText et_in_post_comment;
    private ImageView iv_in_post_register_comment;

    private int m_index_of_this_post;

    private Intent intent;
    private int index_of_this_post;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_in_department_post);

        index_of_this_post = getIntent().getExtras().getInt("index_of_this_post", 0);

        tryGetSpecificDepartmentPost(index_of_this_post);


//        m_comment_item_list = new ArrayList<>();

//        comment_adapter = new CommentAdapter(m_comment_item_list);
        rv_in_post_comment = findViewById(R.id.rv_board_comment_list);

        linear_layout_manager = new LinearLayoutManager(getApplicationContext());
        rv_in_post_comment.setLayoutManager(linear_layout_manager);

//        rv_in_post_comment.setAdapter(comment_adapter);


        bindViews();

//        Toast.makeText(this, index_of_this_post, Toast.LENGTH_SHORT).show();

    }

    private void tryGetSpecificDepartmentPost(int department_board_idx) {
        showProgressDialog();

        final InPostService inPostService = new InPostService(this);
        inPostService.getSpecificDepartmentPost(department_board_idx);
    }


    public void bindViews() {

        tv_in_post_user_idx = findViewById(R.id.tv_in_post_user_idx);
        iv_in_post_user_picture = findViewById(R.id.iv_in_post_user_picture);
        tv_in_post_department_board_idx = findViewById(R.id.tv_in_post_department_board_idx);
        tv_in_post_title = findViewById(R.id.tv_in_post_title);
        tv_in_post_content = findViewById(R.id.tv_in_post_content);
        tv_in_post_nickname = findViewById(R.id.tv_in_post_nickname);
        tv_in_post_time = findViewById(R.id.tv_in_post_time);
        iv_in_post_photo = findViewById(R.id.iv_in_post_photo);
        tv_in_post_like_num = findViewById(R.id.tv_in_post_like_num);
        tv_in_post_comment_num = findViewById(R.id.tv_in_post_comment_num);

        et_in_post_comment = findViewById(R.id.et_in_post_comment);
        iv_in_post_register_comment = findViewById(R.id.iv_in_post_register_comment);
    }


    @Override
    public void validateSuccess(String text) {

    }

    @Override
    public void validateFailure(String message) {

    }

    @Override
    public void getDepartmentPostSuccess(InPostPostResponse inPostPostResponse) {
        hideProgressDialog();

        switch (inPostPostResponse.getCode()) {
            case 100:
                System.out.println("ㅇㅕ기까지만 오세요 제발");

                tv_in_post_user_idx.setText(Integer.toString(inPostPostResponse.getInPostPostResult().getUser_idx()));

                // todo
                // String으로 받아온 사진 어떻게 띄울 것인지 생각해보기
//                iv_in_post_user_picture.setImageResource(inPostPostResponse.getInPostPostResult().getUser_picture());
                tv_in_post_department_board_idx.setText(Integer.toString(inPostPostResponse.getInPostPostResult().getDepartment_board_idx()));
                tv_in_post_title.setText(inPostPostResponse.getInPostPostResult().getTitle());
                tv_in_post_content.setText(inPostPostResponse.getInPostPostResult().getContent());
                tv_in_post_nickname.setText(inPostPostResponse.getInPostPostResult().getNickname());
                tv_in_post_time.setText(inPostPostResponse.getInPostPostResult().getTime());
                // todo
                // String으로 받아온 사진 어떻게 띄울 것인지 생각해보기
//                iv_in_post_photo.setImageResource(inPostPostResponse.getInPostPostResult().getPhoto());
                tv_in_post_like_num.setText(Integer.toString(inPostPostResponse.getInPostPostResult().getLike_num()));
                tv_in_post_comment_num.setText(Integer.toString(inPostPostResponse.getInPostPostResult().getComment_num()));



                break;

            default:
                System.out.println("ㅇㅕ기로 가지 말구요");
                break;
        }
    }
}
