package com.example.mediaproject_agora.src.main.fragments.fragment_restaurant.view_pager_in_frag_restaurant;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.mediaproject_agora.src.main.fragments.fragment_restaurant.view_pager_in_frag_restaurant.frag_restaurant_chinese.FragRestaurantChinese;
import com.example.mediaproject_agora.src.main.fragments.fragment_restaurant.view_pager_in_frag_restaurant.frag_restaurant_japanese.FragRestaurantJapanese;
import com.example.mediaproject_agora.src.main.fragments.fragment_restaurant.view_pager_in_frag_restaurant.frag_restaurant_korean.FragRestaurantKorean;
import com.example.mediaproject_agora.src.main.fragments.fragment_restaurant.view_pager_in_frag_restaurant.frag_restaurant_total.FragRestaurantTotal;

public class RestaurantViewPagerAdapter extends FragmentPagerAdapter {

    public RestaurantViewPagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }


    /**
     * 프래그먼트 교체를 보여주는 처리
     */
    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return FragRestaurantTotal.newInstance();
            case 1:
                return FragRestaurantKorean.newInstance();
            case 2:
                return FragRestaurantChinese.newInstance();
            case 3:
                return FragRestaurantJapanese.newInstance();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 4;
    }


    /**
     * 상단의 탭 레이아웃 인디케이터쪽에 텍스트를 선언해주는 곳
     */
    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "전체";
            case 1:
                return "한식";
            case 2:
                return "중식";
            case 3:
                return "일식";
            default:
                return null;
        }
    }



}
