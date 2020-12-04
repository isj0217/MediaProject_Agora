package com.example.mediaproject_agora.src.main;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
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
import com.example.mediaproject_agora.src.main.items.DepartmentPostItem;
import com.example.mediaproject_agora.src.main.models.DepartmentBoardResponse;

import java.util.ArrayList;
import java.util.HashMap;

public class DepartmentBoardActivity extends BaseActivity implements DepartmentBoardActivityView, PopupMenu.OnMenuItemClickListener{

    private ArrayList<DepartmentPostItem> m_post_item_list;
    private DepartmentBoardAdapter department_board_adapter;
    private RecyclerView rv_department_board;
    private LinearLayoutManager linear_layout_manager;

    private Intent intent;

    private String section_in_agora;
    private String category_name;

    private String department_name;
    private String used_product_category_name;

    private TextView tv_specific_board_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_specific_board);

        tv_specific_board_name = findViewById(R.id.tv_specific_board_name);
        rv_department_board = findViewById(R.id.rv_specific_board_post_list);

        linear_layout_manager = new LinearLayoutManager(getApplicationContext());
        rv_department_board.setLayoutManager(linear_layout_manager);

        m_post_item_list = new ArrayList<>();
        department_board_adapter = new DepartmentBoardAdapter(m_post_item_list);
        rv_department_board.setAdapter(department_board_adapter);



//        Bundle bundle = getIntent().getExtras();
//        if (bundle != null) {
//            section_in_agora = bundle.getString("section_in_agora");
//            category_name = bundle.getString("category_name");
//            tv_specific_board_name.setText(category_name);
//        }

        loadRecentSectionAndCategory();

        tv_specific_board_name.setText(category_name);

        switch (section_in_agora){
            case "department":
                department_name = category_name;
                tryGetSpecificDepartmentBoard(department_name);
                break;

            case "used_product":
                break;
        }


//        SharedPreferences sharedPreferences = getSharedPreferences("recent_section_and_category", MODE_PRIVATE);
//        section_in_agora = sharedPreferences.getString("section_in_agora", "SECTION");
//        category_name = sharedPreferences.getString("category_name", "CATEGORY");

    }

    private void tryGetSpecificDepartmentBoard(String department_name) {
        showProgressDialog();

        HashMap<String, Object> params = new HashMap<>();
        params.put("department_name", department_name);

        final DepartmentBoardService departmentBoardService = new DepartmentBoardService(this, params);
        departmentBoardService.getSpecificDepartmentBoard(department_name);
    }

    private void restartActivity(Activity activity) {
        Intent intent = new Intent();
        intent.setClass(activity, activity.getClass());
        activity.startActivity(intent);
        activity.finish();
    }

    public void customOnClick(View view) {
        switch (view.getId()) {
            case R.id.iv_specific_board_go_back:
                onBackPressed();
                break;
            case R.id.iv_specific_board_sync:
                restartActivity(DepartmentBoardActivity.this);
                break;
            case R.id.iv_specific_board_search:
                intent = new Intent(DepartmentBoardActivity.this, SearchingActivity.class);
                intent.putExtra("department_name", department_name);
                startActivity(intent);
                break;
            case R.id.iv_specific_board_write:

                loadRecentSectionAndCategory();

                switch (section_in_agora){
                    case "department":
                        intent = new Intent(DepartmentBoardActivity.this, WritingDepartmentActivity.class);
                        startActivity(intent);
                        finish();
                        break;

                    case "used_product":
                        intent = new Intent(DepartmentBoardActivity.this, WritingUsedProductDepartmentActivity.class);
                        startActivity(intent);
                        finish();
                        break;

                }
                break;
        }

    }

    public void loadRecentSectionAndCategory(){
        SharedPreferences sharedPreferences = getSharedPreferences("recent_section_and_category", MODE_PRIVATE);
        section_in_agora = sharedPreferences.getString("section_in_agora", "SECTION 불러오기 실패");
        category_name = sharedPreferences.getString("category_name", "CATEGORY 불러오기 실패");
    }


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
    public void getSpecificDepartmentBoardSuccess(DepartmentBoardResponse departmentBoardResponse) {
        hideProgressDialog();

        switch (departmentBoardResponse.getCode()) {

            case 100:

                /**
                 * PostItem 형식의 ArrayList에 모두 넣어두고 어댑터를 이용해서 하나하나 레이아웃에 갖다 붙이자!!
                 * */

                int num_of_posts_in_department_board = departmentBoardResponse.getDepartmentBoardResults().size();

//                System.out.println("몇개??? " + num_of_posts_in_department_board);

                for (int i = 0; i < num_of_posts_in_department_board; i++){
                    DepartmentPostItem departmentPostItem = new DepartmentPostItem();

                    departmentPostItem.setDepartment_board_idx(departmentBoardResponse.getDepartmentBoardResults().get(i).getDepartment_board_idx());
                    departmentPostItem.setTitle(departmentBoardResponse.getDepartmentBoardResults().get(i).getTitle());
                    departmentPostItem.setContent(departmentBoardResponse.getDepartmentBoardResults().get(i).getContent());
                    departmentPostItem.setNickname(departmentBoardResponse.getDepartmentBoardResults().get(i).getNickname());
                    departmentPostItem.setTime(departmentBoardResponse.getDepartmentBoardResults().get(i).getTime());
                    departmentPostItem.setPhoto_status(departmentBoardResponse.getDepartmentBoardResults().get(i).getPhoto_status());
                    departmentPostItem.setLike_num(departmentBoardResponse.getDepartmentBoardResults().get(i).getLike_num());
                    departmentPostItem.setComment_num(departmentBoardResponse.getDepartmentBoardResults().get(i).getComment_num());

                    m_post_item_list.add(departmentPostItem);
                }
                department_board_adapter.notifyDataSetChanged();

                break;

            default:
                Toast.makeText(this, departmentBoardResponse.getMessage(), Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    public void getFilteredDepartmentPostSuccess(DepartmentBoardResponse departmentBoardResponse) {

    }


    @Override
    public boolean onMenuItemClick(MenuItem menuItem) {
        return false;
    }
}
