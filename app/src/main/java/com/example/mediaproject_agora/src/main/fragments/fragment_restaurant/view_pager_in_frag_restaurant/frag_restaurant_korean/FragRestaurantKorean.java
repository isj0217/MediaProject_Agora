package com.example.mediaproject_agora.src.main.fragments.fragment_restaurant.view_pager_in_frag_restaurant.frag_restaurant_korean;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.mediaproject_agora.R;

public class FragRestaurantKorean extends Fragment {

    private View view;

    public static FragRestaurantKorean newInstance() {
        FragRestaurantKorean fragRestaurantKorean = new FragRestaurantKorean();
        return fragRestaurantKorean;
    }

    //여기서부터 수정(지금 Viewpager 이용해서 restaurant fragment 내부에 들어갈 프래그먼트들 만드는 중이었음)



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.frag_restaurant_not_used, container, false);

        bindViews();




        return view;
    }



    public void bindViews(){

    }





}
