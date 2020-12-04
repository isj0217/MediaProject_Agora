package com.example.mediaproject_agora.src.main;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mediaproject_agora.R;
import com.example.mediaproject_agora.src.BaseActivity;
import com.example.mediaproject_agora.src.main.fragments.fragment_agora.DepartmentBoardAdapter;
import com.example.mediaproject_agora.src.main.interfaces.DepartmentBoardActivityView;
import com.example.mediaproject_agora.src.main.interfaces.LikePostsActivityView;
import com.example.mediaproject_agora.src.main.items.DepartmentPostItem;
import com.example.mediaproject_agora.src.main.items.LikePostItem;
import com.example.mediaproject_agora.src.main.models.DepartmentBoardResponse;
import com.example.mediaproject_agora.src.main.models.LikePostResponse;

import java.util.ArrayList;
import java.util.HashMap;

import static android.view.View.GONE;

public class LikePostsActivity extends BaseActivity implements LikePostsActivityView, PopupMenu.OnMenuItemClickListener{

    private ArrayList<LikePostItem> m_like_post_item_list;
    private LikePostsAdapter like_post_adapter;
    private RecyclerView rv_like_posts;
    private LinearLayoutManager linear_layout_manager;

    private Intent intent;

    private LinearLayout ll_like_posts_empty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_like_posts);

        ll_like_posts_empty = findViewById(R.id.ll_like_posts_empty);

        rv_like_posts = findViewById(R.id.rv_like_posts);

        linear_layout_manager = new LinearLayoutManager(getApplicationContext());
        rv_like_posts.setLayoutManager(linear_layout_manager);

        m_like_post_item_list = new ArrayList<>();
        like_post_adapter = new LikePostsAdapter(m_like_post_item_list);
        rv_like_posts.setAdapter(like_post_adapter);

        saveRecentSectionAndCategory("my_page", "my_page");

        tryGetLikePosts();


    }

    public void saveRecentSectionAndCategory(String section_in_agora, String category_name) {
        SharedPreferences sharedPreferences = getSharedPreferences("recent_section_and_category", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("section_in_agora", section_in_agora);
        editor.putString("category_name", category_name);
        editor.apply();
    }

    private void tryGetLikePosts() {
        showProgressDialog();

//        HashMap<String, Object> params = new HashMap<>();
//        params.put("department_name", department_name);

        final LikePostsService likePostsService = new LikePostsService(this);
        likePostsService.getLikePosts();
    }

    private void restartActivity(Activity activity) {
        Intent intent = new Intent();
        intent.setClass(activity, activity.getClass());
        activity.startActivity(intent);
        activity.finish();
    }

//    public void customOnClick(View view) {
//        switch (view.getId()) {
//            case R.id.iv_specific_board_go_back:
//                onBackPressed();
//                break;
//            case R.id.iv_specific_board_sync:
//                restartActivity(LikePostsActivity.this);
//                break;
//            case R.id.iv_specific_board_search:
//                break;
//            case R.id.iv_specific_board_write:
//
//                loadRecentSectionAndCategory();
//
//                switch (section_in_agora){
//                    case "department":
//                        intent = new Intent(LikePostsActivity.this, WritingDepartmentActivity.class);
//                        startActivity(intent);
//                        finish();
//                        break;
//
//                    case "used_product":
//                        intent = new Intent(LikePostsActivity.this, WritingUsedProductDepartmentActivity.class);
//                        startActivity(intent);
//                        finish();
//                        break;
//
//                }
//                break;
//        }
//
//    }

//    public void loadRecentSectionAndCategory(){
//        SharedPreferences sharedPreferences = getSharedPreferences("recent_section_and_category", MODE_PRIVATE);
//        section_in_agora = sharedPreferences.getString("section_in_agora", "SECTION 불러오기 실패");
//        category_name = sharedPreferences.getString("category_name", "CATEGORY 불러오기 실패");
//    }


    // 이 밑으로는 템플릿

//    private void tryGetTest() {
//        showProgressDialog();
//
//        final MainService mainService = new MainService(this);
//        mainService.getTest();
//    }

    @Override
    public void validateSuccess(String text) {
        hideProgressDialog();
//        mTvHelloWorld.setText(text);
    }

    @Override
    public void validateFailure(@Nullable String message) {
        hideProgressDialog();
        showCustomToast(message == null || message.isEmpty() ? getString(R.string.network_error) : message);
    }

    @Override
    public void getLikePostsSuccess(LikePostResponse likePostResponse) {
        hideProgressDialog();

        System.out.println(likePostResponse.getMessage());

        switch (likePostResponse.getCode()) {

            case 100:

                /**
                 * LikePostItem 형식의 ArrayList에 모두 넣어두고 어댑터를 이용해서 하나하나 레이아웃에 갖다 붙이자!!
                 * */

                int num_of_like_posts = likePostResponse.getLikePostResults().size();

                if (num_of_like_posts > 0) {

                    ll_like_posts_empty.setVisibility(GONE);

                    for (int i = 0; i < num_of_like_posts; i++) {
                        LikePostItem likePostItem = new LikePostItem();

                        likePostItem.setDepartment_board_idx(likePostResponse.getLikePostResults().get(i).getDepartment_board_idx());
                        likePostItem.setType(likePostResponse.getLikePostResults().get(i).getType());
                        likePostItem.setTitle(likePostResponse.getLikePostResults().get(i).getTitle());
                        likePostItem.setContent(likePostResponse.getLikePostResults().get(i).getContent());
                        likePostItem.setNickname(likePostResponse.getLikePostResults().get(i).getNickname());
                        likePostItem.setTime(likePostResponse.getLikePostResults().get(i).getTime());
                        likePostItem.setPhoto_status(likePostResponse.getLikePostResults().get(i).getPhoto_status());
                        likePostItem.setLike_num(likePostResponse.getLikePostResults().get(i).getLike_num());
                        likePostItem.setComment_num(likePostResponse.getLikePostResults().get(i).getComment_num());

                        m_like_post_item_list.add(likePostItem);
                    }
                    like_post_adapter.notifyDataSetChanged();
                }

                break;

            default:
//                Toast.makeText(this, likePostResponse.getMessage(), Toast.LENGTH_SHORT).show();
                break;
        }
    }



    @Override
    public boolean onMenuItemClick(MenuItem menuItem) {
        return false;
    }
}
