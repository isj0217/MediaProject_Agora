package com.example.mediaproject_agora.src.main.fragments.fragment_agora.view_pager_in_frag_agora.frag_agora_department;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.mediaproject_agora.R;
import com.example.mediaproject_agora.src.main.SpecificBoardActivity;
import com.example.mediaproject_agora.src.main.fragments.fragment_agora.view_pager_in_frag_agora.frag_agora_department.models.AddFavoriteResponse;
import com.example.mediaproject_agora.src.main.fragments.fragment_agora.view_pager_in_frag_agora.frag_agora_department.models.DepartmentResponse;

public class FragAgoraDepartment extends Fragment implements FragAgoraDepartmentView {

    private View view;

    public static FragAgoraDepartment newInstance() {
        FragAgoraDepartment fragAgoraDepartment = new FragAgoraDepartment();
        return fragAgoraDepartment;
    }

    LinearLayout ll_agora_department_media;
    LinearLayout ll_agora_department_software;
    LinearLayout ll_agora_department_cyber_security;
    LinearLayout ll_agora_department_electronic_engineering;
    LinearLayout ll_agora_department_military_digital_convergence;

    private boolean favorite_media, favorite_software, favorite_cyber_security, favorite_electronic_engineering, favorite_military_digital_convergence;
    private ImageView iv_agora_department_favorite_media,
                        iv_agora_department_favorite_software,
                        iv_agora_department_favorite_cyber_security,
                        iv_agora_department_favorite_electronic_engineering,
                        iv_agora_department_favorite_military_digital_convergence;

    private ImageView iv_agora_department_media_new,
            iv_agora_department_software_new,
            iv_agora_department_cyber_security_new,
            iv_agora_department_electronic_engineering_new,
            iv_agora_department_military_digital_convergence_new;



