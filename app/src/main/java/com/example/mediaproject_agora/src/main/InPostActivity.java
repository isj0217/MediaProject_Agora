package com.example.mediaproject_agora.src.main;

import android.content.Intent;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mediaproject_agora.R;
import com.example.mediaproject_agora.src.BaseActivity;
import com.example.mediaproject_agora.src.main.interfaces.InPostActivityView;
import com.example.mediaproject_agora.src.main.models.InPostResponse;

import java.util.HashMap;


public class InPostActivity extends BaseActivity implements InPostActivityView {

//    private ArrayList<CommentItem> m_comment_item_list;

//    private CommentAdapter comment_adapter;

    private CheckBox chk_in_post_anonymous;

    private RecyclerView rv_in_post_comment;
    private LinearLayoutManager linear_layout_manager;

    private TextView tv_in_post_nickname, tv_in_post_time, tv_in_post_title, tv_in_post_content, tv_in_post_like_num, tv_in_post_comment_num, tv_in_post_scrap_num;

//    private InPostService inPostService;
    private String clicked;

    private EditText et_in_post_comment;
    private ImageView iv_in_post_register_comment;

    private int m_clicked_free_pos;
    private int m_clicked_secret_pos;
    private int m_clicked_alumni_pos;
    private int m_clicked_freshmen_pos;

    private int m_from_board_num;

    private int m_index_of_this_post;

    private boolean m_from_frag_home;

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


        ViewBinding();

        Toast.makeText(this, index_of_this_post, Toast.LENGTH_SHORT).show();

    }

    private void tryGetSpecificDepartmentPost(int department_board_idx) {
        showProgressDialog();

        HashMap<String, Object> params = new HashMap<>();
        params.put("department_board_idx", department_board_idx);

        final InPostService inPostService = new InPostService(this, params);
        inPostService.getSpecificDepartmentPost(department_board_idx);
    }


    public void ViewBinding() {

        chk_in_post_anonymous = findViewById(R.id.chk_in_post_anonymous);

        et_in_post_comment = findViewById(R.id.et_in_post_comment);
        iv_in_post_register_comment = findViewById(R.id.iv_in_post_register_comment);

        tv_in_post_nickname = findViewById(R.id.tv_in_post_nickname);
        tv_in_post_time = findViewById(R.id.tv_in_post_time);
        tv_in_post_title = findViewById(R.id.tv_in_post_title);
        tv_in_post_content = findViewById(R.id.tv_in_post_content);
        tv_in_post_like_num = findViewById(R.id.tv_in_post_like_num);
        tv_in_post_comment_num = findViewById(R.id.tv_in_post_comment_num);
    }


    @Override
    public void validateSuccess(String text) {

    }

    @Override
    public void validateFailure(String message) {

    }

    @Override
    public void getDepartmentPostSuccess(InPostResponse inPostResponse) {
        
    }
}
