package com.example.mediaproject_agora.src.sign_up;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;

import com.example.mediaproject_agora.R;
import com.example.mediaproject_agora.src.BaseActivity;
import com.example.mediaproject_agora.src.main.MainActivity;
import com.example.mediaproject_agora.src.sign_in.SignInActivity;
import com.example.mediaproject_agora.src.sign_up.interfaces.SignUpActivityView;
import com.example.mediaproject_agora.src.sign_up.models.SignUpResponse;

import java.util.HashMap;

import static com.example.mediaproject_agora.src.ApplicationClass.X_ACCESS_TOKEN;
import static com.example.mediaproject_agora.src.ApplicationClass.sSharedPreferences;

public class SignUpActivity extends BaseActivity implements SignUpActivityView {

    private int server_id;
    private String server_name;

    private EditText et_sign_up_nickname;
    private EditText et_sign_up_department;
    private EditText et_sign_up_student_id;
    private Button btn_sign_up_sign_up;

    private ImageView iv_sign_up_check_nickname;
    private ImageView iv_sign_up_check_department;
    private ImageView iv_sign_up_check_student_id;


    private boolean is_suitable_nickname;
    private boolean is_suitable_department;
    private boolean is_suitable_student_id;

    private Intent intent;

    public void bindViews() {

        et_sign_up_nickname = findViewById(R.id.et_sign_up_nickname);
        et_sign_up_department = findViewById(R.id.et_sign_up_department);
        et_sign_up_student_id = findViewById(R.id.et_sign_up_student_id);

        btn_sign_up_sign_up = findViewById(R.id.btn_sign_up_sign_up);

        iv_sign_up_check_nickname = findViewById(R.id.iv_sign_up_check_nickname);
        iv_sign_up_check_department = findViewById(R.id.iv_sign_up_check_department);
        iv_sign_up_check_student_id = findViewById(R.id.iv_sign_up_check_student_id);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        bindViews();
        setTextChangedListeners();

    }

    public void setTextChangedListeners() {

        et_sign_up_nickname.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (editable.length() >= 2) {
                    iv_sign_up_check_nickname.setImageResource(R.drawable.ic_baseline_check_circle_outline_24_green);
                    is_suitable_nickname = true;
                } else {
                    iv_sign_up_check_nickname.setImageResource(R.drawable.ic_baseline_check_circle_outline_24_red);
                    is_suitable_nickname = false;
                }
            }
        });

        et_sign_up_department.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (editable.length() >= 4) {
                    iv_sign_up_check_department.setImageResource(R.drawable.ic_baseline_check_circle_outline_24_green);
                    is_suitable_department = true;
                } else {
                    iv_sign_up_check_department.setImageResource(R.drawable.ic_baseline_check_circle_outline_24_red);
                    is_suitable_department = false;
                }
            }
        });

        et_sign_up_student_id.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (editable.length() >= 8 && editable.length() <= 9) {
                    iv_sign_up_check_student_id.setImageResource(R.drawable.ic_baseline_check_circle_outline_24_green);
                    is_suitable_student_id = true;
                } else {
                    iv_sign_up_check_student_id.setImageResource(R.drawable.ic_baseline_check_circle_outline_24_red);
                    is_suitable_student_id = false;
                }
            }
        });
    }

    public void customOnClick(View view) {
        switch (view.getId()) {
            case R.id.iv_sign_up_go_back:
                intent = new Intent(SignUpActivity.this, SignInActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                finish();
                break;

            case R.id.btn_sign_up_sign_up:

                SharedPreferences sharedPreferences = getSharedPreferences("naverAccessToken", MODE_PRIVATE);
                String at = sharedPreferences.getString("naverAccessToken", "Error Occurred");
                System.out.println("at= " + at);

                if (is_suitable_department &&
                        is_suitable_department &&
                        is_suitable_student_id) {
                    // todo before server connection
                    // 모든 입력창을 적절하게 채워넣었으므로
                    // 1. 회원가입이 완료되었다는 알림창 띄우고
                    // 2. 확인버튼 누르면 SignInActivity로 돌려보내기


                    // todo after server connection
                    // 서버 연결 후에는 서버로부터 잘못된 정보 없는지 응답 받은 후에 그에 상응하게 조치하기!!
                    // ex) 아이디 중복, 닉네임 중복 등 -> 회원정보 수정 요구

                    loadServerIdAndServerName();

                    tryPostSignUp(server_id, server_name,
                            et_sign_up_nickname.getText().toString(),
                            et_sign_up_department.getText().toString(),
                            Integer.parseInt(et_sign_up_student_id.getText().toString()));

                    new AlertDialog.Builder(SignUpActivity.this) // TestActivity 부분에는 현재 Activity의 이름 입력.
                            .setTitle("회원가입 완료")
                            .setMessage("아고라 가입을 축하합니다")
                            .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    intent = new Intent(SignUpActivity.this, SignInActivity.class);
                                    startActivity(intent);
                                    overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                                    finish();
                                }
                            }).setCancelable(false)
                            .show();


                } else {
                    Toast.makeText(this, "모든 양식을 적절하게 작성해주세요", Toast.LENGTH_SHORT).show();
                }
        }
    }

    public void loadServerIdAndServerName() {
        SharedPreferences sharedPreferences = getSharedPreferences("server_id_and_server_name", MODE_PRIVATE);
        server_id = sharedPreferences.getInt("server_id", 0);
        server_name = sharedPreferences.getString("server_name", "server_name 불러오기 실패");
    }

    private void tryPostSignUp(int server_id, String server_name, String nickname, String department, int student_id) {

        showProgressDialog();

        HashMap<String, Object> params = new HashMap<>();
        params.put("server_id", server_id);
        params.put("server_name", server_name);
        params.put("nickname", nickname);
        params.put("department_name", department);
        params.put("student_id", student_id);

        final SignUpService signUpService = new SignUpService(this, params);
        signUpService.postSignUp();
    }

    @Override
    public void signUpSuccess(SignUpResponse signUpResponse) {
        hideProgressDialog();


        switch (signUpResponse.getCode()) {

            case 100:
                showCustomToast("회원가입이 완료되었습니다");

                intent = new Intent(SignUpActivity.this, SignInActivity.class);
                startActivity(intent);
                finish();
                break;

            default:
                showCustomToast(signUpResponse.getMessage());
                break;
        }
    }

    @Override
    public void onBackPressed() {
        intent = new Intent(SignUpActivity.this, SignInActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        finish();
    }


    // 이 밑으로는 템플릿

    //    private void tryGetTest() {
//        showProgressDialog();
//
//        final SignInService signInService = new SignInService(this);
//        signInService.getTest();
//    }
//
    @Override
    public void validateSuccess(String text) {
        hideProgressDialog();
    }

    @Override
    public void validateFailure(@Nullable String message) {
        hideProgressDialog();
        showCustomToast(message == null || message.isEmpty() ? getString(R.string.network_error) : message);
    }

//    public void customOnClick(View view) {
//        switch (view.getId()) {
//            case R.id.main_btn_hello_world:
//                tryGetTest();
//                break;
//            default:
//                break;
//        }
//    }
}
