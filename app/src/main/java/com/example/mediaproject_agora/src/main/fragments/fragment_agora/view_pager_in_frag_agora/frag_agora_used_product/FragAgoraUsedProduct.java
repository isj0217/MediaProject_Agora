package com.example.mediaproject_agora.src.main.fragments.fragment_agora.view_pager_in_frag_agora.frag_agora_used_product;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.mediaproject_agora.R;
import com.example.mediaproject_agora.src.main.DepartmentBoardActivity;
import com.example.mediaproject_agora.src.main.UsedProductBoardActivity_book;
import com.example.mediaproject_agora.src.main.UsedProductBoardActivity_clothes;
import com.example.mediaproject_agora.src.main.UsedProductBoardActivity_electronics;
import com.example.mediaproject_agora.src.main.UsedProductBoardActivity_furniture;

public class FragAgoraUsedProduct extends Fragment {

    private View view;

    public static FragAgoraUsedProduct newInstance() {
        FragAgoraUsedProduct fragAgoraUsedProduct = new FragAgoraUsedProduct();
        return fragAgoraUsedProduct;
    }

    LinearLayout ll_agora_used_book;
    LinearLayout ll_agora_used_electronic;
    LinearLayout ll_agora_used_furniture;
    LinearLayout ll_agora_used_clothes;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.frag_agora_used_product, container, false);

        bindViews();
        setClickListenersToCategories();

        return view;
    }

    public void bindViews() {
        ll_agora_used_book = view.findViewById(R.id.ll_agora_used_book);
        ll_agora_used_electronic = view.findViewById(R.id.ll_agora_used_electronic);
        ll_agora_used_furniture = view.findViewById(R.id.ll_agora_used_furniture);
        ll_agora_used_clothes = view.findViewById(R.id.ll_agora_used_clothes);
    }

    public void setClickListenersToCategories() {
        // 1. 중고책방
        ll_agora_used_book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveRecentSectionAndCategory("used_product", "중고책방");
                Intent intent = new Intent(view.getContext(), UsedProductBoardActivity_book.class);
                startActivity(intent);
            }
        });

        // 2. 중고가전
        ll_agora_used_electronic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveRecentSectionAndCategory("used_product", "가전기기");
                Intent intent = new Intent(view.getContext(), UsedProductBoardActivity_electronics.class);
                startActivity(intent);
            }
        });

        // 3. 중고가구
        ll_agora_used_furniture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveRecentSectionAndCategory("used_product", "자취방 가구");
                Intent intent = new Intent(view.getContext(), UsedProductBoardActivity_furniture.class);
                startActivity(intent);
            }
        });

        // 4. 중고의류
        ll_agora_used_clothes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveRecentSectionAndCategory("used_product", "의류 및 신발");
                Intent intent = new Intent(view.getContext(), UsedProductBoardActivity_clothes.class);
                startActivity(intent);
            }
        });

    }

    public void saveRecentSectionAndCategory(String section_in_agora, String category_name) {
        Context context = getContext();
        SharedPreferences sharedPreferences = context.getSharedPreferences("recent_section_and_category", context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("section_in_agora", section_in_agora);
        editor.putString("category_name", category_name);
        editor.apply();
    }

}
