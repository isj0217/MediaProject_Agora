package com.example.mediaproject_agora.src.main.fragments.fragment_agora.view_pager_in_frag_agora;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.mediaproject_agora.src.main.fragments.fragment_agora.view_pager_in_frag_agora.frag_agora_club.FragAgoraClub;
import com.example.mediaproject_agora.src.main.fragments.fragment_agora.view_pager_in_frag_agora.frag_agora_department.FragAgoraDepartment;
import com.example.mediaproject_agora.src.main.fragments.fragment_agora.view_pager_in_frag_agora.frag_agora_restaurant.FragAgoraRestaurant;
import com.example.mediaproject_agora.src.main.fragments.fragment_agora.view_pager_in_frag_agora.frag_agora_used_product.FragAgoraUsedProduct;

public class ViewPagerAdapter extends FragmentPagerAdapter {

    public ViewPagerAdapter(@NonNull FragmentManager fm) {
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
                return FragAgoraDepartment.newInstance();
            case 1:
                return FragAgoraRestaurant.newInstance();
            case 2:
                return FragAgoraUsedProduct.newInstance();
            case 3:
                return FragAgoraClub.newInstance();
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
                return "학과 게시판";
            case 1:
                return "아주 맛집";
            case 2:
                return "중고거래";
            case 3:
                return "동아리";
            default:
                return null;
        }
    }



}
