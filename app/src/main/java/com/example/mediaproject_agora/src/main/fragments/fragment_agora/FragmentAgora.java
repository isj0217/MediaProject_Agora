package com.example.mediaproject_agora.src.main.fragments.fragment_agora;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.mediaproject_agora.R;
import com.example.mediaproject_agora.src.main.fragments.fragment_agora.view_pager_in_frag_agora.AgoraViewPagerAdapter;
import com.google.android.material.tabs.TabLayout;

public class FragmentAgora extends Fragment {
    ViewGroup viewGroup;
    private FragmentPagerAdapter fragmentPagerAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewGroup = (ViewGroup) inflater.inflate(R.layout.frag_agora_view_pager, container, false);

        ViewPager vp_frag_board = (ViewPager) viewGroup.findViewById(R.id.vp_frag_board);
        fragmentPagerAdapter = new AgoraViewPagerAdapter(getChildFragmentManager());

        TabLayout tabLayout = viewGroup.findViewById(R.id.tabLayout_board);
        vp_frag_board.setAdapter(fragmentPagerAdapter);
        tabLayout.setupWithViewPager(vp_frag_board);

        tabLayout.setSelectedTabIndicatorColor(Color.parseColor("#4285F4"));
        tabLayout.setTabTextColors(Color.parseColor("#666666"), Color.parseColor("#000000"));

        return viewGroup;
    }
}
