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
import com.example.mediaproject_agora.src.main.fragments.fragment_restaurant.RestaurantResponse;
import com.example.mediaproject_agora.src.main.interfaces.InPostActivityView;
import com.example.mediaproject_agora.src.main.interfaces.InRestaurantPostActivityView;
import com.example.mediaproject_agora.src.main.items.CommentItem;
import com.example.mediaproject_agora.src.main.models.CommentAdapter;
import com.example.mediaproject_agora.src.main.models.DefaultResponse;
import com.example.mediaproject_agora.src.main.models.InPostCommentResponse;
import com.example.mediaproject_agora.src.main.models.InPostPostResponse;

import java.util.ArrayList;
import java.util.HashMap;


public class InRestaurantPostActivity extends BaseActivity implements InRestaurantPostActivityView, PopupMenu.OnMenuItemClickListener {


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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_in_restaurant_post);

        bindViews();

        index_of_this_restaurant_post = getIntent().getExtras().getInt("index_of_this_restaurant_post", 0);

        tryGetSpecificRestaurantPost(index_of_this_restaurant_post);

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
    }

    public void tryGetSpecificRestaurantPost(int index_of_this_restaurant_post) {
        final InRestaurantPostService inRestaurantPostService = new InRestaurantPostService(this);
        inRestaurantPostService.getSpecificRestaurantPost(index_of_this_restaurant_post);
    }

    @Override
    public boolean onMenuItemClick(MenuItem menuItem) {
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

                break;

        }

    }
}
