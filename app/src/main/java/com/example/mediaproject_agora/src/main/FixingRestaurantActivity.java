package com.example.mediaproject_agora.src.main;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;

import com.example.mediaproject_agora.R;
import com.example.mediaproject_agora.src.BaseActivity;
import com.example.mediaproject_agora.src.main.interfaces.FixingDepartmentActivityView;
import com.example.mediaproject_agora.src.main.interfaces.FixingRestaurantActivityView;
import com.example.mediaproject_agora.src.main.models.DefaultResponse;

import java.io.InputStream;
import java.util.HashMap;

public class FixingRestaurantActivity extends BaseActivity implements FixingRestaurantActivityView {

    private ImageView iv_fixing_restaurant_cancel;
    private Button btn_fixing_restaurant_complete;
    private Button btn_fixing_restaurant_korean;
    private Button btn_fixing_restaurant_chinese;
    private Button btn_fixing_restaurant_japanese;

    private boolean is_korean, is_chinese, is_japanese;

    private EditText et_fixing_restaurant_restaurant_name;
    private EditText et_fixing_restaurant_menu;
    private EditText et_fixing_restaurant_price;
    private EditText et_fixing_restaurant_stars;
    private EditText et_fixing_restaurant_content;

    private int restaurant_idx;
    private String restaurant_name;
    private String menu_name;
    private int price;
    private float stars;
    private String content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fixing_restaurant);

        loadCurrentRestaurantPostInfos();

        bindViews();

        initButtonStatus();

        setClickListenersToButtons();


        et_fixing_restaurant_restaurant_name.setText(restaurant_name);
        et_fixing_restaurant_menu.setText(menu_name);
        et_fixing_restaurant_price.setText(Integer.toString(price));
        et_fixing_restaurant_stars.setText(Float.toString(stars));
        et_fixing_restaurant_content.setText(content);

        et_fixing_restaurant_content.requestFocus();

    }

    public void makeKoreanSelected() {
        is_korean = true;
        is_chinese = false;
        is_japanese = false;

        btn_fixing_restaurant_korean.setBackgroundResource(R.drawable.btn_orange_round_corner_light_grey);
        btn_fixing_restaurant_chinese.setBackgroundResource(R.drawable.btn_white_round_corner_light_grey);
        btn_fixing_restaurant_japanese.setBackgroundResource(R.drawable.btn_white_round_corner_light_grey);
    }

    public void makeChineseSelected() {
        is_korean = false;
        is_chinese = true;
        is_japanese = false;

        btn_fixing_restaurant_korean.setBackgroundResource(R.drawable.btn_white_round_corner_light_grey);
        btn_fixing_restaurant_chinese.setBackgroundResource(R.drawable.btn_orange_round_corner_light_grey);
        btn_fixing_restaurant_japanese.setBackgroundResource(R.drawable.btn_white_round_corner_light_grey);
    }

    public void makeJapaneseSelected() {
        is_korean = false;
        is_chinese = false;
        is_japanese = true;

        btn_fixing_restaurant_korean.setBackgroundResource(R.drawable.btn_white_round_corner_light_grey);
        btn_fixing_restaurant_chinese.setBackgroundResource(R.drawable.btn_white_round_corner_light_grey);
        btn_fixing_restaurant_japanese.setBackgroundResource(R.drawable.btn_orange_round_corner_light_grey);
    }

    public int whichCategoryIsSelected() {
        int category = 1;

        if (is_korean) {
            category = 1;
        } else if (is_chinese) {
            category = 2;
        } else if (is_japanese) {
            category = 3;
        }
        return category;
    }

    public void initButtonStatus() {
        is_korean = false;
        is_chinese = false;
        is_japanese = false;
    }

    public void setClickListenersToButtons() {
        btn_fixing_restaurant_korean.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                makeKoreanSelected();
            }
        });

        btn_fixing_restaurant_chinese.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                makeChineseSelected();
            }
        });

        btn_fixing_restaurant_japanese.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                makeJapaneseSelected();
            }
        });

        btn_fixing_restaurant_complete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(view.getContext())
                        .setTitle("수정 완료")
                        .setMessage("수정한 맛집 추천글을 다시 게시하시겠습니까?")
                        .setPositiveButton("예", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {

                                tryPatchRestaurantPost(restaurant_idx,
                                        Float.parseFloat(et_fixing_restaurant_stars.getText().toString()),
                                        null,
                                        et_fixing_restaurant_menu.getText().toString(),
                                        Integer.parseInt(et_fixing_restaurant_price.getText().toString()),
                                        et_fixing_restaurant_content.getText().toString());
                            }
                        })
                        .setCancelable(true)
                        .show();
            }
        });
    }

    public void tryPatchRestaurantPost(int restaurant_idx, float stars, String photo, String menu_name, int price, String content){
        showProgressDialog();

        HashMap<String, Object> params = new HashMap<>();

        params.put("tastehouse_star", stars);
        params.put("menu_picture", photo);
        params.put("menu_name", menu_name);
        params.put("menu_price", price);
        params.put("tastehouse_content", content);

        final FixingRestaurantService fixingRestaurantService = new FixingRestaurantService(this, params);
        fixingRestaurantService.patchRestaurantPost(restaurant_idx);
    }

    public void bindViews() {
        iv_fixing_restaurant_cancel = findViewById(R.id.iv_fixing_restaurant_cancel);

        btn_fixing_restaurant_complete = findViewById(R.id.btn_fixing_restaurant_complete);
        btn_fixing_restaurant_korean = findViewById(R.id.btn_fixing_restaurant_korean);
        btn_fixing_restaurant_chinese = findViewById(R.id.btn_fixing_restaurant_chinese);
        btn_fixing_restaurant_japanese = findViewById(R.id.btn_fixing_restaurant_japanese);

        et_fixing_restaurant_restaurant_name = findViewById(R.id.et_fixing_restaurant_restaurant_name);
        et_fixing_restaurant_menu = findViewById(R.id.et_fixing_restaurant_menu);
        et_fixing_restaurant_price = findViewById(R.id.et_fixing_restaurant_price);
        et_fixing_restaurant_stars = findViewById(R.id.et_fixing_restaurant_stars);
        et_fixing_restaurant_content = findViewById(R.id.et_fixing_restaurant_content);
    }

    public void loadCurrentRestaurantPostInfos() {
        SharedPreferences sharedPreferences = getSharedPreferences("currentRestaurantPostInfos", MODE_PRIVATE);

        restaurant_idx = sharedPreferences.getInt("restaurant_idx", 0);
        restaurant_name = sharedPreferences.getString("restaurant_name", "식당 불러오기 실패");
        menu_name = sharedPreferences.getString("menu_name", "메뉴 불러오기 실패");
        price = sharedPreferences.getInt("price", 0);
        stars = sharedPreferences.getFloat("stars", (float) 0.0);
        content = sharedPreferences.getString("content", "내용 불러오기 실패");
    }

    @Override
    public void validateSuccess(String text) {

    }

    @Override
    public void validateFailure(String message) {

    }

    @Override
    public void patchRestaurantPostSuccess(DefaultResponse defaultResponse) {
        hideProgressDialog();

        switch (defaultResponse.getCode()) {
            default:
                Toast.makeText(this, defaultResponse.getMessage(), Toast.LENGTH_SHORT).show();

                finish();
                break;
        }
    }
}
