package com.example.mediaproject_agora.src.main.fragments.fragment_message;

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
import com.example.mediaproject_agora.src.main.items.MessageRoomItem;

import java.util.ArrayList;

public class MessageRoomAdapter extends RecyclerView.Adapter<MessageRoomAdapter.CustomViewHolder> {

    private ArrayList<MessageRoomItem> message_room_item_list;

    private OnItemClickListener mListener = null;

    public MessageRoomAdapter(ArrayList<MessageRoomItem> message_room_item_list) {
        this.message_room_item_list = message_room_item_list;
    }

    public interface OnItemClickListener {
        void onItemClick(View v, int position);
    }


    @NonNull
    @Override
    public MessageRoomAdapter.CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_message_room, parent, false);
        CustomViewHolder holder = new CustomViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MessageRoomAdapter.CustomViewHolder holder, int position) {

        holder.tv_item_message_room_idx.setText(Integer.toString(message_room_item_list.get(position).getMessage_room_idx()));
        holder.tv_item_message_user_idx.setText(Integer.toString(message_room_item_list.get(position).getUser_idx()));
        holder.tv_item_message_user_nickname.setText(message_room_item_list.get(position).getUser_nickname());
        holder.tv_item_message_message_idx.setText(Integer.toString(message_room_item_list.get(position).getMessage_idx()));
        holder.tv_item_message_content.setText(message_room_item_list.get(position).getContent());
        holder.tv_item_message_time.setText(message_room_item_list.get(position).getTime());

        holder.itemView.setTag(position);
        /**
         * click listener 달 거면 여기에 달 것
         * */
    }

    @Override
    public int getItemCount() {
        return (null != message_room_item_list ? message_room_item_list.size() : 0);
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {

        protected TextView tv_item_message_user_nickname;
        protected TextView tv_item_message_time;
        protected TextView tv_item_message_content;
        protected TextView tv_item_message_room_idx;
        protected TextView tv_item_message_user_idx;
        protected TextView tv_item_message_message_idx;

        public CustomViewHolder(@NonNull final View itemView) {
            super(itemView);

            this.tv_item_message_user_nickname = itemView.findViewById(R.id.tv_item_message_user_nickname);
            this.tv_item_message_time = itemView.findViewById(R.id.tv_item_message_time);
            this.tv_item_message_content = itemView.findViewById(R.id.tv_item_message_content);
            this.tv_item_message_room_idx = itemView.findViewById(R.id.tv_item_message_room_idx);
            this.tv_item_message_user_idx = itemView.findViewById(R.id.tv_item_message_user_idx);
            this.tv_item_message_message_idx = itemView.findViewById(R.id.tv_item_message_message_idx);
        }
    }
}


//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//
//                    Context context = view.getContext();
//
//                    int pos = getAdapterPosition();
//                    if (pos != RecyclerView.NO_POSITION) {
//
//                        Intent intent = new Intent(view.getContext(), InPostActivity.class);
//
////                        intent.putExtra("index_of_this_post", post_item_list.get(pos).getContent_index());
////                        intent.putExtra("clicked_pos", pos);
////                        intent.putExtra("clicked_content_index", post_item_list.get(pos).getContent_index());
////
////                        System.out.println("보내기전 freeItem index: " + post_item_list.get(pos).getContent_index());
//
//                        context.startActivity(intent);
//                        ((Activity) context).finish();
//
//                        if (mListener != null) {
//                            mListener.onItemClick(view, pos);
//                        }
//                    }
//                }
//            });
//        }
//    }
//
//
//}


