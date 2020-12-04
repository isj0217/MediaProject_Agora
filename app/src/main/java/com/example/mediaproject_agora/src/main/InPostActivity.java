package com.example.mediaproject_agora.src.main;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
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
import com.example.mediaproject_agora.src.main.fragments.fragment_mypage.FragmentMyPage;
import com.example.mediaproject_agora.src.main.interfaces.InPostActivityView;
import com.example.mediaproject_agora.src.main.items.CommentItem;
import com.example.mediaproject_agora.src.main.models.CommentAdapter;
import com.example.mediaproject_agora.src.main.models.DefaultResponse;
import com.example.mediaproject_agora.src.main.models.InPostCommentResponse;
import com.example.mediaproject_agora.src.main.models.InPostPostResponse;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;


public class InPostActivity extends BaseActivity implements InPostActivityView, PopupMenu.OnMenuItemClickListener {

    private String section_in_agora;
    private String category_name;

    private String department;

    private ArrayList<CommentItem> m_comment_item_list;

    private CommentAdapter comment_adapter;

    private RecyclerView rv_in_post_comment;
    private LinearLayoutManager linear_layout_manager;

    private TextView tv_in_post_user_idx, tv_in_post_department_board_idx, tv_in_post_nickname, tv_in_post_time, tv_in_post_title,
            tv_in_post_content, tv_in_post_like_num, tv_in_post_comment_num;

    // 댓글에 담긴 TextView 4개
    private TextView tv_item_comment_content, tv_item_comment_time, tv_item_comment_nickname, tv_item_comment_department_comment_index;

    private ImageView iv_in_post_user_picture, iv_in_post_photo;

    private EditText et_in_post_comment;
    private ImageView iv_in_post_register_comment;

    private int index_of_this_post;

    private LinearLayout ll_in_post_like_btn;
    private ImageView iv_in_post_thumb_up;
    private TextView tv_in_post_thumb_up;

    private int is_mine;

    private ImageView iv_in_post_more;

