package com.example.mediaproject_agora.src.main.fragments.fragment_message;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.mediaproject_agora.R;
import com.getbase.floatingactionbutton.FloatingActionButton;

public class FragmentMessage extends Fragment {
    ViewGroup viewGroup;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewGroup = (ViewGroup) inflater.inflate(R.layout.frag_message, container, false);

        FloatingActionButton fab1 = viewGroup.findViewById(R.id.fab_action1);

        fab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "llllllll", Toast.LENGTH_SHORT).show();
            }
        });

        return viewGroup;
    }
}
