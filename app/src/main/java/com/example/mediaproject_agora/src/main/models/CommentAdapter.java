package com.example.mediaproject_agora.src.main.models;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mediaproject_agora.R;
import com.example.mediaproject_agora.src.main.items.CommentItem;

import java.util.ArrayList;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.CustomViewHolder> {

    private ArrayList<CommentItem> comment_item_list;
//    private OnItemClickListener mListener = null ;

    public CommentAdapter(ArrayList<CommentItem> comment_item_list) {
        this.comment_item_list = comment_item_list;
    }

//    public interface OnItemClickListener {
//        void onItemClick(View v, int position);
//    }

    @NonNull
    @Override
    public CommentAdapter.CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_comment, parent, false);
        CustomViewHolder holder = new CustomViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull CommentAdapter.CustomViewHolder holder, int position) {
        holder.tv_item_comment_department_comment_index.setText(Integer.toString(comment_item_list.get(position).getDepartment_comment_idx()));
        holder.tv_item_comment_content.setText(comment_item_list.get(position).getComment());
        holder.tv_item_comment_time.setText(comment_item_list.get(position).getTime());
        holder.tv_item_comment_nickname.setText(comment_item_list.get(position).getNickname());

        holder.itemView.setTag(position);

        /**
         * click 리스너 달 거면 여기에 달 것
         * */

    }

    @Override
    public int getItemCount() {
        return (null != comment_item_list ? comment_item_list.size() : 0);
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {

        protected TextView tv_item_comment_department_comment_index;
        protected TextView tv_item_comment_content;
        protected TextView tv_item_comment_time;
        protected TextView tv_item_comment_nickname;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);

            this.tv_item_comment_department_comment_index = itemView.findViewById(R.id.tv_item_comment_department_comment_index);
            this.tv_item_comment_content = itemView.findViewById(R.id.tv_item_comment_content);
            this.tv_item_comment_time = itemView.findViewById(R.id.tv_item_comment_time);
            this.tv_item_comment_nickname = itemView.findViewById(R.id.tv_item_comment_nickname);
        }
    }
}
