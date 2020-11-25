package com.example.mediaproject_agora.src.main;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
import com.example.mediaproject_agora.src.main.interfaces.WritingDepartmentActivityView;
import com.example.mediaproject_agora.src.main.interfaces.WritingRestaurantActivityView;
import com.example.mediaproject_agora.src.main.models.DefaultResponse;

import java.io.InputStream;
import java.util.HashMap;

public class WritingRestaurantActivity extends BaseActivity implements WritingRestaurantActivityView {

    private Button btn_writing_restaurant_complete;

    private Button btn_writing_restaurant_korean, btn_writing_restaurant_chinese, btn_writing_restaurant_japanese;
    private boolean is_korean, is_chinese, is_japanese;

    private EditText et_writing_restaurant_restaurant_name;
    private EditText et_writing_restaurant_menu;
    private EditText et_writing_restaurant_price;
    private EditText et_writing_restaurant_stars;
    private EditText et_writing_restaurant_content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_writing_restaurant);

        bindViews();

        initButtonStatus();

        setClickListenersToButtons();
    }

    public void makeKoreanSelected() {
        is_korean = true;
        is_chinese = false;
        is_japanese = false;

        btn_writing_restaurant_korean.setBackgroundResource(R.drawable.btn_orange_round_corner_light_grey);
        btn_writing_restaurant_chinese.setBackgroundResource(R.drawable.btn_white_round_corner_light_grey);
        btn_writing_restaurant_japanese.setBackgroundResource(R.drawable.btn_white_round_corner_light_grey);
    }

    public void makeChineseSelected() {
        is_korean = false;
        is_chinese = true;
        is_japanese = false;

        btn_writing_restaurant_korean.setBackgroundResource(R.drawable.btn_white_round_corner_light_grey);
        btn_writing_restaurant_chinese.setBackgroundResource(R.drawable.btn_orange_round_corner_light_grey);
        btn_writing_restaurant_japanese.setBackgroundResource(R.drawable.btn_white_round_corner_light_grey);
    }

    public void makeJapaneseSelected() {
        is_korean = false;
        is_chinese = false;
        is_japanese = true;

        btn_writing_restaurant_korean.setBackgroundResource(R.drawable.btn_white_round_corner_light_grey);
        btn_writing_restaurant_chinese.setBackgroundResource(R.drawable.btn_white_round_corner_light_grey);
        btn_writing_restaurant_japanese.setBackgroundResource(R.drawable.btn_orange_round_corner_light_grey);
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

    public void bindViews() {
        btn_writing_restaurant_complete = findViewById(R.id.btn_writing_restaurant_complete);

        btn_writing_restaurant_korean = findViewById(R.id.btn_writing_restaurant_korean);
        btn_writing_restaurant_chinese = findViewById(R.id.btn_writing_restaurant_chinese);
        btn_writing_restaurant_japanese = findViewById(R.id.btn_writing_restaurant_japanese);

        et_writing_restaurant_restaurant_name = findViewById(R.id.et_writing_restaurant_restaurant_name);
        et_writing_restaurant_menu = findViewById(R.id.et_writing_restaurant_menu);
        et_writing_restaurant_price = findViewById(R.id.et_writing_restaurant_price);
        et_writing_restaurant_stars = findViewById(R.id.et_writing_restaurant_stars);
        et_writing_restaurant_content = findViewById(R.id.et_writing_restaurant_content);
    }

    public void setClickListenersToButtons() {
        btn_writing_restaurant_korean.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                makeKoreanSelected();
            }
        });

        btn_writing_restaurant_chinese.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                makeChineseSelected();
            }
        });

        btn_writing_restaurant_japanese.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                makeJapaneseSelected();
            }
        });

        btn_writing_restaurant_complete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(view.getContext())
                        .setTitle("맛집 추천 완료")
                        .setMessage("맛집 추천글을 게시하시겠습니까?")
                        .setPositiveButton("예", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {

                                tryPostRestaurantPost(whichCategoryIsSelected(),
                                        et_writing_restaurant_restaurant_name.getText().toString(),
                                        Float.parseFloat(et_writing_restaurant_stars.getText().toString()),
                                        null,
                                        et_writing_restaurant_menu.getText().toString(),
                                        Integer.parseInt(et_writing_restaurant_price.getText().toString()),
                                        et_writing_restaurant_content.getText().toString());
                            }
                        })
                        .setCancelable(true)
                        .show();
            }
        });
    }

    public void tryPostRestaurantPost(int category, String restaurant_name, float stars, String photo, String menu_name, int price, String content){
        HashMap<String, Object> params = new HashMap<>();

        params.put("tastehouse_category", category);
        params.put("tastehouse_name", restaurant_name);
        params.put("tastehouse_star", stars);
        params.put("menu_picture", photo);
        params.put("menu_name", menu_name);
        params.put("menu_price", price);
        params.put("tastehouse_content", content);

        final WritingRestaurantService writingRestaurantService = new WritingRestaurantService(this, params);
        writingRestaurantService.postRestaurantPost();
    }


    @Override
    public void validateSuccess(String text) {

    }

    @Override
    public void validateFailure(String message) {

    }

    @Override
    public void postRestaurantPostSuccess(DefaultResponse defaultResponse) {
        hideProgressDialog();

        switch(defaultResponse.getCode()){
            default:
                System.out.println(defaultResponse.getMessage());
                finish();

                break;
        }
    }
}