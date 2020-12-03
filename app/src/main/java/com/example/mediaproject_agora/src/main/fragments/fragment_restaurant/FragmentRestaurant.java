package com.example.mediaproject_agora.src.main.fragments.fragment_restaurant;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mediaproject_agora.R;
import com.example.mediaproject_agora.src.main.DepartmentBoardActivity;
import com.example.mediaproject_agora.src.main.InMessageRoomService;
import com.example.mediaproject_agora.src.main.WritingRestaurantActivity;
import com.example.mediaproject_agora.src.main.fragments.fragment_agora.FavoriteDepartmentAdapter;
import com.example.mediaproject_agora.src.main.items.DepartmentItem;
import com.example.mediaproject_agora.src.main.items.RestaurantItem;
import com.getbase.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class FragmentRestaurant extends Fragment implements FragmentRestaurantView {

    private ArrayList<RestaurantItem> m_restaurant_item_list;
    private RestaurantListAdapter restaurant_list_adapter;
    private RecyclerView rv_restaurant;
    private LinearLayoutManager linear_layout_manager;

    // 맛집 카테고리 4가지 - 전체, 한식, 중식, 일식
    private Button btn_restaurant_category_total;
    private Button btn_restaurant_category_korean;
    private Button btn_restaurant_category_chinese;
    private Button btn_restaurant_category_japanese;
    private boolean is_category_total;
    private boolean is_category_korean;
    private boolean is_category_chinese;
    private boolean is_category_japanese;

    // 맛집 필터 6가지 - 별점순, 댓글순, 낮은 가격 순, 높은 가격 순, 최근순, 오래된 순
    private Button btn_restaurant_filter_stars;
    private Button btn_restaurant_filter_comments;
    private Button btn_restaurant_filter_low_price;
    private Button btn_restaurant_filter_high_price;
    private Button btn_restaurant_filter_recent;
    private Button btn_restaurant_filter_old;
    private boolean is_filtered_by_stars;
    private boolean is_filtered_by_comments;
    private boolean is_filtered_by_low_price;
    private boolean is_filtered_by_high_price;
    private boolean is_filtered_by_recent;
    private boolean is_filtered_by_old;


    ViewGroup viewGroup;
    private FragmentPagerAdapter fragmentPagerAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewGroup = (ViewGroup) inflater.inflate(R.layout.frag_restaurant, container, false);

        FloatingActionButton fab1 = viewGroup.findViewById(R.id.fab_action1);
        fab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent intent = new Intent(view.getContext(), WritingRestaurantActivity.class);
                startActivity(intent);

            }
        });

        bindViews();

        initCategories();
        initFilters();

        setClickListenersToCategories();
        setClickListenersToFilters();

        rv_restaurant = viewGroup.findViewById(R.id.rv_restaurant);

        linear_layout_manager = new LinearLayoutManager(viewGroup.getContext());
        rv_restaurant.setLayoutManager(linear_layout_manager);

        m_restaurant_item_list = new ArrayList<>();
        restaurant_list_adapter = new RestaurantListAdapter(m_restaurant_item_list);
        rv_restaurant.setAdapter(restaurant_list_adapter);


        // 화면을 띄우면서 바로 '전체' - '별점순'으로 셋팅해야함
        tryGetRestaurantTotal(1);

        return viewGroup;
    }


    // 글 게시 후에 자동으로 다시 새로고침 하려다가 계속 중복으로 나와서 포기
