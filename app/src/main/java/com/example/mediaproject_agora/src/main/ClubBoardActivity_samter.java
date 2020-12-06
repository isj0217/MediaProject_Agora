package com.example.mediaproject_agora.src.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.example.mediaproject_agora.R;
import com.example.mediaproject_agora.src.BaseActivity;


public class ClubBoardActivity_samter extends BaseActivity {

    private LinearLayout ll_club_samter_1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_club_board_samter);

        ll_club_samter_1 = findViewById(R.id.ll_club_samter_1);

        ll_club_samter_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ClubBoardActivity_samter.this, ClubBoardActivity_samter_1.class);
                startActivity(intent);
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            }
        });

    }

}
