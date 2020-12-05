package com.example.mediaproject_agora.src.main.models;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mediaproject_agora.R;
import com.example.mediaproject_agora.src.ApplicationClass;
import com.example.mediaproject_agora.src.main.DepartmentBoardActivity;
import com.example.mediaproject_agora.src.main.InPostActivity;
import com.example.mediaproject_agora.src.main.InPostService;
import com.example.mediaproject_agora.src.main.WritingUsedProductDepartmentActivity;
import com.example.mediaproject_agora.src.main.interfaces.InPostActivityView;
import com.example.mediaproject_agora.src.main.items.CommentItem;

import java.util.ArrayList;

import static android.view.View.GONE;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.CustomViewHolder> implements InPostActivityView {

    private ArrayList<CommentItem> comment_item_list;
//    private OnItemClickListener mListener = null ;

    private Context adapter_context;

    public CommentAdapter(ArrayList<CommentItem> comment_item_list, Context context) {
        this.comment_item_list = comment_item_list;
        this.adapter_context = context;
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
    public void onBindViewHolder(@NonNull final CommentAdapter.CustomViewHolder holder, int position) {

        holder.tv_item_comment_is_mine.setText((Integer.toString(comment_item_list.get(position).getIs_mine())));

        holder.tv_item_comment_department_comment_index.setText(Integer.toString(comment_item_list.get(position).getDepartment_comment_idx()));
        holder.tv_item_comment_content.setText(comment_item_list.get(position).getComment());
        holder.tv_item_comment_time.setText(comment_item_list.get(position).getTime());
        holder.tv_item_comment_nickname.setText(comment_item_list.get(position).getNickname());

        holder.itemView.setTag(position);

        if (holder.tv_item_comment_is_mine.getText().toString().equals("0")) {
            holder.iv_item_comment_trash_can.setVisibility(GONE);
        }

        /**
         * click 리스너 달 거면 여기에 달 것
         * */

        holder.iv_item_comment_trash_can.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                new AlertDialog.Builder(view.getContext())
                        .setTitle("댓글 삭제")
                        .setMessage("댓글을 삭제하시겠습니까?")
                        .setPositiveButton("예", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // todo
                                // 댓글 삭제 API 구현

                                // 1. 어차피 자기가 쓴 댓글일 때만 쓰레기통 모양이 보여 삭제가 가능하므로 일단 댓글 통째로 안보이게 없애버리기
                                holder.ll_item_comment.setVisibility(GONE);
                                holder.view_item_comment_underline.setVisibility(GONE);

                                // 2. 댓글 갯수 하나 줄이기
                                // like_num은 어댑터가 아니라 액티비티 내에 있는데 어떻게 바꾸지??

                                tryDeleteDepartmentComment(Integer.parseInt(holder.tv_item_comment_department_comment_index.getText().toString()));
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

    public void tryDeleteDepartmentComment(int department_comment_idx) {

        final InPostService inPostService = new InPostService(this);
        inPostService.deleteDepartmentComment(department_comment_idx);

    }

    @Override
    public int getItemCount() {
        return (null != comment_item_list ? comment_item_list.size() : 0);
    }

    @Override
    public void validateSuccess(String text) {

    }

    @Override
    public void validateFailure(String message) {

    }

    @Override
    public void getDepartmentPostSuccess(InPostPostResponse inPostPostResponse) {

    }

    @Override
    public void getSpecificDepartmentCommentSuccess(InPostCommentResponse inPostCommentResponse) {

    }

    @Override
    public void postDepartmentCommentSuccess(DefaultResponse defaultResponse) {

    }

    @Override
    public void patchThumbUpDepartmentPostSuccess(DefaultResponse defaultResponse) {

    }

    @Override
    public void deleteDepartmentPostSuccess(DefaultResponse defaultResponse) {

    }

    @Override
    public void deleteDepartmentCommentSuccess(DefaultResponse defaultResponse) {

        switch (defaultResponse.getCode()){

            case 100:
                Toast.makeText(adapter_context, "댓글 삭제 완료", Toast.LENGTH_SHORT).show();
//                Intent intent = new Intent(adapter_context, DepartmentBoardActivity.class);
//                ((Application)adapter_context).startActivity(intent);

            default:
//                Toast.makeText(adapter_context, defaultResponse.getMessage(), Toast.LENGTH_SHORT).show();
                break;
        }
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {

        protected TextView tv_item_comment_is_mine;

        protected LinearLayout ll_item_comment;
        protected View view_item_comment_underline;

        protected ImageView iv_item_comment_trash_can;
        protected TextView tv_item_comment_department_comment_index;
        protected TextView tv_item_comment_content;
        protected TextView tv_item_comment_time;
        protected TextView tv_item_comment_nickname;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);

            this.tv_item_comment_is_mine = itemView.findViewById(R.id.tv_item_comment_is_mine);

            this.ll_item_comment = itemView.findViewById(R.id.ll_item_comment);
            this.view_item_comment_underline = itemView.findViewById(R.id.view_item_comment_underline);

            this.iv_item_comment_trash_can = itemView.findViewById(R.id.iv_item_comment_trash_can);
            this.tv_item_comment_department_comment_index = itemView.findViewById(R.id.tv_item_comment_department_comment_index);
            this.tv_item_comment_content = itemView.findViewById(R.id.tv_item_comment_content);
            this.tv_item_comment_time = itemView.findViewById(R.id.tv_item_comment_time);
            this.tv_item_comment_nickname = itemView.findViewById(R.id.tv_item_comment_nickname);
        }
    }
}