//    @Override
//    public void onResume() {
//        super.onResume();
//
//        m_restaurant_item_list.clear();
//        restaurant_list_adapter.notifyDataSetChanged();
//
//        if (is_category_total) {
//            switch (whichFilterIsActivatedNow()) {
//                case 1:
//                    tryGetRestaurantTotal(1);
//                    break;
//
//                case 2:
//                    tryGetRestaurantTotal(2);
//                    break;
//
//                case 3:
//                    tryGetRestaurantTotal(3);
//                    break;
//            }
//        } else {
//            if (whichCategoryIsActivatedNow() == 1) {
//                switch (whichFilterIsActivatedNow()) {
//                    case 1:
//                        tryGetCategorizedRestaurant(1, 1);
//                        break;
//
//                    case 2:
//                        tryGetCategorizedRestaurant(2, 1);
//                        break;
//
//                    case 3:
//                        tryGetCategorizedRestaurant(3, 1);
//                        break;
//
//                    case 4:
//                        tryGetCategorizedRestaurant(4, 1);
//                        break;
//
//                    case 5:
//                        tryGetCategorizedRestaurant(5, 1);
//                        break;
//
//                    case 6:
//                        tryGetCategorizedRestaurant(6, 1);
//                        break;
//                }
//            } else if (whichCategoryIsActivatedNow() == 2) {
//                switch (whichFilterIsActivatedNow()) {
//                    case 1:
//                        tryGetCategorizedRestaurant(1, 2);
//                        break;
//
//                    case 2:
//                        tryGetCategorizedRestaurant(2, 2);
//                        break;
//
//                    case 3:
//                        tryGetCategorizedRestaurant(3, 2);
//                        break;
//
//                    case 4:
//                        tryGetCategorizedRestaurant(4, 2);
//                        break;
//
//                    case 5:
//                        tryGetCategorizedRestaurant(5, 2);
//                        break;
//
//                    case 6:
//                        tryGetCategorizedRestaurant(6, 2);
//                        break;
//                }
//            } else if (whichCategoryIsActivatedNow() == 3) {
//                switch (whichFilterIsActivatedNow()) {
//                    case 1:
//                        tryGetCategorizedRestaurant(1, 3);
//                        break;
//
//                    case 2:
//                        tryGetCategorizedRestaurant(2, 3);
//                        break;
//
//                    case 3:
//                        tryGetCategorizedRestaurant(3, 3);
//                        break;
//
//                    case 4:
//                        tryGetCategorizedRestaurant(4, 3);
//                        break;
//
//                    case 5:
//                        tryGetCategorizedRestaurant(5, 3);
//                        break;
//
//                    case 6:
//                        tryGetCategorizedRestaurant(6, 3);
//                        break;
//                }
//            }
//        }
//
//    }

    private void tryGetRestaurantTotal(int filter) {

        final FragmentRestaurantService fragmentRestaurantService = new FragmentRestaurantService(this);
        fragmentRestaurantService.getRestaurantTotal(filter);
    }

    private void tryGetCategorizedRestaurant(int filter, int category) {

        final FragmentRestaurantService fragmentRestaurantService = new FragmentRestaurantService(this);
        fragmentRestaurantService.getCategorizedRestaurant(filter, category);
    }

    public void setClickListenersToCategories() {
        btn_restaurant_category_total.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (whichCategoryIsActivatedNow() != 0) {

                    makeCategoryTotal();
                    m_restaurant_item_list.clear();
                    tryGetRestaurantTotal(whichFilterIsActivatedNow());
                }
            }
        });

        btn_restaurant_category_korean.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (whichCategoryIsActivatedNow() != 1) {

                    makeCategoryKorean();
                    m_restaurant_item_list.clear();
                    // todo
                    // 24번 API 엮기
                    tryGetCategorizedRestaurant(whichFilterIsActivatedNow(), whichCategoryIsActivatedNow());
                }
            }
        });

        btn_restaurant_category_chinese.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (whichCategoryIsActivatedNow() != 2) {

                    makeCategoryChinese();
                    m_restaurant_item_list.clear();
                    // todo
                    // 24번 API 엮기
                    tryGetCategorizedRestaurant(whichFilterIsActivatedNow(), whichCategoryIsActivatedNow());
                }
            }
        });

        btn_restaurant_category_japanese.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (whichCategoryIsActivatedNow() != 3) {

                    makeCategoryJapanese();
                    m_restaurant_item_list.clear();
                    // todo
                    // 24번 API 엮기
                    tryGetCategorizedRestaurant(whichFilterIsActivatedNow(), whichCategoryIsActivatedNow());
                }
            }
        });

    }

    public void setClickListenersToFilters() {
        btn_restaurant_filter_stars.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // 이미 선택되지 않았을 때만 시행
                if (whichFilterIsActivatedNow() != 1) {

                    makeStarsFilter();
                    m_restaurant_item_list.clear();

                    if (whichCategoryIsActivatedNow() == 0) {
                        tryGetRestaurantTotal(whichFilterIsActivatedNow());
                        restaurant_list_adapter.notifyDataSetChanged();
                    } else {
                        // todo
                        // 24번 API 엮기
                        tryGetCategorizedRestaurant(whichFilterIsActivatedNow(), whichCategoryIsActivatedNow());
                    }

                }

            }
        });

        btn_restaurant_filter_comments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // 이미 선택되지 않았을 때만 시행
                if (whichFilterIsActivatedNow() != 2) {

                    makeCommentsFilter();
                    m_restaurant_item_list.clear();

                    if (whichCategoryIsActivatedNow() == 0) {
                        tryGetRestaurantTotal(whichFilterIsActivatedNow());
                        restaurant_list_adapter.notifyDataSetChanged();
                    } else {
                        // todo
                        // 24번 API 엮기
                        tryGetCategorizedRestaurant(whichFilterIsActivatedNow(), whichCategoryIsActivatedNow());
                    }

                }

            }
        });

        btn_restaurant_filter_low_price.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // 이미 선택되지 않았을 때만 시행
                if (whichFilterIsActivatedNow() != 3) {

                    makeLowPriceFilter();
                    m_restaurant_item_list.clear();

                    if (whichCategoryIsActivatedNow() == 0) {
                        tryGetRestaurantTotal(whichFilterIsActivatedNow());
                        restaurant_list_adapter.notifyDataSetChanged();
                    } else {
                        // todo
                        // 24번 API 엮기
                        tryGetCategorizedRestaurant(whichFilterIsActivatedNow(), whichCategoryIsActivatedNow());
                    }

                }
            }
        });

        btn_restaurant_filter_high_price.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // 이미 선택되지 않았을 때만 시행
                if (whichFilterIsActivatedNow() != 4) {

                    makeHighPriceFilter();
                    m_restaurant_item_list.clear();

                    if (whichCategoryIsActivatedNow() == 0) {
                        tryGetRestaurantTotal(whichFilterIsActivatedNow());
                        restaurant_list_adapter.notifyDataSetChanged();
                    } else {
                        // todo
                        // 24번 API 엮기
                        tryGetCategorizedRestaurant(whichFilterIsActivatedNow(), whichCategoryIsActivatedNow());
                    }

                }
            }
        });

        btn_restaurant_filter_recent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // 이미 선택되지 않았을 때만 시행
                if (whichFilterIsActivatedNow() != 5) {

                    makeRecentFilter();
                    m_restaurant_item_list.clear();

                    if (whichCategoryIsActivatedNow() == 0) {
                        tryGetRestaurantTotal(whichFilterIsActivatedNow());
                        restaurant_list_adapter.notifyDataSetChanged();
                    } else {
                        // todo
                        // 24번 API 엮기
                        tryGetCategorizedRestaurant(whichFilterIsActivatedNow(), whichCategoryIsActivatedNow());
                    }

                }
            }
        });

        btn_restaurant_filter_old.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // 이미 선택되지 않았을 때만 시행
                if (whichFilterIsActivatedNow() != 6) {

                    makeOldFilter();
                    m_restaurant_item_list.clear();

                    if (whichCategoryIsActivatedNow() == 0) {
                        tryGetRestaurantTotal(whichFilterIsActivatedNow());
                        restaurant_list_adapter.notifyDataSetChanged();
                    } else {
                        // todo
                        // 24번 API 엮기
                        tryGetCategorizedRestaurant(whichFilterIsActivatedNow(), whichCategoryIsActivatedNow());
                    }

                }
            }
        });
    }

    /**
     * 어떤 카테고리가 현재 적용되어 있는지를 판단해주는 메서드로, 4가지 경우에 따라 0~3을 반환하고, 아무것도 선택되어있지 않으면 -1을 반환한다.
     */
    public int whichCategoryIsActivatedNow() {
        if (is_category_total)
            return 0;
        else if (is_category_korean)
            return 1;
        else if (is_category_chinese)
            return 2;
        else if (is_category_japanese)
            return 3;

        else
            return -1;
    }

    /**
     * 어떤 필터가 현재 적용되어 있는지를 판단해주는 메서드로, 6가지 경우에 따라 1~6을 반환하고, 아무것도 선택되어있지 않으면 -1을 반환한다.
     */
    public int whichFilterIsActivatedNow() {
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
            return -1;
    }


    public void bindViews() {

        // 맛집 카테고리 4가지 - 전체, 한식, 중식, 일식
        btn_restaurant_category_total = viewGroup.findViewById(R.id.btn_restaurant_category_total);
        btn_restaurant_category_korean = viewGroup.findViewById(R.id.btn_restaurant_category_korean);
        btn_restaurant_category_chinese = viewGroup.findViewById(R.id.btn_restaurant_category_chinese);
        btn_restaurant_category_japanese = viewGroup.findViewById(R.id.btn_restaurant_category_japanese);

        // 맛집 필터 6가지 - 별점순, 댓글순, 낮은 가격 순, 높은 가격 순, 최근순, 오래된 순
        btn_restaurant_filter_stars = viewGroup.findViewById(R.id.btn_restaurant_filter_stars);
        btn_restaurant_filter_comments = viewGroup.findViewById(R.id.btn_restaurant_filter_comments);
        btn_restaurant_filter_low_price = viewGroup.findViewById(R.id.btn_restaurant_filter_low_price);
        btn_restaurant_filter_high_price = viewGroup.findViewById(R.id.btn_restaurant_filter_high_price);
        btn_restaurant_filter_recent = viewGroup.findViewById(R.id.btn_restaurant_filter_recent);
        btn_restaurant_filter_old = viewGroup.findViewById(R.id.btn_restaurant_filter_old);
    }

    public void initCategories() {

        // 초기에는 '전체'로 기본 셋팅!!
        is_category_total = true;
        is_category_korean = false;
        is_category_chinese = false;
        is_category_japanese = false;

        // 초기에는 '전체'로 기본 셋팅!!
        btn_restaurant_category_total.setBackgroundResource(R.drawable.btn_dark_orange_round_corner_light_grey);
        btn_restaurant_category_korean.setBackgroundResource(R.drawable.btn_white_round_corner_light_grey);
        btn_restaurant_category_chinese.setBackgroundResource(R.drawable.btn_white_round_corner_light_grey);
        btn_restaurant_category_japanese.setBackgroundResource(R.drawable.btn_white_round_corner_light_grey);
    }

    public void initFilters() {

        // 초기에는 별점순으로 기본 셋팅!!
        is_filtered_by_stars = true;
        is_filtered_by_comments = false;
        is_filtered_by_low_price = false;
        is_filtered_by_high_price = false;
        is_filtered_by_recent = false;
        is_filtered_by_old = false;

        // 초기에는 별점순으로 기본 셋팅!!
        btn_restaurant_filter_stars.setBackgroundResource(R.drawable.btn_orange_round_corner_light_grey);
        btn_restaurant_filter_comments.setBackgroundResource(R.drawable.btn_white_round_corner_light_grey);
        btn_restaurant_filter_low_price.setBackgroundResource(R.drawable.btn_white_round_corner_light_grey);
        btn_restaurant_filter_high_price.setBackgroundResource(R.drawable.btn_white_round_corner_light_grey);
        btn_restaurant_filter_recent.setBackgroundResource(R.drawable.btn_white_round_corner_light_grey);
        btn_restaurant_filter_old.setBackgroundResource(R.drawable.btn_white_round_corner_light_grey);
    }

    // 카테고리 변경하는 4가지 경우
    public void makeCategoryTotal() {
        is_category_total = true;
        btn_restaurant_category_total.setBackgroundResource(R.drawable.btn_dark_orange_round_corner_light_grey);

        is_category_korean = false;
        btn_restaurant_category_korean.setBackgroundResource(R.drawable.btn_white_round_corner_light_grey);

        is_category_chinese = false;
        btn_restaurant_category_chinese.setBackgroundResource(R.drawable.btn_white_round_corner_light_grey);

        is_category_japanese = false;
        btn_restaurant_category_japanese.setBackgroundResource(R.drawable.btn_white_round_corner_light_grey);
    }

    public void makeCategoryKorean() {
        is_category_total = false;
        btn_restaurant_category_total.setBackgroundResource(R.drawable.btn_white_round_corner_light_grey);

        is_category_korean = true;
        btn_restaurant_category_korean.setBackgroundResource(R.drawable.btn_dark_orange_round_corner_light_grey);

        is_category_chinese = false;
        btn_restaurant_category_chinese.setBackgroundResource(R.drawable.btn_white_round_corner_light_grey);

        is_category_japanese = false;
        btn_restaurant_category_japanese.setBackgroundResource(R.drawable.btn_white_round_corner_light_grey);
    }

    public void makeCategoryChinese() {
        is_category_total = false;
        btn_restaurant_category_total.setBackgroundResource(R.drawable.btn_white_round_corner_light_grey);

        is_category_korean = false;
        btn_restaurant_category_korean.setBackgroundResource(R.drawable.btn_white_round_corner_light_grey);

        is_category_chinese = true;
        btn_restaurant_category_chinese.setBackgroundResource(R.drawable.btn_dark_orange_round_corner_light_grey);

        is_category_japanese = false;
        btn_restaurant_category_japanese.setBackgroundResource(R.drawable.btn_white_round_corner_light_grey);
    }

    public void makeCategoryJapanese() {
        is_category_total = false;
        btn_restaurant_category_total.setBackgroundResource(R.drawable.btn_white_round_corner_light_grey);

        is_category_korean = false;
        btn_restaurant_category_korean.setBackgroundResource(R.drawable.btn_white_round_corner_light_grey);

        is_category_chinese = false;
        btn_restaurant_category_chinese.setBackgroundResource(R.drawable.btn_white_round_corner_light_grey);

        is_category_japanese = true;
        btn_restaurant_category_japanese.setBackgroundResource(R.drawable.btn_dark_orange_round_corner_light_grey);
    }

    // 필터 변경하는 6가지 경우
    public void makeStarsFilter() {
        is_filtered_by_stars = true;
        btn_restaurant_filter_stars.setBackgroundResource(R.drawable.btn_orange_round_corner_light_grey);

        is_filtered_by_comments = false;
        btn_restaurant_filter_comments.setBackgroundResource(R.drawable.btn_white_round_corner_light_grey);
        is_filtered_by_low_price = false;
        btn_restaurant_filter_low_price.setBackgroundResource(R.drawable.btn_white_round_corner_light_grey);
        is_filtered_by_high_price = false;
        btn_restaurant_filter_high_price.setBackgroundResource(R.drawable.btn_white_round_corner_light_grey);
        is_filtered_by_recent = false;
        btn_restaurant_filter_recent.setBackgroundResource(R.drawable.btn_white_round_corner_light_grey);
        is_filtered_by_old = false;
        btn_restaurant_filter_old.setBackgroundResource(R.drawable.btn_white_round_corner_light_grey);
    }

    public void makeCommentsFilter() {
        is_filtered_by_stars = false;
        btn_restaurant_filter_stars.setBackgroundResource(R.drawable.btn_white_round_corner_light_grey);

        is_filtered_by_comments = true;
        btn_restaurant_filter_comments.setBackgroundResource(R.drawable.btn_orange_round_corner_light_grey);

        is_filtered_by_low_price = false;
        btn_restaurant_filter_low_price.setBackgroundResource(R.drawable.btn_white_round_corner_light_grey);
        is_filtered_by_high_price = false;
        btn_restaurant_filter_high_price.setBackgroundResource(R.drawable.btn_white_round_corner_light_grey);
        is_filtered_by_recent = false;
        btn_restaurant_filter_recent.setBackgroundResource(R.drawable.btn_white_round_corner_light_grey);
        is_filtered_by_old = false;
        btn_restaurant_filter_old.setBackgroundResource(R.drawable.btn_white_round_corner_light_grey);
    }

    public void makeLowPriceFilter() {
        is_filtered_by_stars = false;
        btn_restaurant_filter_stars.setBackgroundResource(R.drawable.btn_white_round_corner_light_grey);
        is_filtered_by_comments = false;
        btn_restaurant_filter_comments.setBackgroundResource(R.drawable.btn_white_round_corner_light_grey);

        is_filtered_by_low_price = true;
        btn_restaurant_filter_low_price.setBackgroundResource(R.drawable.btn_orange_round_corner_light_grey);

        is_filtered_by_high_price = false;
        btn_restaurant_filter_high_price.setBackgroundResource(R.drawable.btn_white_round_corner_light_grey);
        is_filtered_by_recent = false;
        btn_restaurant_filter_recent.setBackgroundResource(R.drawable.btn_white_round_corner_light_grey);
        is_filtered_by_old = false;
        btn_restaurant_filter_old.setBackgroundResource(R.drawable.btn_white_round_corner_light_grey);
    }

    public void makeHighPriceFilter() {
        is_filtered_by_stars = false;
        btn_restaurant_filter_stars.setBackgroundResource(R.drawable.btn_white_round_corner_light_grey);
        is_filtered_by_comments = false;
        btn_restaurant_filter_comments.setBackgroundResource(R.drawable.btn_white_round_corner_light_grey);
        is_filtered_by_low_price = false;
        btn_restaurant_filter_low_price.setBackgroundResource(R.drawable.btn_white_round_corner_light_grey);

        is_filtered_by_high_price = true;
        btn_restaurant_filter_high_price.setBackgroundResource(R.drawable.btn_orange_round_corner_light_grey);

        is_filtered_by_recent = false;
        btn_restaurant_filter_recent.setBackgroundResource(R.drawable.btn_white_round_corner_light_grey);
        is_filtered_by_old = false;
        btn_restaurant_filter_old.setBackgroundResource(R.drawable.btn_white_round_corner_light_grey);
    }

    public void makeRecentFilter() {
        is_filtered_by_stars = false;
        btn_restaurant_filter_stars.setBackgroundResource(R.drawable.btn_white_round_corner_light_grey);
        is_filtered_by_comments = false;
        btn_restaurant_filter_comments.setBackgroundResource(R.drawable.btn_white_round_corner_light_grey);
        is_filtered_by_low_price = false;
        btn_restaurant_filter_low_price.setBackgroundResource(R.drawable.btn_white_round_corner_light_grey);
        is_filtered_by_high_price = false;
        btn_restaurant_filter_high_price.setBackgroundResource(R.drawable.btn_white_round_corner_light_grey);

        is_filtered_by_recent = true;
        btn_restaurant_filter_recent.setBackgroundResource(R.drawable.btn_orange_round_corner_light_grey);

        is_filtered_by_old = false;
        btn_restaurant_filter_old.setBackgroundResource(R.drawable.btn_white_round_corner_light_grey);
    }

    public void makeOldFilter() {
        is_filtered_by_stars = false;
        btn_restaurant_filter_stars.setBackgroundResource(R.drawable.btn_white_round_corner_light_grey);
        is_filtered_by_comments = false;
        btn_restaurant_filter_comments.setBackgroundResource(R.drawable.btn_white_round_corner_light_grey);
        is_filtered_by_low_price = false;
        btn_restaurant_filter_low_price.setBackgroundResource(R.drawable.btn_white_round_corner_light_grey);
        is_filtered_by_high_price = false;
        btn_restaurant_filter_high_price.setBackgroundResource(R.drawable.btn_white_round_corner_light_grey);
        is_filtered_by_recent = false;
        btn_restaurant_filter_recent.setBackgroundResource(R.drawable.btn_white_round_corner_light_grey);

        is_filtered_by_old = true;
        btn_restaurant_filter_old.setBackgroundResource(R.drawable.btn_orange_round_corner_light_grey);
    }

    @Override
    public void validateSuccess(String text) {

    }

    @Override
    public void validateFailure(String message) {

    }

    @Override
    public void getRestaurantListSuccess(RestaurantListResponse restaurantListResponse) {

        System.out.println(restaurantListResponse.getMessage());

        switch (restaurantListResponse.getCode()) {
            default:
                int num_of_restaurants = restaurantListResponse.getRestaurantResults().size();

                for (int i = 0; i < num_of_restaurants; i++) {
                    RestaurantItem restaurantItem = new RestaurantItem();

                    restaurantItem.setTastehouse_idx(restaurantListResponse.getRestaurantResults().get(i).getTastehouse_idx());
                    restaurantItem.setTastehouse_name(restaurantListResponse.getRestaurantResults().get(i).getTastehouse_name());
                    restaurantItem.setTastehouse_star(restaurantListResponse.getRestaurantResults().get(i).getTastehouse_star());
                    restaurantItem.setMenu_picture(restaurantListResponse.getRestaurantResults().get(i).getMenu_picture());
                    restaurantItem.setMenu_name(restaurantListResponse.getRestaurantResults().get(i).getMenu_name());
                    restaurantItem.setMenu_price(restaurantListResponse.getRestaurantResults().get(i).getMenu_price());
                    restaurantItem.setTastehouse_content(restaurantListResponse.getRestaurantResults().get(i).getTastehouse_content());
                    restaurantItem.setNickname(restaurantListResponse.getRestaurantResults().get(i).getNickname());
                    restaurantItem.setComment_num(restaurantListResponse.getRestaurantResults().get(i).getComment_num());
                    restaurantItem.setTime(restaurantListResponse.getRestaurantResults().get(i).getTime());

                    m_restaurant_item_list.add(restaurantItem);
                }
                restaurant_list_adapter.notifyDataSetChanged();
                break;
        }
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
