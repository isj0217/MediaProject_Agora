package com.example.mediaproject_agora.src.main.fragments.fragment_restaurant;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.mediaproject_agora.R;
import com.example.mediaproject_agora.src.main.DepartmentBoardActivity;
import com.getbase.floatingactionbutton.FloatingActionButton;

public class FragmentRestaurant extends Fragment {

    ViewGroup viewGroup;
    private FragmentPagerAdapter fragmentPagerAdapter;

    private Button btn_restaurant_filter_stars, btn_restaurant_filter_comments,
            btn_restaurant_filter_low_price, btn_restaurant_filter_high_price,
            btn_restaurant_filter_recent, btn_restaurant_filter_old;

    private boolean is_filtered_by_stars, is_filtered_by_comments, is_filtered_by_low_price, is_filtered_by_high_price, is_filtered_by_recent, is_filtered_by_old;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewGroup = (ViewGroup) inflater.inflate(R.layout.frag_restaurant, container, false);

        FloatingActionButton fab1 = viewGroup.findViewById(R.id.fab_action1);
        fab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), DepartmentBoardActivity.class);
                startActivity(intent);
            }
        });

        bindViews();

        initFilters();

        btn_restaurant_filter_stars.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (whichFilterIsActivatedNow() == 1) {
                    btn_restaurant_filter_stars.setBackgroundResource(R.drawable.btn_white_round_corner_light_grey);
                    is_filtered_by_stars = false;
                } else{
                    makeStarsFilter();
                }
            }
        });

        btn_restaurant_filter_comments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (whichFilterIsActivatedNow() == 2) {
                    btn_restaurant_filter_comments.setBackgroundResource(R.drawable.btn_white_round_corner_light_grey);
                    is_filtered_by_comments = false;
                } else{
                    makeCommentsFilter();
                }
            }
        });

        btn_restaurant_filter_low_price.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (whichFilterIsActivatedNow() == 3) {
                    btn_restaurant_filter_low_price.setBackgroundResource(R.drawable.btn_white_round_corner_light_grey);
                    is_filtered_by_low_price = false;
                } else{
                    makeLowPriceFilter();
                }
            }
        });

        btn_restaurant_filter_high_price.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (whichFilterIsActivatedNow() == 4) {
                    btn_restaurant_filter_high_price.setBackgroundResource(R.drawable.btn_white_round_corner_light_grey);
                    is_filtered_by_high_price = false;
                } else{
                    makeHighPriceFilter();
                }
            }
        });

        btn_restaurant_filter_recent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (whichFilterIsActivatedNow() == 5) {
                    btn_restaurant_filter_recent.setBackgroundResource(R.drawable.btn_white_round_corner_light_grey);
                    is_filtered_by_recent = false;
                } else{
                    makeRecentFilter();
                }
            }
        });

        btn_restaurant_filter_old.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (whichFilterIsActivatedNow() == 6) {
                    btn_restaurant_filter_old.setBackgroundResource(R.drawable.btn_white_round_corner_light_grey);
                    is_filtered_by_old = false;
                } else{
                    makeOldFilter();
                }
            }
        });


        return viewGroup;
    }

    /**
     * 어떤 필터가 현재 적용되어 있는지를 판단해주는 메서드로, 6가지 경우에 따라 1~6을 반환하고, 아무것도 선택되어있지 않으면 0을 반환한다.
     * */
    public int whichFilterIsActivatedNow(){
        if (is_filtered_by_stars)
            return 1;
        else if (is_filtered_by_comments)
            return 2;
        else if (is_filtered_by_low_price)
            return 3;
        else if (is_filtered_by_high_price)
            return 4;
        else if (is_filtered_by_recent)
            return 5;
        else if (is_filtered_by_old)
            return 6;
        else
            return 0;
    }




    public void makeStarsFilter() {
        initFilters();
        btn_restaurant_filter_stars.setBackgroundResource(R.drawable.btn_mint_round_corner_light_grey);
        is_filtered_by_stars = true;
    }

    public void makeCommentsFilter() {
        initFilters();
        btn_restaurant_filter_comments.setBackgroundResource(R.drawable.btn_mint_round_corner_light_grey);
        is_filtered_by_comments = true;
    }

    public void makeLowPriceFilter() {
        initFilters();
        btn_restaurant_filter_low_price.setBackgroundResource(R.drawable.btn_mint_round_corner_light_grey);
        is_filtered_by_low_price = true;
    }

    public void makeHighPriceFilter() {
        initFilters();
        btn_restaurant_filter_high_price.setBackgroundResource(R.drawable.btn_mint_round_corner_light_grey);
        is_filtered_by_high_price = true;
    }

    public void makeRecentFilter() {
        initFilters();
        btn_restaurant_filter_recent.setBackgroundResource(R.drawable.btn_mint_round_corner_light_grey);
        is_filtered_by_recent = true;
    }

    public void makeOldFilter() {
        initFilters();
        btn_restaurant_filter_old.setBackgroundResource(R.drawable.btn_mint_round_corner_light_grey);
        is_filtered_by_old = true;
    }



    public boolean isNotFiltered() {
        if (!(is_filtered_by_stars || is_filtered_by_comments || is_filtered_by_low_price || is_filtered_by_high_price || is_filtered_by_recent || is_filtered_by_old)) {
            return true;
        } else
            return false;
    }

    public void initFilters() {
        is_filtered_by_stars = false;
        is_filtered_by_comments = false;
        is_filtered_by_low_price = false;
        is_filtered_by_high_price = false;
        is_filtered_by_recent = false;
        is_filtered_by_old = false;

        btn_restaurant_filter_stars.setBackgroundResource(R.drawable.btn_white_round_corner_light_grey);
        btn_restaurant_filter_comments.setBackgroundResource(R.drawable.btn_white_round_corner_light_grey);
        btn_restaurant_filter_low_price.setBackgroundResource(R.drawable.btn_white_round_corner_light_grey);
        btn_restaurant_filter_high_price.setBackgroundResource(R.drawable.btn_white_round_corner_light_grey);
        btn_restaurant_filter_recent.setBackgroundResource(R.drawable.btn_white_round_corner_light_grey);
        btn_restaurant_filter_old.setBackgroundResource(R.drawable.btn_white_round_corner_light_grey);
    }

    public void bindViews() {
        btn_restaurant_filter_stars = viewGroup.findViewById(R.id.btn_restaurant_filter_stars);
        btn_restaurant_filter_comments = viewGroup.findViewById(R.id.btn_restaurant_filter_comments);
        btn_restaurant_filter_low_price = viewGroup.findViewById(R.id.btn_restaurant_filter_low_price);
        btn_restaurant_filter_high_price = viewGroup.findViewById(R.id.btn_restaurant_filter_high_price);
        btn_restaurant_filter_recent = viewGroup.findViewById(R.id.btn_restaurant_filter_recent);
        btn_restaurant_filter_old = viewGroup.findViewById(R.id.btn_restaurant_filter_old);
    }

//    public void customOnClick(View view) {
//        switch (view.getId()) {
////            case R.id.fab_action1:
////                Toast.makeText(context, "ssssss", Toast.LENGTH_SHORT).show();
////                break;
//
//            case R.id.floating_action_button:
//                Toast.makeText(getContext(), "kkkkkk", Toast.LENGTH_SHORT).show();
//            default:
//                break;
//        }
//    }
}