    private Intent intent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_in_department_post);

        ll_in_post_like_btn = findViewById(R.id.ll_in_post_like_btn);
        iv_in_post_thumb_up = findViewById(R.id.iv_in_post_thumb_up);
        tv_in_post_thumb_up = findViewById(R.id.tv_in_post_thumb_up);
        iv_in_post_more = findViewById(R.id.iv_in_post_more);


        index_of_this_post = getIntent().getExtras().getInt("index_of_this_post", 0);

        tryGetSpecificDepartmentPost(index_of_this_post);

        tryGetSpecificDepartmentComment(index_of_this_post);


        m_comment_item_list = new ArrayList<>();

        comment_adapter = new CommentAdapter(m_comment_item_list, getApplicationContext());
        rv_in_post_comment = findViewById(R.id.rv_board_comment_list);

        linear_layout_manager = new LinearLayoutManager(getApplicationContext());
        rv_in_post_comment.setLayoutManager(linear_layout_manager);

        rv_in_post_comment.setAdapter(comment_adapter);


        bindViews();

        setClickListenersToButtons();

    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    public void setClickListenersToButtons() {

        // 댓글 등록 버튼
        iv_in_post_register_comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(InPostActivity.this) // TestActivity 부분에는 현재 Activity의 이름 입력.
                        .setTitle("댓글 작성 완료")
                        .setMessage("댓글을 등록하시겠습니까?")
                        .setPositiveButton("예", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {

                                // todo
                                // 서버에 댓글 등록 해야함!!

                                int department_board_idx = Integer.parseInt(tv_in_post_department_board_idx.getText().toString());

                                System.out.println("몇번 idx에 달 것인가???? " + department_board_idx);

                                tryPostDepartmentComment(department_board_idx, et_in_post_comment.getText().toString());

//                                restartActivity(InPostActivity.this);

                                Intent intent = new Intent(InPostActivity.this, DepartmentBoardActivity.class);
                                startActivity(intent);
                                finish();
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

        // 게시물 좋아요 버튼
        ll_in_post_like_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (is_mine == 1) {
                    Toast.makeText(InPostActivity.this, "자신의 글에는 공감할 수 없습니다", Toast.LENGTH_SHORT).show();
                } else {
                    tryPatchThumbUpDepartmentPost(Integer.parseInt(tv_in_post_department_board_idx.getText().toString()));
                }
            }
        });

    }

    private void restartActivity(Activity activity) {
        Intent intent = new Intent();
        intent.setClass(activity, activity.getClass());
        activity.startActivity(intent);
        activity.finish();
    }

    public void loadRecentSectionAndCategory() {
        SharedPreferences sharedPreferences = getSharedPreferences("recent_section_and_category", MODE_PRIVATE);
        section_in_agora = sharedPreferences.getString("section_in_agora", "SECTION 불러오기 실패");
        category_name = sharedPreferences.getString("category_name", "CATEGORY 불러오기 실패");
    }

    private void tryPostDepartmentComment(int department_board_idx, String comment) {
        showProgressDialog();

        HashMap<String, Object> params = new HashMap<>();
        params.put("comment", comment);

        final InPostService inPostService = new InPostService(this, params);
        inPostService.postDepartmentComment(department_board_idx);
    }

    private void tryGetSpecificDepartmentPost(int department_board_idx) {
        showProgressDialog();

        final InPostService inPostService = new InPostService(this);
        inPostService.getSpecificDepartmentPost(department_board_idx);
    }

    private void tryGetSpecificDepartmentComment(int department_board_idx) {
        showProgressDialog();

        final InPostService inPostService = new InPostService(this);
        inPostService.getSpecificDepartmentComments(department_board_idx);
    }

    private void tryPatchThumbUpDepartmentPost(int department_board_idx) {
        showProgressDialog();

        final InPostService inPostService = new InPostService(this);
        inPostService.patchThumbUpDepartmentPost(department_board_idx);
    }

    private void tryDeleteDepartmentPost(int department_board_idx) {
        showProgressDialog();

        final InPostService inPostService = new InPostService(this);
        inPostService.deleteDepartmentPost(department_board_idx);
    }


    public void bindViews() {

        // 게시글 레이아웃
        tv_in_post_user_idx = findViewById(R.id.tv_in_post_user_idx);
        iv_in_post_user_picture = findViewById(R.id.iv_in_post_user_picture);
        tv_in_post_department_board_idx = findViewById(R.id.tv_in_post_department_board_idx);
        tv_in_post_title = findViewById(R.id.tv_in_post_title);
        tv_in_post_content = findViewById(R.id.tv_in_post_content);
        tv_in_post_nickname = findViewById(R.id.tv_in_post_nickname);
        tv_in_post_time = findViewById(R.id.tv_in_post_time);
        iv_in_post_photo = findViewById(R.id.iv_in_post_photo);
        tv_in_post_like_num = findViewById(R.id.tv_in_post_like_num);
        tv_in_post_comment_num = findViewById(R.id.tv_in_post_comment_num);

        // 댓글 레이아웃
        tv_item_comment_content = findViewById(R.id.tv_item_comment_content);
        tv_item_comment_time = findViewById(R.id.tv_item_comment_time);
        tv_item_comment_nickname = findViewById(R.id.tv_item_comment_nickname);
        tv_item_comment_department_comment_index = findViewById(R.id.tv_item_comment_department_comment_index);

        et_in_post_comment = findViewById(R.id.et_in_post_comment);
        iv_in_post_register_comment = findViewById(R.id.iv_in_post_register_comment);
    }


    @Override
    public void validateSuccess(String text) {

    }

    @Override
    public void validateFailure(String message) {

    }

    @Override
    public void getDepartmentPostSuccess(InPostPostResponse inPostPostResponse) {
        hideProgressDialog();

        switch (inPostPostResponse.getCode()) {
            case 100:

                tv_in_post_user_idx.setText(Integer.toString(inPostPostResponse.getInPostPostResult().getUser_idx()));

                // todo
                // String으로 받아온 사진 어떻게 띄울 것인지 생각해보기

                final String in_post_photo = inPostPostResponse.getInPostPostResult().getPhoto();
                iv_in_post_photo.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(in_post_photo));
                        startActivity(intent);
                    }
                });

                new DownloadPhotoTask().execute(in_post_photo);

                tv_in_post_department_board_idx.setText(Integer.toString(inPostPostResponse.getInPostPostResult().getDepartment_board_idx()));
                tv_in_post_title.setText(inPostPostResponse.getInPostPostResult().getTitle());
                tv_in_post_content.setText(inPostPostResponse.getInPostPostResult().getContent());
                tv_in_post_nickname.setText(inPostPostResponse.getInPostPostResult().getNickname());
                tv_in_post_time.setText(inPostPostResponse.getInPostPostResult().getTime());
                // todo
                // String으로 받아온 사진 어떻게 띄울 것인지 생각해보기

                final String in_post_profile_photo = inPostPostResponse.getInPostPostResult().getUser_picture();
                iv_in_post_user_picture.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(in_post_profile_photo));
                        startActivity(intent);
                    }
                });

                new DownloadProfilePhotoTask().execute(in_post_profile_photo);

