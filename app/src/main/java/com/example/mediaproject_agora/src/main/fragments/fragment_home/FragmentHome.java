package com.example.mediaproject_agora.src.main.fragments.fragment_home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import com.example.mediaproject_agora.R;
import com.google.android.material.navigation.NavigationView;

public class FragmentHome extends Fragment {
    ViewGroup viewGroup;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewGroup = (ViewGroup) inflater.inflate(R.layout.frag_home, container, false);

        final DrawerLayout drawerLayout = viewGroup.findViewById(R.id.drawerLayout);

        viewGroup.findViewById(R.id.imageMenu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });

//        // 이미지들이 모두 검은색으로 보이는 것 막아주기
//        NavigationView navigationView = viewGroup.findViewById(R.id.navigationView);
//        navigationView.setItemIconTintList(null);


//        NavController navController = Navigation.findNavController(this.getActivity(), R.id.navHostFragment);
//        NavigationUI.setupWithNavController(navigationView, navController);


//        final TextView textTitle = viewGroup.findViewById(R.id.textTitle);

//        navController.addOnDestinationChangedListener(new NavController.OnDestinationChangedListener() {
//            @Override
//            public void onDestinationChanged(@NonNull NavController controller, @NonNull NavDestination destination, @Nullable Bundle arguments) {
//                textTitle.setText(destination.getLabel());
//            }
//        });

        return viewGroup;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // 이미지들이 모두 검은색으로 보이는 것 막아주기
        NavigationView navigationView = viewGroup.findViewById(R.id.navigationView);
        navigationView.setItemIconTintList(null);

        NavController navController = Navigation.findNavController(this.getActivity(), R.id.navHostFragment);
        NavigationUI.setupWithNavController(navigationView, navController);

        final TextView textTitle = viewGroup.findViewById(R.id.textTitle);

        navController.addOnDestinationChangedListener(new NavController.OnDestinationChangedListener() {
            @Override
            public void onDestinationChanged(@NonNull NavController controller, @NonNull NavDestination destination, @Nullable Bundle arguments) {
                textTitle.setText(destination.getLabel());
            }
        });

    }
}
