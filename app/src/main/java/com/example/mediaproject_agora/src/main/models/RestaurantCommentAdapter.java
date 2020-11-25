package com.example.mediaproject_agora.src.main.models;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mediaproject_agora.R;
import com.example.mediaproject_agora.src.main.InPostService;
import com.example.mediaproject_agora.src.main.InRestaurantPostService;
import com.example.mediaproject_agora.src.main.fragments.fragment_restaurant.RestaurantResponse;
import com.example.mediaproject_agora.src.main.interfaces.InPostActivityView;
import com.example.mediaproject_agora.src.main.interfaces.InRestaurantPostActivityView;
import com.example.mediaproject_agora.src.main.items.CommentItem;
import com.example.mediaproject_agora.src.main.items.RestaurantCommentItem;

import java.util.ArrayList;

public class RestaurantCommentAdapter extends RecyclerView.Adapter<RestaurantCommentAdapter.CustomViewHolder> implements InRestaurantPostActivityView {

    private ArrayList<RestaurantCommentItem> restaurant_comment_item_list;
//    private OnItemClickListener mListener = null ;

    private Context adapter_context;

    public RestaurantCommentAdapter(ArrayList<RestaurantCommentItem> restaurant_comment_item_list, Context adapter_context) {
        this.restaurant_comment_item_list = restaurant_comment_item_list;
        this.adapter_context = adapter_context;
    }

    //    public interface OnItemClickListener {
//        void onItemClick(View v, int position);
//    }

    @NonNull
    @Override
    public RestaurantCommentAdapter.CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_restaurant_comment, parent, false);
        CustomViewHolder holder = new CustomViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final RestaurantCommentAdapter.CustomViewHolder holder, int position) {
        holder.tv_item_restaurant_comment_restaurant_comment_index.setText(Integer.toString(restaurant_comment_item_list.get(position).getComment_idx()));
        holder.tv_item_restaurant_comment_content.setText(restaurant_comment_item_list.get(position).getComment_content());
        holder.tv_item_restaurant_comment_time.setText(restaurant_comment_item_list.get(position).getTime());
        holder.tv_item_restaurant_comment_nickname.setText(restaurant_comment_item_list.get(position).getNickname());

        holder.itemView.setTag(position);

        /**
         * click 리스너 달 거면 여기에 달 것
         * */

        holder.iv_item_restaurant_comment_trash_can.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                new AlertDialog.Builder(view.getContext())
                        .setTitle("댓글 삭제")
                        .setMessage("댓글을 삭제하시겠습니까?")
                        .setPositiveButton("예", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // todo
                                // 댓글 삭제 API 구현

//                                Toast.makeText(view.getContext(), holder.tv_item_comment_department_comment_index.getText().toString(), Toast.LENGTH_SHORT).show();

                                tryDeleteRestaurantComment(Integer.parseInt(holder.tv_item_restaurant_comment_restaurant_comment_index.getText().toString()));
                            }
                        })
                        .setNegativeButton("아니오", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        })
                        .setCancelable(false)
                        .show();
            }
        });
    }

    public void tryDeleteRestaurantComment(int restaurant_comment_idx) {

        final InRestaurantPostService inRestaurantPostService = new InRestaurantPostService(this);
        inRestaurantPostService.deleteRestaurantComment(restaurant_comment_idx);

    }

    @Override
    public int getItemCount() {
        return (null != restaurant_comment_item_list ? restaurant_comment_item_list.size() : 0);
    }

    @Override
    public void validateSuccess(String text) {

    }

    @Override
    public void validateFailure(String message) {

    }

    @Override
    public void getRestaurantPostSuccess(RestaurantResponse restaurantResponse) {

    }

    @Override
    public void getRestaurantCommentSuccess(InRestaurantPostCommentResponse inRestaurantPostCommentResponse) {

    }

    @Override
    public void postRestaurantCommentSuccess(DefaultResponse defaultResponse) {

    }

    @Override
    public void deleteRestaurantCommentSuccess(DefaultResponse defaultResponse) {
        switch (defaultResponse.getCode()) {
            default:
                System.out.println(defaultResponse.getMessage());
                break;
        }
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {

        protected ImageView iv_item_restaurant_comment_trash_can;
        protected TextView tv_item_restaurant_comment_restaurant_comment_index;
        protected TextView tv_item_restaurant_comment_content;
        protected TextView tv_item_restaurant_comment_time;
        protected TextView tv_item_restaurant_comment_nickname;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);

            this.iv_item_restaurant_comment_trash_can = itemView.findViewById(R.id.iv_item_restaurant_comment_trash_can);
            this.tv_item_restaurant_comment_restaurant_comment_index = itemView.findViewById(R.id.tv_item_restaurant_comment_restaurant_comment_index);
            this.tv_item_restaurant_comment_content = itemView.findViewById(R.id.tv_item_restaurant_comment_content);
            this.tv_item_restaurant_comment_time = itemView.findViewById(R.id.tv_item_restaurant_comment_time);
            this.tv_item_restaurant_comment_nickname = itemView.findViewById(R.id.tv_item_restaurant_comment_nickname);
        }
    }
}
