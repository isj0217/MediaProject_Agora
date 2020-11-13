package com.example.mediaproject_agora.src.main.fragments.fragment_agora;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mediaproject_agora.R;
import com.example.mediaproject_agora.src.main.InPostActivity;
import com.example.mediaproject_agora.src.main.items.PostItem;

import java.util.ArrayList;

public class SpecificBoardAdapter extends RecyclerView.Adapter<SpecificBoardAdapter.CustomViewHolder> {

    private ArrayList<PostItem> post_item_list;
    private OnItemClickListener mListener = null;

    public SpecificBoardAdapter(ArrayList<PostItem> post_item_list) {
        this.post_item_list = post_item_list;
    }

    public interface OnItemClickListener {
        void onItemClick(View v, int position);
    }


    @NonNull
    @Override
    public SpecificBoardAdapter.CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_summarized_department_post, parent, false);
        CustomViewHolder holder = new CustomViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull SpecificBoardAdapter.CustomViewHolder holder, int position) {
        holder.tv_item_post_title.setText(post_item_list.get(position).getTitle());
        holder.tv_item_post_content.setText(post_item_list.get(position).getContent());
        holder.tv_item_post_time.setText(post_item_list.get(position).getTime());
        holder.tv_item_post_nickname.setText(post_item_list.get(position).getWriter());
        holder.tv_item_post_like_num.setText(Integer.toString(post_item_list.get(position).getLike_num()));
        holder.tv_item_post_comment_num.setText(Integer.toString(post_item_list.get(position).getComment_num()));

        holder.itemView.setTag(position);

        /**
         * click listener 달 거면 여기에 달 것
         * */
    }

    @Override
    public int getItemCount() {
        return (null != post_item_list ? post_item_list.size() : 0);
    }


    public class CustomViewHolder extends RecyclerView.ViewHolder {

        protected int content_index;
        protected TextView tv_item_post_title;
        protected TextView tv_item_post_content;
        protected TextView tv_item_post_time;
        protected TextView tv_item_post_nickname;
        protected TextView tv_item_post_like_num;
        protected TextView tv_item_post_comment_num;

        public CustomViewHolder(@NonNull final View itemView) {
            super(itemView);
            this.tv_item_post_title = itemView.findViewById(R.id.tv_item_post_title);
            this.tv_item_post_content = itemView.findViewById(R.id.tv_item_post_content);
            this.tv_item_post_time = itemView.findViewById(R.id.tv_item_post_time);
            this.tv_item_post_nickname = itemView.findViewById(R.id.tv_item_post_nickname);
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


