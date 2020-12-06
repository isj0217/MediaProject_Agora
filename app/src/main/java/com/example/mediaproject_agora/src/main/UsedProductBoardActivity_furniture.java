package com.example.mediaproject_agora.src.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.example.mediaproject_agora.R;
import com.example.mediaproject_agora.src.BaseActivity;


public class UsedProductBoardActivity_furniture extends BaseActivity {

    private LinearLayout ll_used_furniture_1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_used_product_board_furniture);

        ll_used_furniture_1 = findViewById(R.id.ll_used_furniture_1);

        ll_used_furniture_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UsedProductBoardActivity_furniture.this, UsedProductBoardActivity_furniture_1.class);
                startActivity(intent);
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            }
        });

    }

}
