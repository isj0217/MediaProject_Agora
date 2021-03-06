package com.example.mediaproject_agora.src.main.fragments.fragment_agora.view_pager_in_frag_agora.frag_agora_department;


import com.example.mediaproject_agora.src.main.fragments.fragment_agora.view_pager_in_frag_agora.frag_agora_department.models.AddFavoriteResponse;
import com.example.mediaproject_agora.src.main.fragments.fragment_agora.view_pager_in_frag_agora.frag_agora_department.models.DepartmentResponse;
import com.example.mediaproject_agora.src.main.models.FavoriteDepartmentResponse;

public interface FragAgoraDepartmentView {

    void validateSuccess(String text);

    void validateFailure(String message);

    void getDepartmentListSuccess(DepartmentResponse departmentResponse);

    void patchFavoriteDepartmentSuccess(AddFavoriteResponse addFavoriteResponse);
}