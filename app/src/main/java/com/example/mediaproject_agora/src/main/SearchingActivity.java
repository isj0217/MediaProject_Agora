package com.example.mediaproject_agora.src.main;

import android.app.Activity;
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

public class SearchingActivity extends BaseActivity implements DepartmentBoardActivityView{

    private ArrayList<DepartmentPostItem> m_filtered_item_list;
    private DepartmentBoardAdapter filtered_post_adapter;
    private RecyclerView rv_filtered_posts_list;
    private LinearLayoutManager linear_layout_manager;

    private String department_name;

    private LinearLayout ll_searching_description;
    private LinearLayout ll_searching_not_found;

    private TextView tv_searching_department_name;
    private EditText et_searching_text;
    private ImageView iv_searching_search;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searching);

        bindViews();

        setClickListenersToViews();

        rv_filtered_posts_list = findViewById(R.id.rv_filtered_posts_list);

        linear_layout_manager = new LinearLayoutManager(getApplicationContext());
        rv_filtered_posts_list.setLayoutManager(linear_layout_manager);

        m_filtered_item_list = new ArrayList<>();
        filtered_post_adapter = new DepartmentBoardAdapter(m_filtered_item_list);
        rv_filtered_posts_list.setAdapter(filtered_post_adapter);




        department_name = getIntent().getExtras().getString("department_name");

        tv_searching_department_name.setText(department_name);

    }

    public void bindViews() {
        tv_searching_department_name = findViewById(R.id.tv_searching_department_name);
        et_searching_text = findViewById(R.id.et_searching_text);
        iv_searching_search = findViewById(R.id.iv_searching_search);

        ll_searching_description = findViewById(R.id.ll_searching_description);
        ll_searching_not_found = findViewById(R.id.ll_searching_not_found);
    }

    public void setClickListenersToViews() {
        iv_searching_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                m_filtered_item_list.clear();

                if (et_searching_text.length() != 0) {
                    tryGetFilteredDepartmentPost(department_name, et_searching_text.getText().toString());
                }
            }
        });
    }

    private void tryGetFilteredDepartmentPost(String department_name, String text) {
        showProgressDialog();

//        HashMap<String, Object> params = new HashMap<>();
//        params.put("department_name", department_name);

        final DepartmentBoardService departmentBoardService = new DepartmentBoardService(this);
        departmentBoardService.getFilteredDepartmentPost(department_name, text);
    }

    @Override
    public void validateSuccess(String text) {

    }

    @Override
    public void validateFailure(String message) {

    }

    @Override
    public void getSpecificDepartmentBoardSuccess(DepartmentBoardResponse departmentBoardResponse) {

    }

    @Override
    public void getFilteredDepartmentPostSuccess(DepartmentBoardResponse departmentBoardResponse) {
        hideProgressDialog();

        switch (departmentBoardResponse.getCode()) {

            case 100:

                ll_searching_description.setVisibility(View.GONE);
                ll_searching_not_found.setVisibility(View.GONE);

                /**
                 * PostItem 형식의 ArrayList에 모두 넣어두고 어댑터를 이용해서 하나하나 레이아웃에 갖다 붙이자!!
                 * */

                int num_of_filtered_posts = departmentBoardResponse.getDepartmentBoardResults().size();

                for (int i = 0; i < num_of_filtered_posts; i++){
                    DepartmentPostItem departmentPostItem = new DepartmentPostItem();

                    departmentPostItem.setDepartment_board_idx(departmentBoardResponse.getDepartmentBoardResults().get(i).getDepartment_board_idx());
                    departmentPostItem.setTitle(departmentBoardResponse.getDepartmentBoardResults().get(i).getTitle());
                    departmentPostItem.setContent(departmentBoardResponse.getDepartmentBoardResults().get(i).getContent());
                    departmentPostItem.setNickname(departmentBoardResponse.getDepartmentBoardResults().get(i).getNickname());
                    departmentPostItem.setTime(departmentBoardResponse.getDepartmentBoardResults().get(i).getTime());
                    departmentPostItem.setPhoto_status(departmentBoardResponse.getDepartmentBoardResults().get(i).getPhoto_status());
                    departmentPostItem.setLike_num(departmentBoardResponse.getDepartmentBoardResults().get(i).getLike_num());
                    departmentPostItem.setComment_num(departmentBoardResponse.getDepartmentBoardResults().get(i).getComment_num());

                    m_filtered_item_list.add(departmentPostItem);
                }
                filtered_post_adapter.notifyDataSetChanged();

                break;

            default:
                ll_searching_description.setVisibility(View.GONE);
                ll_searching_not_found.setVisibility(View.VISIBLE);
                break;
        }


    }
}

