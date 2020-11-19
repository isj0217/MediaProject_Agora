package com.example.mediaproject_agora.src.main;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mediaproject_agora.R;
import com.example.mediaproject_agora.src.BaseActivity;
import com.example.mediaproject_agora.src.main.interfaces.InPostActivityView;
import com.example.mediaproject_agora.src.main.items.CommentItem;
import com.example.mediaproject_agora.src.main.items.DepartmentPostItem;
import com.example.mediaproject_agora.src.main.models.CommentAdapter;
import com.example.mediaproject_agora.src.main.models.InPostCommentResponse;
import com.example.mediaproject_agora.src.main.models.InPostPostResponse;

import java.util.ArrayList;


public class InPostActivity extends BaseActivity implements InPostActivityView {

    private ArrayList<CommentItem> m_comment_item_list;

    private CommentAdapter comment_adapter;

    private RecyclerView rv_in_post_comment;
    private LinearLayoutManager linear_layout_manager;

    private TextView tv_in_post_user_idx, tv_in_post_department_board_idx, tv_in_post_nickname, tv_in_post_time, tv_in_post_title,
            tv_in_post_content, tv_in_post_like_num, tv_in_post_comment_num;

    // 댓글에 담긴 TextView 4개
    private TextView tv_item_comment_content, tv_item_comment_time, tv_item_comment_nickname, tv_item_comment_department_comment_index;

    private ImageView iv_in_post_user_picture, iv_in_post_photo;

    private EditText et_in_post_comment;
    private ImageView iv_in_post_register_comment;

    private int index_of_this_post;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_in_department_post);

        index_of_this_post = getIntent().getExtras().getInt("index_of_this_post", 0);

        tryGetSpecificDepartmentPost(index_of_this_post);

        tryGetSpecificDepartmentComment(index_of_this_post);


        m_comment_item_list = new ArrayList<>();

        comment_adapter = new CommentAdapter(m_comment_item_list);
        rv_in_post_comment = findViewById(R.id.rv_board_comment_list);

        linear_layout_manager = new LinearLayoutManager(getApplicationContext());
        rv_in_post_comment.setLayoutManager(linear_layout_manager);

        rv_in_post_comment.setAdapter(comment_adapter);


        bindViews();

//        Toast.makeText(this, index_of_this_post, Toast.LENGTH_SHORT).show();

    }

    private void tryGetSpecificDepartmentPost(int department_board_idx) {
        showProgressDialog();

        final InPostService inPostService = new InPostService(this);
        inPostService.getSpecificDepartmentPost(department_board_idx);
    }

    private void tryGetSpecificDepartmentComment(int department_board_idx) {
        showProgressDialog();

        final InPostService inPostService = new InPostService(this);
        inPostService.getSpecificDepartmentComments(department_board_idx);
    }




    public void bindViews() {

        // 게시글 레이아웃
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

        // 댓글 레이아웃
        tv_item_comment_content = findViewById(R.id.tv_item_comment_content);
        tv_item_comment_time =findViewById(R.id.tv_item_comment_time);
        tv_item_comment_nickname =findViewById(R.id.tv_item_comment_nickname);
        tv_item_comment_department_comment_index =findViewById(R.id.tv_item_comment_department_comment_index);

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

    @Override
    public void getSpecificDepartmentCommentSuccess(InPostCommentResponse inPostCommentResponse) {
        hideProgressDialog();

        switch (inPostCommentResponse.getCode()) {
            case 100:
                System.out.println("ㅇㅕ기로 오십쇼");

                /**
                 * CommentItem 형식의 ArrayList에 모두 넣어두고 어댑터를 이용해서 하나하나 레이아웃에 갖다 붙이자!!
                 * */

                int num_of_comments_in_the_post = inPostCommentResponse.getInPostCommentResults().size();

                for (int i = 0; i < num_of_comments_in_the_post; i++){
                    CommentItem commentItem = new CommentItem();

                    commentItem.setComment(inPostCommentResponse.getInPostCommentResults().get(i).getComment());
                    commentItem.setTime(inPostCommentResponse.getInPostCommentResults().get(i).getTime());
                    commentItem.setNickname(inPostCommentResponse.getInPostCommentResults().get(i).getNickname());
                    commentItem.setDepartment_comment_idx(inPostCommentResponse.getInPostCommentResults().get(i).getDepartment_board_idx());

                    m_comment_item_list.add(commentItem);
                }
                comment_adapter.notifyDataSetChanged();

                break;

            default:
                System.out.println("ㅇㅕ기로 가시지 마십쇼");
                break;
        }
    }
}
