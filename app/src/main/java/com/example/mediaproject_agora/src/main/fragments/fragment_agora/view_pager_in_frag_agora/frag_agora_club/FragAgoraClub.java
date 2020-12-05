package com.example.mediaproject_agora.src.main.fragments.fragment_agora.view_pager_in_frag_agora.frag_agora_club;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.mediaproject_agora.R;
import com.example.mediaproject_agora.src.main.ClubBoardActivity_samter;
import com.example.mediaproject_agora.src.main.UsedProductBoardActivity_book;

public class FragAgoraClub extends Fragment {

    private View view;

    public static FragAgoraClub newInstance() {
        FragAgoraClub fragAgoraClub = new FragAgoraClub();
        return fragAgoraClub;
    }

    LinearLayout ll_agora_club_samter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.frag_agora_club, container, false);

        bindViews();
        setClickListenersToCategories();

        return view;
    }

    public void bindViews() {
        ll_agora_club_samter = view.findViewById(R.id.ll_agora_club_samter);
    }

    public void setClickListenersToCategories() {
        // 1. 샘터야학
        ll_agora_club_samter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), ClubBoardActivity_samter.class);
                startActivity(intent);
            }
        });

    }
}
