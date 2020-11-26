package com.example.mediaproject_agora.src.main;

import android.app.Activity;
import android.content.Context;
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
import com.example.mediaproject_agora.src.main.fragments.fragment_restaurant.RestaurantResponse;
import com.example.mediaproject_agora.src.main.interfaces.InPostActivityView;
import com.example.mediaproject_agora.src.main.interfaces.InRestaurantPostActivityView;
import com.example.mediaproject_agora.src.main.items.CommentItem;
import com.example.mediaproject_agora.src.main.items.RestaurantCommentItem;
import com.example.mediaproject_agora.src.main.models.CommentAdapter;
import com.example.mediaproject_agora.src.main.models.DefaultResponse;
import com.example.mediaproject_agora.src.main.models.InPostCommentResponse;
import com.example.mediaproject_agora.src.main.models.InPostPostResponse;
import com.example.mediaproject_agora.src.main.models.InRestaurantPostCommentResponse;
import com.example.mediaproject_agora.src.main.models.RestaurantCommentAdapter;

import java.util.ArrayList;
import java.util.HashMap;


public class InRestaurantPostActivity extends BaseActivity implements InRestaurantPostActivityView, PopupMenu.OnMenuItemClickListener {

    private ArrayList<RestaurantCommentItem> m_restaurant_comment_item_list;
    private RestaurantCommentAdapter comment_adapter;
    private RecyclerView rv_in_restaurant_post_comment;
    private LinearLayoutManager linear_layout_manager;


    private int index_of_this_restaurant_post;

    private ImageView iv_in_restaurant_post_photo;

    private TextView tv_in_restaurant_post_nickname;
    private TextView tv_in_restaurant_post_time;
    private TextView tv_in_restaurant_post_restaurant_idx;
    private TextView tv_in_restaurant_post_restaurant_name;
    private TextView tv_in_restaurant_post_menu_name;
    private TextView tv_in_restaurant_post_price;
    private TextView tv_in_restaurant_post_content;
    private TextView tv_in_restaurant_post_star;
    private TextView tv_in_restaurant_post_comment_num;

    private ImageView iv_in_restaurant_post_register_comment;
    private EditText et_in_restaurant_post_comment;

