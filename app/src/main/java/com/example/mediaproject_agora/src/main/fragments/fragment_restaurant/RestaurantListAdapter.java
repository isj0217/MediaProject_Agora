package com.example.mediaproject_agora.src.main.fragments.fragment_restaurant;

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
import com.example.mediaproject_agora.src.main.items.DepartmentItem;
import com.example.mediaproject_agora.src.main.items.RestaurantItem;

import java.util.ArrayList;

public class RestaurantListAdapter extends RecyclerView.Adapter<RestaurantListAdapter.CustomViewHolder> {

    private ArrayList<RestaurantItem> restaurant_item_list;

    private OnItemClickListener mListener = null;

    public RestaurantListAdapter(ArrayList<RestaurantItem> restaurant_item_list) {
        this.restaurant_item_list = restaurant_item_list;
    }

    public interface OnItemClickListener {
        void onItemClick(View v, int position);
    }


    @NonNull
    @Override
    public RestaurantListAdapter.CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_summarized_restaurant_post, parent, false);
        CustomViewHolder holder = new CustomViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RestaurantListAdapter.CustomViewHolder holder, int position) {

        // todo
        // String으로 받아온 것을 어떻게 사진으로 바꿔 ImageView에 넣을 것인가
//        holder.iv_item_restaurant_photo.setImageResource(restaurant_item_list.get(position).getMenu_picture());

        holder.tv_item_restaurant_name.setText(restaurant_item_list.get(position).getTastehouse_name());
        holder.tv_item_restaurant_star.setText(Float.toString(restaurant_item_list.get(position).getTastehouse_star()));
        holder.tv_item_restaurant_menu_name.setText(restaurant_item_list.get(position).getMenu_name());
        holder.tv_item_restaurant_price.setText(Integer.toString(restaurant_item_list.get(position).getMenu_price()));
        holder.tv_item_restaurant_content.setText(restaurant_item_list.get(position).getTastehouse_content());
        holder.tv_item_restaurant_time.setText(restaurant_item_list.get(position).getTime());
        holder.tv_item_restaurant_nickname.setText(restaurant_item_list.get(position).getNickname());
        holder.tv_item_restaurant_idx_of_restaurant.setText(Integer.toString(restaurant_item_list.get(position).getTastehouse_idx()));
        holder.tv_item_restaurant_comment_num.setText(Integer.toString(restaurant_item_list.get(position).getComment_num()));

        holder.itemView.setTag(position);

        /**
         * click listener 달 거면 여기에 달 것
         * */
    }

    @Override
    public int getItemCount() {
        return (null != restaurant_item_list ? restaurant_item_list.size() : 0);
    }


    public class CustomViewHolder extends RecyclerView.ViewHolder {

        protected ImageView iv_item_restaurant_photo;

        protected TextView tv_item_restaurant_name;
        protected TextView tv_item_restaurant_star;
        protected TextView tv_item_restaurant_menu_name;
        protected TextView tv_item_restaurant_price;
        protected TextView tv_item_restaurant_content;
        protected TextView tv_item_restaurant_time;
        protected TextView tv_item_restaurant_nickname;
        protected TextView tv_item_restaurant_idx_of_restaurant;
        protected TextView tv_item_restaurant_comment_num;


        public CustomViewHolder(@NonNull final View itemView) {
            super(itemView);

            this.iv_item_restaurant_photo = itemView.findViewById(R.id.iv_item_restaurant_photo);
            this.tv_item_restaurant_name = itemView.findViewById(R.id.tv_item_restaurant_name);
            this.tv_item_restaurant_star = itemView.findViewById(R.id.tv_item_restaurant_star);
            this.tv_item_restaurant_menu_name = itemView.findViewById(R.id.tv_item_restaurant_menu_name);
            this.tv_item_restaurant_price = itemView.findViewById(R.id.tv_item_restaurant_price);
            this.tv_item_restaurant_content = itemView.findViewById(R.id.tv_item_restaurant_content);
            this.tv_item_restaurant_time = itemView.findViewById(R.id.tv_item_restaurant_time);
            this.tv_item_restaurant_nickname = itemView.findViewById(R.id.tv_item_restaurant_nickname);
            this.tv_item_restaurant_idx_of_restaurant = itemView.findViewById(R.id.tv_item_restaurant_idx_of_restaurant);
            this.tv_item_restaurant_comment_num = itemView.findViewById(R.id.tv_item_restaurant_comment_num);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Context context = view.getContext();

                    int pos = getAdapterPosition();
                    if (pos != RecyclerView.NO_POSITION) {

//                        Intent intent = new Intent(view.getContext(), InRestaurantPostActivity.class);


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


