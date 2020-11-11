package com.example.mediaproject_agora.src.sign_in;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.mediaproject_agora.R;
import com.example.mediaproject_agora.src.BaseActivity;
import com.example.mediaproject_agora.src.main.MainActivity;
import com.example.mediaproject_agora.src.sign_in.interfaces.SignInActivityView;
import com.example.mediaproject_agora.src.sign_up.SignUpActivity;
import com.nhn.android.naverlogin.OAuthLogin;
import com.nhn.android.naverlogin.OAuthLoginHandler;
import com.nhn.android.naverlogin.ui.view.OAuthLoginButton;

public class SignInActivity extends BaseActivity implements SignInActivityView {

    private static String OAUTH_CLIENT_ID = "tmRiOHk1L6UG_iO2Io_B";
    private static String OAUTH_CLIENT_SECRET = "h722klNl_l";
    private static String OAUTH_CLIENT_NAME = "아고라";

    public static OAuthLoginButton mOAthLoginButton;
    public static OAuthLogin mOAthLoginInstance;

    public static Context mContext;

    private long mBackKeyPressedTime = 0;
    private Toast mToast;

    private EditText et_sign_in_id;
    private EditText et_sign_in_pw;
    private Button btn_sign_in_sign_in;
    //    private Button btn_sign_in_find_id_or_pw;
    private Button btn_sign_in_sign_up;

    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        // context 저장
        mContext = SignInActivity.this;

        // 1. 초기화
        mOAthLoginInstance = OAuthLogin.getInstance();
        mOAthLoginInstance.showDevelopersLog(true);
        mOAthLoginInstance.init(mContext, OAUTH_CLIENT_ID, OAUTH_CLIENT_SECRET, OAUTH_CLIENT_NAME);

        // 2. 로그인 버튼 세팅
        mOAthLoginButton = (OAuthLoginButton) findViewById(R.id.buttonOAuthLoginImg);
        mOAthLoginButton.setOAuthLoginHandler(mOAthLoginHandler);

//        bindViews();

//        btn_sign_in_sign_in.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                // todo before server connection
//                // 1. 아이디랑 비밀번호 EditText를 읽어와서 비어있지만 않다면 그냥 MainActivity로 이동
//
//                if (et_sign_in_id.getText().toString().equals("") && et_sign_in_pw.getText().toString().equals("")){
//                    Toast.makeText(SignInActivity.this, "아이디와 비밀번호를 입력하세요", Toast.LENGTH_SHORT).show();
//                } else if (et_sign_in_id.getText().toString().equals("")){
//                    Toast.makeText(SignInActivity.this, "아이디를 입력하세요", Toast.LENGTH_SHORT).show();
//                } else if (et_sign_in_pw.getText().toString().equals("")){
//                    Toast.makeText(SignInActivity.this, "비밀번호를 입력하세요", Toast.LENGTH_SHORT).show();
//                } else{
//                    intent = new Intent(SignInActivity.this, MainActivity.class);
//                    startActivity(intent);
//                    overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
//                    finish();
//                }
//
//
//                // todo after server connection
//                // 1. 사용자가 입력한 아이디랑 비밀번호를 edittext에서 읽어와서 서버로 두 정보를 보낸 후에
//                // 2. 올바르다는 응답이 오면 로그인 성공(MainActivity로 이동)
//                // 3. 틀리다는 응답이 오면 로그인 실패(팝업창 띄우기)
//            }
//        });

//        btn_sign_in_sign_up.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                intent = new Intent(SignInActivity.this, SignUpActivity.class);
//                startActivity(intent);
//                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
//                finish();
//            }
//        });

    }

    private OAuthLoginHandler mOAthLoginHandler = new OAuthLoginHandler() {
        @Override
        public void run(boolean success) {
            // 로그인 인증 성공
            if (success) {
                // 사용자 정보 가져오기
                String accessToken = mOAthLoginInstance.getAccessToken(mContext);
                String refreshToken = mOAthLoginInstance.getRefreshToken(mContext);
                long expriresAt = mOAthLoginInstance.getExpiresAt(mContext);
                String tokenType = mOAthLoginInstance.getTokenType(mContext);

                // todo
                // 회원가입이 되어있는 사람인지 확인해서,
                // 가입이 되어있지 않다면 회원가입 액티비티로 보내고, 가입이 되어있다면 메인 액티비티로 보낸다.

                saveAccessTokenToSharedPreferences(accessToken);

                System.out.println(accessToken);
                System.out.println(expriresAt);

                redirectToNextActivity(checkAlreadyMember());

            } else {
                String errorCode = mOAthLoginInstance.getLastErrorCode(mContext).getCode();
                String errorDesc = mOAthLoginInstance.getLastErrorDesc(mContext);
                Toast.makeText(mContext, "errorCode:" + errorCode + ", errorDesc:" + errorDesc, Toast.LENGTH_SHORT).show();
            }
        }
    };

    public boolean checkAlreadyMember(){
        // todo
        // 우리 DB에 가입되어있는지 여부 확인
        // 우리 DB에 가입되어 있다면 return true;
        // 우리 DB에 가입되지 않은 신규회원이라면 return false;

        return true;
    }

    public void saveAccessTokenToSharedPreferences(String token){
        SharedPreferences sharedPreferences = getSharedPreferences("naverAccessToken", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("naverAccessToken", token);
        editor.apply();
    }

    protected void redirectToNextActivity(boolean isMember) {

        if (isMember) {
            // todo
            // 회원이므로 해당 회원의 정보 받아옴

            final Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        } else {
            final Intent intent = new Intent(this, SignUpActivity.class);
            startActivity(intent);
            finish();
        }
    }



    @Override
    public void onBackPressed() {

        if(System.currentTimeMillis() > mBackKeyPressedTime + 2000) {
            mBackKeyPressedTime = System.currentTimeMillis();
            mToast = Toast.makeText(this, "\'뒤로\' 버튼을 한번 더 누르시면 종료됩니다.", Toast.LENGTH_SHORT);
            mToast.show();
        }
        else if(System.currentTimeMillis() <= mBackKeyPressedTime + 2000) {

            finish();
            mToast.cancel();
        }

    }









    // 이 밑으로는 템플릿

    private void tryGetTest() {
        showProgressDialog();

        final SignInService signInService = new SignInService(this);
        signInService.getTest();
    }

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

    public void customOnClick(View view) {
        switch (view.getId()) {
            case R.id.main_btn_hello_world:
                tryGetTest();
                break;
            default:
                break;
        }
    }
}
