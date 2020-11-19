package com.example.mediaproject_agora.src.main.fragments.fragment_agora.view_pager_in_frag_agora.frag_agora_favorite;


import com.example.mediaproject_agora.src.main.fragments.fragment_agora.view_pager_in_frag_agora.frag_agora_department.models.AddFavoriteResponse;
import com.example.mediaproject_agora.src.main.fragments.fragment_agora.view_pager_in_frag_agora.frag_agora_department.models.DepartmentResponse;
import com.example.mediaproject_agora.src.main.models.FavoriteDepartmentResponse;

public interface FragAgoraFavoriteView {

    void validateSuccess(String text);

    void validateFailure(String message);

    void getFavoriteDepartmentListSuccess(FavoriteDepartmentResponse favoriteDepartmentResponse);
}