    private int is_mine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_in_restaurant_post);

        m_restaurant_comment_item_list = new ArrayList<>();
        comment_adapter = new RestaurantCommentAdapter(m_restaurant_comment_item_list, getApplicationContext());
        rv_in_restaurant_post_comment = findViewById(R.id.rv_in_restaurant_post_comment);

        linear_layout_manager = new LinearLayoutManager(getApplicationContext());
        rv_in_restaurant_post_comment.setLayoutManager(linear_layout_manager);

        rv_in_restaurant_post_comment.setAdapter(comment_adapter);

        bindViews();

        setClickListenersToButtons();

        index_of_this_restaurant_post = getIntent().getExtras().getInt("index_of_this_restaurant_post", 0);

        tryGetSpecificRestaurantPost(index_of_this_restaurant_post);

        tryGetSpecificRestaurantComment(index_of_this_restaurant_post);

    }

    public void bindViews() {
        iv_in_restaurant_post_photo = findViewById(R.id.iv_in_restaurant_post_photo);

        tv_in_restaurant_post_nickname = findViewById(R.id.tv_in_restaurant_post_nickname);
        tv_in_restaurant_post_time = findViewById(R.id.tv_in_restaurant_post_time);
        tv_in_restaurant_post_restaurant_idx = findViewById(R.id.tv_in_restaurant_post_restaurant_idx);
        tv_in_restaurant_post_restaurant_name = findViewById(R.id.tv_in_restaurant_post_restaurant_name);
        tv_in_restaurant_post_menu_name = findViewById(R.id.tv_in_restaurant_post_menu_name);
        tv_in_restaurant_post_price = findViewById(R.id.tv_in_restaurant_post_price);
        tv_in_restaurant_post_content = findViewById(R.id.tv_in_restaurant_post_content);
        tv_in_restaurant_post_star = findViewById(R.id.tv_in_restaurant_post_star);
        tv_in_restaurant_post_comment_num = findViewById(R.id.tv_in_restaurant_post_comment_num);

        iv_in_restaurant_post_register_comment = findViewById(R.id.iv_in_restaurant_post_register_comment);
        et_in_restaurant_post_comment = findViewById(R.id.et_in_restaurant_post_comment);
    }

    public void setClickListenersToButtons() {
        iv_in_restaurant_post_register_comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(InRestaurantPostActivity.this) // TestActivity 부분에는 현재 Activity의 이름 입력.
                        .setTitle("댓글 작성 완료")
                        .setMessage("댓글을 등록하시겠습니까?")
                        .setPositiveButton("예", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {

                                // todo
                                // 서버에 댓글 등록 해야함!!

                                int restaurant_idx = Integer.parseInt(tv_in_restaurant_post_restaurant_idx.getText().toString());

                                tryPostRestaurantComment(restaurant_idx, et_in_restaurant_post_comment.getText().toString());

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
        });


    }

    public void tryGetSpecificRestaurantPost(int index_of_this_restaurant_post) {
        showProgressDialog();

        final InRestaurantPostService inRestaurantPostService = new InRestaurantPostService(this);
        inRestaurantPostService.getSpecificRestaurantPost(index_of_this_restaurant_post);
    }


    public void tryGetSpecificRestaurantComment(int index_of_this_restaurant_post) {
        showProgressDialog();

        final InRestaurantPostService inRestaurantPostService = new InRestaurantPostService(this);
        inRestaurantPostService.getSpecificRestaurantComment(index_of_this_restaurant_post);
    }

    private void tryPostRestaurantComment(int restaurant_idx, String comment) {
        showProgressDialog();

        HashMap<String, Object> params = new HashMap<>();
        params.put("comment_content", comment);

        final InRestaurantPostService inRestaurantPostService = new InRestaurantPostService(this, params);
        inRestaurantPostService.postRestaurantComment(restaurant_idx);
    }


    public void customOnClick(View view) {
        switch (view.getId()) {
            case R.id.btn_in_restaurant_post_go_back:
                onBackPressed();
                break;
            case R.id.btn_in_restaurant_post_more:
                showPopUp(view);
                break;
        }

    }

    public void showPopUp(View v) {
        PopupMenu popupMenu = new PopupMenu(this, v);

        popupMenu.setOnMenuItemClickListener(this);

        // 치언이가 서버 반영해주기 전까지 임시로 1로 설정
        is_mine = 1;

        if (is_mine == 1) {
            popupMenu.inflate(R.menu.menu_in_restaurant_post_mine);
            popupMenu.show();
        } else {
            popupMenu.inflate(R.menu.menu_in_restaurant_post_not_mine);
            popupMenu.show();
        }
    }

    public void saveCurrentRestaurantPostInfos() {
        SharedPreferences sharedPreferences = getSharedPreferences("currentRestaurantPostInfos", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("restaurant_idx", Integer.parseInt(tv_in_restaurant_post_restaurant_idx.getText().toString()));
        editor.putString("restaurant_name", tv_in_restaurant_post_restaurant_name.getText().toString());
        editor.putString("menu_name", tv_in_restaurant_post_menu_name.getText().toString());
        editor.putInt("price", Integer.parseInt(tv_in_restaurant_post_price.getText().toString()));
        editor.putFloat("stars", Float.parseFloat(tv_in_restaurant_post_star.getText().toString()));
        editor.putString("content", tv_in_restaurant_post_content.getText().toString());
        editor.apply();
    }

    @Override
    public boolean onMenuItemClick(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.menu_in_restaurant_post_fix_post:
                // todo
                // 일단 FixingRestaurantActivity로 넘긴 후에 거기서 글 수정 API 엮기

                saveCurrentRestaurantPostInfos();

//                int idx_of_restaurant_post_we_are_fixing = Integer.parseInt(tv_in_restaurant_post_restaurant_idx.getText().toString());

                Intent intent = new Intent(InRestaurantPostActivity.this, FixingRestaurantActivity.class);
//                intent.putExtra("idx_of_restaurant_post_we_are_fixing", idx_of_restaurant_post_we_are_fixing);


//                intent.putExtra("origin_title", tv_in_post_title.getText().toString());
//                intent.putExtra("origin_content", tv_in_post_content.getText().toString());
                startActivity(intent);

                finish();

                break;
            case R.id.menu_in_post_delete_post:
                // todo
                // 글 삭제 API 엮기

//                int idx_of_post_we_are_deleting = Integer.parseInt(tv_in_post_department_board_idx.getText().toString());
//
//                tryDeleteDepartmentPost(idx_of_post_we_are_deleting);

                break;
            case R.id.menu_in_post_send_message:
                // todo
                // 추후에 쪽지보내기 기능 생겼을 때 만들기
                break;
        }


        return false;
    }


    @Override
    public void validateSuccess(String text) {

    }

    @Override
    public void validateFailure(String message) {

    }

    @Override
    public void getRestaurantPostSuccess(RestaurantResponse restaurantResponse) {
        hideProgressDialog();

        switch(restaurantResponse.getCode()){
            default:

                // Image 어떻게 넣을 것인지 생각
//                iv_in_restaurant_post_photo.setImageResource();

                tv_in_restaurant_post_nickname.setText(restaurantResponse.getRestaurantResult().getNickname());
                tv_in_restaurant_post_time.setText(restaurantResponse.getRestaurantResult().getTime());
                tv_in_restaurant_post_restaurant_idx.setText(Integer.toString(restaurantResponse.getRestaurantResult().getTastehouse_idx()));
                tv_in_restaurant_post_restaurant_name.setText(restaurantResponse.getRestaurantResult().getTastehouse_name());
                tv_in_restaurant_post_menu_name.setText(restaurantResponse.getRestaurantResult().getMenu_name());
                tv_in_restaurant_post_price.setText(Integer.toString(restaurantResponse.getRestaurantResult().getMenu_price()));
                tv_in_restaurant_post_content.setText(restaurantResponse.getRestaurantResult().getTastehouse_content());
                tv_in_restaurant_post_star.setText(Float.toString(restaurantResponse.getRestaurantResult().getTastehouse_star()));
                tv_in_restaurant_post_comment_num.setText(Integer.toString(restaurantResponse.getRestaurantResult().getComment_num()));

                if (restaurantResponse.getRestaurantResult().getIs_mine() == 1) {
                    is_mine = 1;
                } else {
                    is_mine = 0;
                }

                break;

        }

    }


    @Override
    public void getRestaurantCommentSuccess(InRestaurantPostCommentResponse inRestaurantPostCommentResponse) {
        hideProgressDialog();

        switch (inRestaurantPostCommentResponse.getCode()) {
            case 100:
                /**
                 * CommentItem 형식의 ArrayList에 모두 넣어두고 어댑터를 이용해서 하나하나 레이아웃에 갖다 붙이자!!
                 * */

                int num_of_comments_in_the_restaurant_post = inRestaurantPostCommentResponse.getInRestaurantPostCommentResults().size();

                for (int i = 0; i < num_of_comments_in_the_restaurant_post; i++) {
                    RestaurantCommentItem restaurantCommentItem = new RestaurantCommentItem();

                    restaurantCommentItem.setComment_content(inRestaurantPostCommentResponse.getInRestaurantPostCommentResults().get(i).getComment_content());
                    restaurantCommentItem.setTime(inRestaurantPostCommentResponse.getInRestaurantPostCommentResults().get(i).getTime());
                    restaurantCommentItem.setNickname(inRestaurantPostCommentResponse.getInRestaurantPostCommentResults().get(i).getNickname());
                    restaurantCommentItem.setComment_idx(inRestaurantPostCommentResponse.getInRestaurantPostCommentResults().get(i).getComment_idx());

                    m_restaurant_comment_item_list.add(restaurantCommentItem);
                }
                comment_adapter.notifyDataSetChanged();

                break;
            default:
                break;
        }
    }

    @Override
    public void postRestaurantCommentSuccess(DefaultResponse defaultResponse) {
        hideProgressDialog();

        switch (defaultResponse.getCode()) {
            default:
                System.out.println(defaultResponse.getMessage());
                break;
        }
    }

    @Override
    public void deleteRestaurantCommentSuccess(DefaultResponse defaultResponse) {
        // RestaurantCommentAdapter에서 처리함
    }
}
