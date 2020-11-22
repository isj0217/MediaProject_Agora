package com.example.mediaproject_agora.src.main;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mediaproject_agora.R;
import com.example.mediaproject_agora.src.BaseActivity;
import com.example.mediaproject_agora.src.main.interfaces.InPostActivityView;
import com.example.mediaproject_agora.src.main.interfaces.InRestaurantPostActivityView;
import com.example.mediaproject_agora.src.main.items.CommentItem;
import com.example.mediaproject_agora.src.main.models.CommentAdapter;
import com.example.mediaproject_agora.src.main.models.DefaultResponse;
import com.example.mediaproject_agora.src.main.models.InPostCommentResponse;
import com.example.mediaproject_agora.src.main.models.InPostPostResponse;

import java.util.ArrayList;
import java.util.HashMap;


public class InRestaurantPostActivity extends BaseActivity implements InRestaurantPostActivityView, PopupMenu.OnMenuItemClickListener {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_in_restaurant_post);

    }

    @Override
    public boolean onMenuItemClick(MenuItem menuItem) {
        return false;
    }
}
