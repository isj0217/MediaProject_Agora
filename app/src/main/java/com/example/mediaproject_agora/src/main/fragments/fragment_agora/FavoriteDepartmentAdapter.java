package com.example.mediaproject_agora.src.main.fragments.fragment_agora;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mediaproject_agora.R;
import com.example.mediaproject_agora.src.main.DepartmentBoardActivity;
import com.example.mediaproject_agora.src.main.InPostActivity;
import com.example.mediaproject_agora.src.main.items.DepartmentItem;
import com.example.mediaproject_agora.src.main.items.DepartmentPostItem;

import java.util.ArrayList;

public class FavoriteDepartmentAdapter extends RecyclerView.Adapter<FavoriteDepartmentAdapter.CustomViewHolder> {

    private ArrayList<DepartmentItem> department_item_list;

    private OnItemClickListener mListener = null;

    public FavoriteDepartmentAdapter(ArrayList<DepartmentItem> department_item_list) {
        this.department_item_list = department_item_list;
    }

    public interface OnItemClickListener {
        void onItemClick(View v, int position);
    }


    @NonNull
    @Override
    public FavoriteDepartmentAdapter.CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_favorite_department, parent, false);
        CustomViewHolder holder = new CustomViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull FavoriteDepartmentAdapter.CustomViewHolder holder, int position) {

        holder.tv_item_favorite_department_name.setText(department_item_list.get(position).getDepartment());

        holder.itemView.setTag(position);

        /**
         * click listener 달 거면 여기에 달 것
         * */
    }

    @Override
    public int getItemCount() {
        return (null != department_item_list ? department_item_list.size() : 0);
    }


    public class CustomViewHolder extends RecyclerView.ViewHolder {

        protected TextView tv_item_favorite_department_name;

        public CustomViewHolder(@NonNull final View itemView) {
            super(itemView);

            this.tv_item_favorite_department_name = itemView.findViewById(R.id.tv_item_favorite_department_name);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Context context = view.getContext();

                    int pos = getAdapterPosition();
                    if (pos != RecyclerView.NO_POSITION) {

//                        System.out.println("보내기 전 pos: " + pos);

                        // todo
                        // 그 학과의 게시판으로 보내야함!!!

                        Intent intent = new Intent(view.getContext(), DepartmentBoardActivity.class);
//                        intent.putExtra("index_of_this_post", department_item_list.get(pos).getDepartment_board_idx());

//                        intent.putExtra("index_of_this_post", post_item_list.get(pos).getContent_index());
//                        intent.putExtra("clicked_pos", pos);
//                        intent.putExtra("clicked_content_index", post_item_list.get(pos).getContent_index());
//
//                        System.out.println("보내기전 freeItem index: " + post_item_list.get(pos).getContent_index());

                        context.startActivity(intent);
//                        ((Activity) context).finish();

                        if (mListener != null) {
                            mListener.onItemClick(view, pos);
                        }
                    }
                }
            });
        }
    }


}


