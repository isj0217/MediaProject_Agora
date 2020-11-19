package com.example.mediaproject_agora.src.main;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;

import com.example.mediaproject_agora.R;
import com.example.mediaproject_agora.src.BaseActivity;
import com.example.mediaproject_agora.src.main.interfaces.FixingDepartmentActivityView;
import com.example.mediaproject_agora.src.main.interfaces.WritingDepartmentActivityView;
import com.example.mediaproject_agora.src.main.models.DefaultResponse;

import java.io.InputStream;
import java.util.HashMap;

public class FixingDepartmentActivity extends BaseActivity implements FixingDepartmentActivityView {

    private LinearLayout ll_writing_thumbnail;
    private LinearLayout ll_writing_attach_cancel;
    private ImageView iv_writing_attach_cancel;

    private static final int REQUEST_CODE = 0;

    private String section_in_agora;
    private String category_name;

    private ImageView iv_writing_attached_thumbnail;
    private ImageView btn_writing_cancel;
    private Button btn_writing_complete;
    private EditText et_writing_title;
    private EditText et_writing_content;
    private ImageView btn_writing_attach_photo;
    private CheckBox chk_writing_anonymous;


    private int idx_of_post_we_are_fixing;
    private String origin_title;
    private String origin_content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_writing_department);

        bindViews();

        loadRecentSectionAndCategory();

        setClickListenersToViews();

        idx_of_post_we_are_fixing = getIntent().getExtras().getInt("idx_of_post_we_are_fixing", 0);
        origin_title = getIntent().getExtras().getString("origin_title", "");
        origin_content = getIntent().getExtras().getString("origin_content", "");

        et_writing_title.setText(origin_title);
        et_writing_content.setText(origin_content);

        et_writing_content.requestFocus();

    }

    public void bindViews() {

        iv_writing_attach_cancel = findViewById(R.id.iv_writing_attach_cancel);
        ll_writing_attach_cancel = findViewById(R.id.ll_writing_attach_cancel);
        ll_writing_thumbnail = findViewById(R.id.ll_writing_thumbnail);
        iv_writing_attached_thumbnail = findViewById(R.id.iv_writing_attached_thumbnail);
        btn_writing_cancel = findViewById(R.id.btn_writing_cancel);
        btn_writing_complete = findViewById(R.id.btn_writing_complete);
        et_writing_title = findViewById(R.id.et_writing_title);
        et_writing_content = findViewById(R.id.et_writing_content);
        btn_writing_attach_photo = findViewById(R.id.btn_writing_attach_photo);
//        chk_writing_anonymous = findViewById(R.id.chk_writing_anonymous);
    }

    public void loadRecentSectionAndCategory() {
        SharedPreferences sharedPreferences = getSharedPreferences("recent_section_and_category", MODE_PRIVATE);
        section_in_agora = sharedPreferences.getString("section_in_agora", "SECTION 불러오기 실패");
        category_name = sharedPreferences.getString("category_name", "CATEGORY 불러오기 실패");
    }

    public void setClickListenersToViews() {
        btn_writing_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(FixingDepartmentActivity.this)
                        .setTitle("수정 취소")
                        .setMessage("글 수정을 취소하시겠습니까?")
                        .setPositiveButton("예", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent(FixingDepartmentActivity.this, DepartmentBoardActivity.class);
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

        btn_writing_complete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (isEmptyTitle()) {
                    Toast.makeText(FixingDepartmentActivity.this, "글의 제목을 작성해주세요", Toast.LENGTH_SHORT).show();
                    et_writing_title.requestFocus();
                } else if (isEmptyContent()){
                    Toast.makeText(FixingDepartmentActivity.this, "글의 내용을 작성해주세요", Toast.LENGTH_SHORT).show();
                    et_writing_content.requestFocus();
                } else{
                    new AlertDialog.Builder(FixingDepartmentActivity.this) // TestActivity 부분에는 현재 Activity의 이름 입력.
                            .setTitle("수정 완료")
                            .setMessage("수정을 마치시겠습니까?")
                            .setPositiveButton("예", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {

                                    loadRecentSectionAndCategory();

                                    tryPatchDepartmentWriting(et_writing_title.getText().toString(), et_writing_content.getText().toString(), null);

                                    Intent intent = new Intent(FixingDepartmentActivity.this, DepartmentBoardActivity.class);
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
            }
        });

        btn_writing_attach_photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent, REQUEST_CODE);
            }
        });

        iv_writing_attach_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(FixingDepartmentActivity.this)
                        .setTitle("첨부 취소")
                        .setMessage("사진 첨부를 취소하시겠습니까?")
                        .setPositiveButton("예", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                ll_writing_attach_cancel.setVisibility(View.INVISIBLE);
                                ll_writing_thumbnail.setVisibility(View.INVISIBLE);
                            }
                        })
                        .setNegativeButton("아니오", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        })
                        .setCancelable(true)
                        .show();
            }
        });


    }

    public void tryPatchDepartmentWriting(String title, String content, String photo){
        HashMap<String, Object> params = new HashMap<>();

        params.put("title", title);
        params.put("content", content);
        params.put("photo", photo);

        final FixingDepartmentService fixingDepartmentService = new FixingDepartmentService(this, params);
        fixingDepartmentService.patchDepartmentPost(idx_of_post_we_are_fixing);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                try {
                    InputStream in = getContentResolver().openInputStream(data.getData());

                    Bitmap img = BitmapFactory.decodeStream(in);
                    in.close();

                    iv_writing_attached_thumbnail.setImageBitmap(img);
                    ll_writing_thumbnail.setVisibility(View.VISIBLE);
                    ll_writing_attach_cancel.setVisibility(View.VISIBLE);
                } catch (Exception e) {

                }
            } else if (resultCode == RESULT_CANCELED) {
                Toast.makeText(this, "사진 선택 취소", Toast.LENGTH_LONG).show();
            }
        }
    }


    @Override
    public void onBackPressed() {

        new AlertDialog.Builder(FixingDepartmentActivity.this) // TestActivity 부분에는 현재 Activity의 이름 입력.
                .setTitle("수정 취소")
                .setMessage("글 수정을 취소하시겠습니까?")
                .setPositiveButton("예", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(FixingDepartmentActivity.this, DepartmentBoardActivity.class);
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

    public boolean isEmptyTitle() {
        if (et_writing_title.getText().toString().trim().length() <= 0)
            return true;
        else
            return false;
    }

    public boolean isEmptyContent() {
        if (et_writing_content.getText().toString().trim().length() <= 0)
            return true;
        else
            return false;
    }




    // 이 밑으로는 템플릿

//    private void tryGetTest() {
//        showProgressDialog();
//
//        final MainService mainService = new MainService(this);
//        mainService.getTest();
//    }

    @Override
    public void validateSuccess(String text) {
        hideProgressDialog();
//        mTvHelloWorld.setText(text);
    }

    @Override
    public void validateFailure(@Nullable String message) {
        hideProgressDialog();
        showCustomToast(message == null || message.isEmpty() ? getString(R.string.network_error) : message);
    }

    @Override
    public void patchDepartmentPostSuccess(DefaultResponse defaultResponse) {

    }

}
