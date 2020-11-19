package com.example.mediaproject_agora.src.main.fragments.fragment_agora;

import android.app.Activity;
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
import com.example.mediaproject_agora.src.main.InPostActivity;
import com.example.mediaproject_agora.src.main.items.DepartmentPostItem;

import java.util.ArrayList;

public class DepartmentBoardAdapter extends RecyclerView.Adapter<DepartmentBoardAdapter.CustomViewHolder> {

    private ArrayList<DepartmentPostItem> department_post_item_list;

    private OnItemClickListener mListener = null;

    public DepartmentBoardAdapter(ArrayList<DepartmentPostItem> department_post_item_list) {
        this.department_post_item_list = department_post_item_list;
    }

    public interface OnItemClickListener {
        void onItemClick(View v, int position);
    }


    @NonNull
    @Override
    public DepartmentBoardAdapter.CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_summarized_department_post, parent, false);
        CustomViewHolder holder = new CustomViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull DepartmentBoardAdapter.CustomViewHolder holder, int position) {

        holder.tv_item_post_dept_board_idx.setText(Integer.toString(department_post_item_list.get(position).getDepartment_board_idx()));
        holder.tv_item_post_title.setText(department_post_item_list.get(position).getTitle());
        holder.tv_item_post_content.setText(department_post_item_list.get(position).getContent());
        holder.tv_item_post_nickname.setText(department_post_item_list.get(position).getNickname());
        holder.tv_item_post_time.setText(department_post_item_list.get(position).getTime());
        if(department_post_item_list.get(position).getPhoto_status() == 0){
            holder.iv_item_post_photo_status.setVisibility(View.INVISIBLE);
        } else{
            holder.iv_item_post_photo_status.setVisibility(View.VISIBLE);
        }
        holder.tv_item_post_like_num.setText(Integer.toString(department_post_item_list.get(position).getLike_num()));
        holder.tv_item_post_comment_num.setText(Integer.toString(department_post_item_list.get(position).getComment_num()));

        holder.itemView.setTag(position);

        /**
         * click listener 달 거면 여기에 달 것
         * */
    }

    @Override
    public int getItemCount() {
        return (null != department_post_item_list ? department_post_item_list.size() : 0);
    }


    public class CustomViewHolder extends RecyclerView.ViewHolder {

        protected TextView tv_item_post_dept_board_idx;
        protected TextView tv_item_post_title;
        protected TextView tv_item_post_content;
        protected TextView tv_item_post_nickname;
        protected TextView tv_item_post_time;
        protected ImageView iv_item_post_photo_status;
        protected TextView tv_item_post_like_num;
        protected TextView tv_item_post_comment_num;

        public CustomViewHolder(@NonNull final View itemView) {
            super(itemView);

            this.tv_item_post_dept_board_idx = itemView.findViewById(R.id.tv_item_post_dept_board_idx_num);
            this.tv_item_post_title = itemView.findViewById(R.id.tv_item_post_title);
            this.tv_item_post_content = itemView.findViewById(R.id.tv_item_post_content);
            this.tv_item_post_nickname = itemView.findViewById(R.id.tv_item_post_nickname);
            this.tv_item_post_time = itemView.findViewById(R.id.tv_item_post_time);
            this.iv_item_post_photo_status = itemView.findViewById(R.id.iv_item_post_photo_status);
            this.tv_item_post_like_num = itemView.findViewById(R.id.tv_item_post_like_num);
            this.tv_item_post_comment_num = itemView.findViewById(R.id.tv_item_post_comment_num);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Context context = view.getContext();

                    int pos = getAdapterPosition();
                    if (pos != RecyclerView.NO_POSITION) {

//                        System.out.println("보내기 전 pos: " + pos);

                        Intent intent = new Intent(view.getContext(), InPostActivity.class);
                        intent.putExtra("index_of_this_post", department_post_item_list.get(pos).getDepartment_board_idx());

//                        intent.putExtra("index_of_this_post", post_item_list.get(pos).getContent_index());
//                        intent.putExtra("clicked_pos", pos);
//                        intent.putExtra("clicked_content_index", post_item_list.get(pos).getContent_index());
//
//                        System.out.println("보내기전 freeItem index: " + post_item_list.get(pos).getContent_index());

                        context.startActivity(intent);
                        ((Activity) context).finish();

                        if (mListener != null) {
                            mListener.onItemClick(view, pos);
                        }
                    }
                }
            });
        }
    }


}


