package com.example.mediaproject_agora.src.main.fragments.fragment_agora.view_pager_in_frag_agora.frag_agora_restaurant;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.mediaproject_agora.R;

public class FragAgoraRestaurant extends Fragment {

    private View view;

    public static FragAgoraRestaurant newInstance() {
        FragAgoraRestaurant fragAgoraRestaurant = new FragAgoraRestaurant();
        return fragAgoraRestaurant;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.frag_agora_restaurant, container, false);

        return view;
    }
    
}