//                iv_in_post_photo.setImageResource(inPostPostResponse.getInPostPostResult().getPhoto());
                tv_in_post_like_num.setText(Integer.toString(inPostPostResponse.getInPostPostResult().getLike_num()));
                tv_in_post_comment_num.setText(Integer.toString(inPostPostResponse.getInPostPostResult().getComment_num()));

                if (inPostPostResponse.getInPostPostResult().getLike_status() == 1) {
                    ll_in_post_like_btn.setBackgroundResource(R.drawable.blue_btn_with_light_grey_border);
                    iv_in_post_thumb_up.setImageResource(R.drawable.thumb_up_white);
                    tv_in_post_thumb_up.setTextColor(getResources().getColor(R.color.white));
                }

                if (inPostPostResponse.getInPostPostResult().getIs_mine() == 1) {
                    is_mine = 1;
                } else {
                    is_mine = 0;
                }


                break;

            default:
                break;
        }
    }

    private class DownloadPhotoTask extends AsyncTask<String, Void, Bitmap> {
        @Override
        protected Bitmap doInBackground(String... strings) {
            Bitmap bmp = null;
            try {
                String img_url = strings[0]; //url of the image
                URL url = new URL(img_url);
                bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
            return bmp;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }


        @Override
        protected void onPostExecute(Bitmap result) {
            // doInBackground 에서 받아온 total 값 사용 장소
            iv_in_post_photo.setImageBitmap(result);
        }
    }

    private class DownloadProfilePhotoTask extends AsyncTask<String, Void, Bitmap> {
        @Override
        protected Bitmap doInBackground(String... strings) {
            Bitmap bmp = null;
            try {
                String img_url = strings[0]; //url of the image
                URL url = new URL(img_url);
                bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
            return bmp;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }


        @Override
        protected void onPostExecute(Bitmap result) {
            // doInBackground 에서 받아온 total 값 사용 장소
            iv_in_post_user_picture.setImageBitmap(result);
        }
    }

    @Override
    public void getSpecificDepartmentCommentSuccess(InPostCommentResponse inPostCommentResponse) {
        hideProgressDialog();

        switch (inPostCommentResponse.getCode()) {
            case 100:

                /**
                 * CommentItem 형식의 ArrayList에 모두 넣어두고 어댑터를 이용해서 하나하나 레이아웃에 갖다 붙이자!!
                 * */

                int num_of_comments_in_the_post = inPostCommentResponse.getInPostCommentResults().size();

                for (int i = 0; i < num_of_comments_in_the_post; i++) {
                    CommentItem commentItem = new CommentItem();

                    commentItem.setComment(inPostCommentResponse.getInPostCommentResults().get(i).getComment());
                    commentItem.setTime(inPostCommentResponse.getInPostCommentResults().get(i).getTime());
                    commentItem.setNickname(inPostCommentResponse.getInPostCommentResults().get(i).getNickname());
                    commentItem.setDepartment_comment_idx(inPostCommentResponse.getInPostCommentResults().get(i).getDepartment_board_idx());

                    m_comment_item_list.add(commentItem);
                }
                comment_adapter.notifyDataSetChanged();

                break;

            default:
                break;
        }
    }

    @Override
    public void postDepartmentCommentSuccess(DefaultResponse defaultResponse) {
        hideProgressDialog();

        switch (defaultResponse.getCode()) {
            case 100:
                System.out.println("댓글 달기 성공???????");
        }
    }

    @Override
    public void patchThumbUpDepartmentPostSuccess(DefaultResponse defaultResponse) {
        hideProgressDialog();

        int like_num;

        switch (defaultResponse.getCode()) {
            case 100:
                ll_in_post_like_btn.setBackgroundResource(R.drawable.blue_btn_with_light_grey_border);
                iv_in_post_thumb_up.setImageResource(R.drawable.thumb_up_white);
                tv_in_post_thumb_up.setTextColor(getResources().getColor(R.color.white));
                showCustomToast("이 게시물을 공감하였습니다.");

                like_num = Integer.parseInt(tv_in_post_like_num.getText().toString());
                like_num++;
                tv_in_post_like_num.setText(Integer.toString(like_num));
                break;

            case 101:
                ll_in_post_like_btn.setBackgroundResource(R.drawable.white_btn_with_light_grey_border);
                iv_in_post_thumb_up.setImageResource(R.drawable.thumb_up);
                tv_in_post_thumb_up.setTextColor(getResources().getColor(R.color.black));
                showCustomToast(defaultResponse.getMessage());
                showCustomToast("이 게시물의 공감을 취소하였습니다.");

                like_num = Integer.parseInt(tv_in_post_like_num.getText().toString());
                like_num--;
                tv_in_post_like_num.setText(Integer.toString(like_num));
                break;

            default:
                System.out.println(defaultResponse.getMessage());
                break;
        }
    }

    @Override
    public void deleteDepartmentPostSuccess(DefaultResponse defaultResponse) {
        hideProgressDialog();

        switch (defaultResponse.getCode()) {
            case 100:
                showCustomToast("게시글 삭제 완료");
                Intent intent = new Intent(InPostActivity.this, DepartmentBoardActivity.class);
                startActivity(intent);
                finish();
                break;

            default:
                Toast.makeText(this, defaultResponse.getMessage(), Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    public void deleteDepartmentCommentSuccess(DefaultResponse defaultResponse) {
        //Adapter에서 처리함
    }


    public void customOnClick(View view) {
        switch (view.getId()) {
            case R.id.btn_in_post_go_back:
                onBackPressed();
                break;
            case R.id.iv_in_post_more:
                showPopUp(view);
                break;
        }

    }

    public void showPopUp(View v) {
        PopupMenu popupMenu = new PopupMenu(this, v);

        popupMenu.setOnMenuItemClickListener(this);

        if (is_mine == 1) {
            popupMenu.inflate(R.menu.menu_in_post_mine);
            popupMenu.show();
        } else {
            popupMenu.inflate(R.menu.menu_in_post_not_mine);
            popupMenu.show();
        }
    }

    @Override
    public boolean onMenuItemClick(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.menu_in_post_fix_post:
                // todo
                // 일단 FixingActivity로 넘긴 후에 거기서 글 수정 API 엮기
                int idx_of_post_we_are_fixing = Integer.parseInt(tv_in_post_department_board_idx.getText().toString());

                intent = new Intent(InPostActivity.this, FixingDepartmentActivity.class);
                intent.putExtra("idx_of_post_we_are_fixing", idx_of_post_we_are_fixing);
                intent.putExtra("origin_title", tv_in_post_title.getText().toString());
                intent.putExtra("origin_content", tv_in_post_content.getText().toString());
                startActivity(intent);

                finish();

                break;
            case R.id.menu_in_post_delete_post:
                // todo
                // 글 삭제 API 엮기

                int idx_of_post_we_are_deleting = Integer.parseInt(tv_in_post_department_board_idx.getText().toString());

                tryDeleteDepartmentPost(idx_of_post_we_are_deleting);

                break;
            case R.id.menu_in_post_send_message:
                // todo
                // 추후에 쪽지보내기 기능 생겼을 때 만들기

                intent = new Intent(InPostActivity.this, SendingFirstMessageActivity.class);
                intent.putExtra("nickname", tv_in_post_nickname.getText().toString());
                intent.putExtra("user_idx", Integer.parseInt(tv_in_post_user_idx.getText().toString()));
                startActivity(intent);
//                finish();





                break;
        }


        return false;
    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
        loadRecentSectionAndCategory();
        if (section_in_agora.equals("department")) {
            Intent intent = new Intent(InPostActivity.this, DepartmentBoardActivity.class);
            startActivity(intent);
            finish();
        } else if (section_in_agora.equals("my_page")) {
            finish();
        }
    }
}
