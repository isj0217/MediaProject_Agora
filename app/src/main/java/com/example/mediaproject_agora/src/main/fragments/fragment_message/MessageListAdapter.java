package com.example.mediaproject_agora.src.main.fragments.fragment_message;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mediaproject_agora.R;
import com.example.mediaproject_agora.src.main.InMessageRoomActivity;
import com.example.mediaproject_agora.src.main.items.MessageItem;
import com.example.mediaproject_agora.src.main.items.MessageRoomItem;

import java.util.ArrayList;

public class MessageListAdapter extends RecyclerView.Adapter<MessageListAdapter.CustomViewHolder> {

    private ArrayList<MessageItem> message_item_list;

    private OnItemClickListener mListener = null;

    public MessageListAdapter(ArrayList<MessageItem> message_item_list) {
        this.message_item_list = message_item_list;
    }

    public interface OnItemClickListener {
        void onItemClick(View v, int position);
    }


    @NonNull
    @Override
    public MessageListAdapter.CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_message, parent, false);
        CustomViewHolder holder = new CustomViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MessageListAdapter.CustomViewHolder holder, int position) {

        holder.tv_item_message_send_or_receive.setText(message_item_list.get(position).getStatus());
        holder.tv_item_message_time.setText(message_item_list.get(position).getTime());
        holder.tv_item_message_content.setText(message_item_list.get(position).getContent());

        holder.itemView.setTag(position);
        /**
         * click listener 달 거면 여기에 달 것
         * */
    }

    @Override
    public int getItemCount() {
        return (null != message_item_list ? message_item_list.size() : 0);
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {

        protected TextView tv_item_message_send_or_receive;
        protected TextView tv_item_message_time;
        protected TextView tv_item_message_content;

        public CustomViewHolder(@NonNull final View itemView) {
            super(itemView);

            this.tv_item_message_send_or_receive = itemView.findViewById(R.id.tv_item_message_send_or_receive);
            this.tv_item_message_time = itemView.findViewById(R.id.tv_item_message_time);
            this.tv_item_message_content = itemView.findViewById(R.id.tv_item_message_content);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Context context = view.getContext();

                    int pos = getAdapterPosition();
                    if (pos != RecyclerView.NO_POSITION) {

//                        Intent intent = new Intent(view.getContext(), InMessageRoomActivity.class);
//                        intent.putExtra("nickname", message_room_item_list.get(pos).getUser_nickname());
//                        intent.putExtra("message_room_idx", message_room_item_list.get(pos).getMessage_room_idx());

//                        context.startActivity(intent);
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


