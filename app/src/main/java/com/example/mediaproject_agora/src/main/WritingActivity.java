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
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;

import com.example.mediaproject_agora.R;
import com.example.mediaproject_agora.src.BaseActivity;
import com.example.mediaproject_agora.src.main.interfaces.SpecificBoardActivityView;
import com.example.mediaproject_agora.src.main.interfaces.WritingActivityView;

import java.io.InputStream;

public class WritingActivity extends BaseActivity implements WritingActivityView {

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_writing);

        bindViews();

        loadRecentSectionAndCategory();

        setClickListenersToViews();

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
                new AlertDialog.Builder(WritingActivity.this)
                        .setTitle("작성 취소")
                        .setMessage("글 작성을 취소하시겠습니까?")
                        .setPositiveButton("예", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent(WritingActivity.this, SpecificBoardActivity.class);
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
                    Toast.makeText(WritingActivity.this, "글의 제목을 작성해주세요", Toast.LENGTH_SHORT).show();
                    et_writing_title.requestFocus();
                } else if (isEmptyContent()){
                    Toast.makeText(WritingActivity.this, "글의 내용을 작성해주세요", Toast.LENGTH_SHORT).show();
                    et_writing_content.requestFocus();
                } else{
                    new AlertDialog.Builder(WritingActivity.this) // TestActivity 부분에는 현재 Activity의 이름 입력.
                            .setTitle("작성 완료")
                            .setMessage("글을 게시하시겠습니까?")
                            .setPositiveButton("예", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    isEmptyTitle();
                                    Intent intent = new Intent(WritingActivity.this, SpecificBoardActivity.class);
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
                new AlertDialog.Builder(WritingActivity.this)
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

        new AlertDialog.Builder(WritingActivity.this) // TestActivity 부분에는 현재 Activity의 이름 입력.
                .setTitle("작성 취소")
                .setMessage("글 작성을 취소하시겠습니까?")
                .setPositiveButton("예", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(WritingActivity.this, SpecificBoardActivity.class);
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


}
