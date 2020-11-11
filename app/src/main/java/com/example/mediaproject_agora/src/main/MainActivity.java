package com.example.mediaproject_agora.src.main;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.mediaproject_agora.R;
import com.example.mediaproject_agora.src.BaseActivity;
import com.example.mediaproject_agora.src.main.fragments.fragment_agora.FragmentAgora;
import com.example.mediaproject_agora.src.main.fragments.fragment_chat.FragmentChat;
import com.example.mediaproject_agora.src.main.fragments.fragment_home.FragmentHome;
import com.example.mediaproject_agora.src.main.interfaces.MainActivityView;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends BaseActivity implements MainActivityView {

    private long mBackKeyPressedTime = 0;
    private Toast mToast;

    BottomNavigationView bottom_navigation_view_main;

    FragmentHome fragmentHome;
    FragmentAgora fragmentAgora;
    FragmentChat fragmentChat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottom_navigation_view_main = findViewById(R.id.bottom_navigation_view_main);

        // 4개의 프래그먼트 생성
        fragmentHome = new FragmentHome();
        fragmentAgora = new FragmentAgora();
        fragmentChat = new FragmentChat();

        // 제일 처음 띄워줄 뷰를 세팅함
        // commitAllowingStateLoss();까지 해야함을 주의!
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout_main, fragmentHome).commitAllowingStateLoss();

        // bottomNavigationView의 아이콘을 선택 했을때 원하는 프래그먼트가 띄워질 수 있도록 리스너를 추가
        bottom_navigation_view_main.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) { //menu_bottom.xml에서 지정해줬던 아이디 값을 받아와서 각 아이디값마다 다른 이벤트를 발생시킵니다.
                    case R.id.item_home_main: {
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout_main, fragmentHome).commitAllowingStateLoss();
                        return true;
                    }
                    case R.id.item_agora_main: {
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout_main, fragmentAgora).commitAllowingStateLoss();
                        return true;
                    }
                    case R.id.item_chat_main: {
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout_main, fragmentChat).commitAllowingStateLoss();
                        return true;
                    }

                    default:
                        return false;
                }
            }
        });
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

        final MainService mainService = new MainService(this);
        mainService.getTest();
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
