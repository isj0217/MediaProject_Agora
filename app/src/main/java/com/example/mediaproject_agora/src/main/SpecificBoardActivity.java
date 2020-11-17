package com.example.mediaproject_agora.src.main;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;

import com.example.mediaproject_agora.R;
import com.example.mediaproject_agora.src.BaseActivity;

import com.example.mediaproject_agora.src.main.interfaces.SpecificBoardActivityView;
import com.example.mediaproject_agora.src.main.models.SpecificBoardResponse;
import com.example.mediaproject_agora.src.sign_in.SignInActivity;
import com.example.mediaproject_agora.src.sign_up.SignUpActivity;

import java.util.HashMap;

import static com.example.mediaproject_agora.src.ApplicationClass.X_ACCESS_TOKEN;
import static com.example.mediaproject_agora.src.ApplicationClass.sSharedPreferences;

public class SpecificBoardActivity extends BaseActivity implements SpecificBoardActivityView {

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

        final SpecificBoardService specificBoardService = new SpecificBoardService(this, params);
        specificBoardService.getSpecificDepartmentBoard(department_name);
    }

    public void customOnClick(View view) {
        switch (view.getId()) {
            case R.id.iv_specific_board_go_back:
                onBackPressed();
                break;
            case R.id.iv_specific_board_sync:
                break;
            case R.id.iv_specific_board_search:
                break;
            case R.id.iv_specific_board_write:

                loadRecentSectionAndCategory();

                switch (section_in_agora){
                    case "department":
                        intent = new Intent(SpecificBoardActivity.this, WritingDepartmentActivity.class);
                        startActivity(intent);
                        finish();
                        break;

                    case "used_product":
                        intent = new Intent(SpecificBoardActivity.this, WritingUsedProductActivity.class);
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
    public void getSpecificDepartmentBoardSuccess(SpecificBoardResponse specificBoardResponse) {
        hideProgressDialog();

        switch (specificBoardResponse.getCode()) {

            case 100:
                showCustomToast("일단 여기까지는 성공!!!!");

                break;

            // 우리 DB에 회원가입이 안되어있는 경우에는 자체회원가입 시키기
            case 201:
                showCustomToast(specificBoardResponse.getMessage());
                break;

            case 202:
                showCustomToast(specificBoardResponse.getMessage());
                break;

            default:
                showCustomToast("SpecificBoard의 default response입니다");
                break;
        }
    }


}