    @Nullable
    @Override
    public android.view.View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.frag_agora_department, container, false);

        initializeFavorites();

        bindViews();
        setClickListenersToStars();

        setClickListenersToDepartments();

        tryGetDepartmentList();




        return view;
    }


    public void initializeFavorites(){
        favorite_media = false;
        favorite_software = false;
        favorite_cyber_security = false;
        favorite_electronic_engineering = false;
        favorite_military_digital_convergence = false;
    }

    public void bindViews(){
        ll_agora_department_media = view.findViewById(R.id.ll_agora_department_media);
        ll_agora_department_software = view.findViewById(R.id.ll_agora_department_software);
        ll_agora_department_cyber_security = view.findViewById(R.id.ll_agora_department_cyber_security);
        ll_agora_department_electronic_engineering = view.findViewById(R.id.ll_agora_department_electronic_engineering);
        ll_agora_department_military_digital_convergence = view.findViewById(R.id.ll_agora_department_military_digital_convergence);

        iv_agora_department_favorite_media = view.findViewById(R.id.iv_agora_department_favorite_media);
        iv_agora_department_favorite_software = view.findViewById(R.id.iv_agora_department_favorite_software);
        iv_agora_department_favorite_cyber_security = view.findViewById(R.id.iv_agora_department_favorite_cyber_security);
        iv_agora_department_favorite_electronic_engineering = view.findViewById(R.id.iv_agora_department_favorite_electronic_engineering);
        iv_agora_department_favorite_military_digital_convergence = view.findViewById(R.id.iv_agora_department_favorite_military_digital_convergence);

        iv_agora_department_media_new = view.findViewById(R.id.iv_agora_department_media_new);
        iv_agora_department_software_new = view.findViewById(R.id.iv_agora_department_software_new);
        iv_agora_department_cyber_security_new = view.findViewById(R.id.iv_agora_department_cyber_security_new);
        iv_agora_department_electronic_engineering_new = view.findViewById(R.id.iv_agora_department_electronic_engineering_new);
        iv_agora_department_military_digital_convergence_new = view.findViewById(R.id.iv_agora_department_military_digital_convergence_new);
    }



    public void setClickListenersToStars(){

        // 1. 미디어학과
        iv_agora_department_favorite_media.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                tryPatchFavoriteDepartment("미디어학과");

                if (favorite_media){
                    iv_agora_department_favorite_media.setImageResource(R.drawable.star_border);
                    favorite_media = false;
                }else{
                    iv_agora_department_favorite_media.setImageResource(R.drawable.star);
                    favorite_media=true;
                }
            }
        });

        // 2. 소프트웨어학과
        iv_agora_department_favorite_software.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                tryPatchFavoriteDepartment("소프트웨어학과");

                if (favorite_software){
                    iv_agora_department_favorite_software.setImageResource(R.drawable.star_border);
                    favorite_software = false;
                }else{
                    iv_agora_department_favorite_software.setImageResource(R.drawable.star);
                    favorite_software=true;
                }
            }
        });

        // 3. 사이버보안학과
        iv_agora_department_favorite_cyber_security.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                tryPatchFavoriteDepartment("사이버보안학과");

                if (favorite_cyber_security){
                    iv_agora_department_favorite_cyber_security.setImageResource(R.drawable.star_border);
                    favorite_cyber_security = false;
                }else{
                    iv_agora_department_favorite_cyber_security.setImageResource(R.drawable.star);
                    favorite_cyber_security=true;
                }
            }
        });

        // 4. 전자공학과
        iv_agora_department_favorite_electronic_engineering.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                tryPatchFavoriteDepartment("전자공학과");

                if (favorite_electronic_engineering){
                    iv_agora_department_favorite_electronic_engineering.setImageResource(R.drawable.star_border);
                    favorite_electronic_engineering = false;
                }else{
                    iv_agora_department_favorite_electronic_engineering.setImageResource(R.drawable.star);
                    favorite_electronic_engineering=true;
                }
            }
        });

        // 5. 국방디지털융합학과
        iv_agora_department_favorite_military_digital_convergence.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                tryPatchFavoriteDepartment("국방디지털융합학과");

                if (favorite_military_digital_convergence){
                    iv_agora_department_favorite_military_digital_convergence.setImageResource(R.drawable.star_border);
                    favorite_military_digital_convergence = false;
                }else{
                    iv_agora_department_favorite_military_digital_convergence.setImageResource(R.drawable.star);
                    favorite_military_digital_convergence=true;
                }
            }
        });


    }

    public void setClickListenersToDepartments(){
        // 1. 미디어학과
        ll_agora_department_media.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveRecentSectionAndCategory("department", "미디어학과");
                Intent intent = new Intent(view.getContext(), SpecificBoardActivity.class);
                startActivity(intent);
            }
        });

        // 2. 소프트웨어학과
        ll_agora_department_software.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveRecentSectionAndCategory("department", "소프트웨어학과");
                Intent intent = new Intent(view.getContext(), SpecificBoardActivity.class);
                startActivity(intent);
            }
        });

        // 3. 사이버보안학과
        ll_agora_department_cyber_security.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveRecentSectionAndCategory("department", "사이버보안학과");
                Intent intent = new Intent(view.getContext(), SpecificBoardActivity.class);
                startActivity(intent);
            }
        });

        // 4. 전자공학과
        ll_agora_department_electronic_engineering.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveRecentSectionAndCategory("department", "전자공학과");
                Intent intent = new Intent(view.getContext(), SpecificBoardActivity.class);
                startActivity(intent);
            }
        });

        // 5. 국방디지털융합학과
        ll_agora_department_military_digital_convergence.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveRecentSectionAndCategory("department", "국방디지털융합학과");
                Intent intent = new Intent(view.getContext(), SpecificBoardActivity.class);
                startActivity(intent);
            }
        });
    }

    public void saveRecentSectionAndCategory(String section_in_agora, String category_name){
        Context context = getContext();
        SharedPreferences sharedPreferences = context.getSharedPreferences("recent_section_and_category", context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("section_in_agora", section_in_agora);
        editor.putString("category_name", category_name);
        editor.apply();
    }


    @Override
    public void validateSuccess(String text) {
        Toast.makeText(getContext(), "validateSuccess", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void validateFailure(String message) {
        Toast.makeText(getContext(), "validateFailure", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void getDepartmentListSuccess(DepartmentResponse departmentResponse) {
        loadFavoriteAndNew(departmentResponse);
    }

    @Override
    public void patchFavoriteDepartmentSuccess(AddFavoriteResponse addFavoriteResponse) {

        switch (addFavoriteResponse.getCode()){
//            case 100:
//                Toast.makeText(getContext(), addFavoriteResponse.getMessage(), Toast.LENGTH_SHORT).show();
//                break;

            default:
                Toast.makeText(getContext(), addFavoriteResponse.getMessage(), Toast.LENGTH_SHORT).show();
                break;
        }
    }



    private void tryGetDepartmentList() {

        final FragAgoraDepartmentService fragAgoraDepartmentService = new FragAgoraDepartmentService(this);
        fragAgoraDepartmentService.getDepartmentList();
    }

    private void tryPatchFavoriteDepartment(String department_name) {

        final FragAgoraDepartmentService fragAgoraDepartmentService = new FragAgoraDepartmentService(this);
        fragAgoraDepartmentService.patchFavoriteDepartment(department_name);
    }


    public void loadFavoriteAndNew(DepartmentResponse departmentResponse){
        switch (departmentResponse.getCode()){
            case 100:
                int num_of_departments_in_frag_agora_department = departmentResponse.getDepartmentResults().size();

                System.out.println("학과 갯수: " + num_of_departments_in_frag_agora_department);

                for (int i = 0; i < num_of_departments_in_frag_agora_department; i++){



                    System.out.println(departmentResponse.getDepartmentResults().get(i).getIs_new());




                    if (departmentResponse.getDepartmentResults().get(i).getDepartment_name().equals("미디어학과")){
                        if (departmentResponse.getDepartmentResults().get(i).getStatus() == 1){
                            iv_agora_department_favorite_media.setImageResource(R.drawable.star);
                            favorite_media=true;
                        }
                        if (departmentResponse.getDepartmentResults().get(i).getIs_new() == 0){
                            iv_agora_department_media_new.setVisibility(View.INVISIBLE);
                        }else{
                            iv_agora_department_media_new.setVisibility(View.VISIBLE);
                        }
                    }

                    if (departmentResponse.getDepartmentResults().get(i).getDepartment_name().equals("소프트웨어학과")){
                        if (departmentResponse.getDepartmentResults().get(i).getStatus() == 1){
                            iv_agora_department_favorite_software.setImageResource(R.drawable.star);
                            favorite_software=true;
                        }
                        if (departmentResponse.getDepartmentResults().get(i).getIs_new() == 0){
                            iv_agora_department_software_new.setVisibility(View.INVISIBLE);
                        }else{
                            iv_agora_department_software_new.setVisibility(View.VISIBLE);
                        }
                    }

                    if (departmentResponse.getDepartmentResults().get(i).getDepartment_name().equals("사이버보안학과")){
                        if (departmentResponse.getDepartmentResults().get(i).getStatus() == 1){
                            iv_agora_department_favorite_cyber_security.setImageResource(R.drawable.star);
                            favorite_cyber_security=true;
                        }
                        if (departmentResponse.getDepartmentResults().get(i).getIs_new() == 0){
                            iv_agora_department_cyber_security_new.setVisibility(View.INVISIBLE);
                        }else{
                            iv_agora_department_cyber_security_new.setVisibility(View.VISIBLE);
                        }
                    }

                    if (departmentResponse.getDepartmentResults().get(i).getDepartment_name().equals("전자공학과")){
                        if (departmentResponse.getDepartmentResults().get(i).getStatus() == 1){
                            iv_agora_department_favorite_electronic_engineering.setImageResource(R.drawable.star);
                            favorite_electronic_engineering=true;
                        }
                        if (departmentResponse.getDepartmentResults().get(i).getIs_new() == 0){
                            iv_agora_department_electronic_engineering_new.setVisibility(View.INVISIBLE);
                        }else{
                            iv_agora_department_electronic_engineering_new.setVisibility(View.VISIBLE);
                        }
                    }

                    if (departmentResponse.getDepartmentResults().get(i).getDepartment_name().equals("국방디지털융합학과")){
                        if (departmentResponse.getDepartmentResults().get(i).getStatus() == 1){
                            iv_agora_department_favorite_military_digital_convergence.setImageResource(R.drawable.star);
                            favorite_military_digital_convergence=true;
                        }
                        if (departmentResponse.getDepartmentResults().get(i).getIs_new() == 0){
                            iv_agora_department_military_digital_convergence_new.setVisibility(View.INVISIBLE);
                        }else{
                            iv_agora_department_military_digital_convergence_new.setVisibility(View.VISIBLE);
                        }
                    }

                }

                break;

            default:
                Toast.makeText(getContext(), departmentResponse.getMessage(), Toast.LENGTH_SHORT).show();
                break;
        }
    }

}
