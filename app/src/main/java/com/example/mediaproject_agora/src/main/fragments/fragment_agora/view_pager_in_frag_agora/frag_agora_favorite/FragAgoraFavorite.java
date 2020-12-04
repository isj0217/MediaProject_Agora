package com.example.mediaproject_agora.src.main.fragments.fragment_agora.view_pager_in_frag_agora.frag_agora_favorite;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mediaproject_agora.R;
import com.example.mediaproject_agora.src.main.fragments.fragment_agora.FavoriteDepartmentAdapter;
import com.example.mediaproject_agora.src.main.items.DepartmentItem;
import com.example.mediaproject_agora.src.main.models.FavoriteDepartmentResponse;

import java.util.ArrayList;

public class FragAgoraFavorite extends Fragment implements FragAgoraFavoriteView{

    private ArrayList<DepartmentItem> m_department_item_list;
    private FavoriteDepartmentAdapter favorite_department_adapter;
    private RecyclerView rv_favorite_department;
    private LinearLayoutManager linear_layout_manager;

    private View view;

    private LinearLayout ll_agora_favorite_empty;

    public static FragAgoraFavorite newInstance() {
        FragAgoraFavorite fragAgoraFavorite = new FragAgoraFavorite();
        return fragAgoraFavorite;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.frag_agora_favorite, container, false);

        ll_agora_favorite_empty = view.findViewById(R.id.ll_agora_favorite_empty);

        rv_favorite_department = view.findViewById(R.id.rv_favorite_department_list);

        linear_layout_manager = new LinearLayoutManager(view.getContext());
        rv_favorite_department.setLayoutManager(linear_layout_manager);

        m_department_item_list = new ArrayList<>();
        favorite_department_adapter = new FavoriteDepartmentAdapter(m_department_item_list);
        rv_favorite_department.setAdapter(favorite_department_adapter);

        tryGetFavoriteDepartments();

        return view;
    }

    private void tryGetFavoriteDepartments() {

        final FragAgoraFavoriteService fragAgoraFavoriteService = new FragAgoraFavoriteService(this);
        fragAgoraFavoriteService.getFavoriteDepartmentsList();
    }

    @Override
    public void validateSuccess(String text) {

    }

    @Override
    public void validateFailure(String message) {

    }

    @Override
    public void getFavoriteDepartmentListSuccess(FavoriteDepartmentResponse favoriteDepartmentResponse) {

        switch (favoriteDepartmentResponse.getCode()){
            case 100:
                int num_of_favorite_departments = favoriteDepartmentResponse.getFavoriteDepartmentResults().size();

                if (num_of_favorite_departments > 0) {

                    ll_agora_favorite_empty.setVisibility(View.GONE);

                    for (int i = 0; i < num_of_favorite_departments; i++) {
                        DepartmentItem departmentItem = new DepartmentItem();

                        departmentItem.setDepartment(favoriteDepartmentResponse.getFavoriteDepartmentResults().get(i).getDepartment());

                        m_department_item_list.add(departmentItem);
                    }
                    favorite_department_adapter.notifyDataSetChanged();
                }
                break;

            default:
                System.out.println("message:  " + favoriteDepartmentResponse.getMessage());
        }

    }
}
