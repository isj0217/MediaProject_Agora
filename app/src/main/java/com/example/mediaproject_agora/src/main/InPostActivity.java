package com.example.mediaproject_agora.src.main;

import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mediaproject_agora.R;


public class InPostActivity extends AppCompatActivity {

//    private ArrayList<CommentItem> m_comment_item_list;

//    private CommentAdapter comment_adapter;

    private CheckBox chk_in_post_anonymous;

    private RecyclerView rv_in_post_comment;
    private LinearLayoutManager linear_layout_manager;

    private TextView tv_in_post_nickname, tv_in_post_time, tv_in_post_title, tv_in_post_content, tv_in_post_like_num, tv_in_post_comment_num, tv_in_post_scrap_num;

//    private InPostService inPostService;
    private String clicked;

    private EditText et_in_post_comment;
    private ImageView iv_in_post_register_comment;

    private int m_clicked_free_pos;
    private int m_clicked_secret_pos;
    private int m_clicked_alumni_pos;
    private int m_clicked_freshmen_pos;

    private int m_from_board_num;

    private int m_index_of_this_post;

    private boolean m_from_frag_home;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_in_post);

//        m_comment_item_list = new ArrayList<>();

//        comment_adapter = new CommentAdapter(m_comment_item_list);
        rv_in_post_comment = findViewById(R.id.rv_board_comment_list);

        linear_layout_manager = new LinearLayoutManager(getApplicationContext());
        rv_in_post_comment.setLayoutManager(linear_layout_manager);

//        rv_in_post_comment.setAdapter(comment_adapter);


//        inPostService = new InPostService(this);

        ViewBinding();

        if (getIntent().getStringExtra("clicked") != null) {
            clicked = getIntent().getStringExtra("clicked");
            m_from_frag_home = true;
        } else {
            clicked = "";
            m_from_frag_home = false;
        }

//        /**
//         * 홈화면에서 바로 건너올때 글을 채워주기 위한 부분!!!!! (댓글과 무관!!!!!!!)
//         * */
//        if (m_from_frag_home && (!clicked.equals(""))) {
//            switch (clicked) {
//                case "realtime_hot_post_1":
//                case "realtime_hot_post_2":
//                    inPostService.getRealTimeHotBoard();
//                    break;
//                case "hot_post_1":
//                case "hot_post_2":
//                case "hot_post_3":
//                case "hot_post_4":
//                    inPostService.getHotBoard();
//                    break;
//                case "review_1":
//                case "review_2":
//                case "review_3":
//                case "review_4":
//                    inPostService.getRecentLectureReview();
//                    break;
//                case "frag_home_favorite_free_board":
//                    inPostService.getFreeBoard();
//                    break;
//                case "frag_home_favorite_secret_board":
//                    inPostService.getSecretBoard();
//                    break;
//                case "frag_home_favorite_alumni_board":
//                    inPostService.getAlumniBoard();
//                    break;
//                case "frag_home_favorite_freshmen_board":
//                    inPostService.getFreshmenBoard();
//                    break;
//                default:
//                    break;
//            }
//        }
//
//        /**
//         * 홈화면에서 바로 건너올때 댓글을 채워주기 위한 부분!!!!!
//         * */
//        if (m_from_frag_home) {
//            m_index_of_this_post = getIntent().getIntExtra("index_of_this_post", 0);
//            System.out.println("넘어온 index_of_this_post는 " + m_index_of_this_post + "입니다.");
//
//            inPostService.getFreeComment(m_index_of_this_post);
//        }
//
//
//        /**
//         * 게시판에서 직접 글을 클릭할 때 불러오는 부분!!!!(댓글과 무관함!!!)
//         * */
//        if (!m_from_frag_home) {
//            m_index_of_this_post = getIntent().getIntExtra("index_of_this_post", 0);
//            m_clicked_free_pos = getIntent().getIntExtra("clicked_free_pos", -1);
//            if (m_clicked_free_pos != -1) {
//                m_from_board_num = 1;
//                inPostService.getExactFreePost();
//            }
//            m_clicked_secret_pos = getIntent().getIntExtra("clicked_secret_pos", -1);
//            if (m_clicked_secret_pos != -1) {
//                m_from_board_num = 2;
//                inPostService.getExactSecretPost();
//            }
//            m_clicked_alumni_pos = getIntent().getIntExtra("clicked_alumni_pos", -1);
//            if (m_clicked_alumni_pos != -1) {
//                m_from_board_num = 3;
//                inPostService.getExactAlumniPost();
//            }
//            m_clicked_freshmen_pos = getIntent().getIntExtra("clicked_freshmen_pos", -1);
//            if (m_clicked_freshmen_pos != -1) {
//                m_from_board_num = 4;
//                inPostService.getExactFreshmenPost();
//            }
//        }
//
//
//        /**
//         * 게시판에서 직접 클릭할 때 댓글을 채워주기 위한 부분!!!!!!
//         * */
//        if (!m_from_frag_home) {
//            int clicked_content_index = getIntent().getIntExtra("clicked_content_index", 0);
//            System.out.println("clicked_content_index: " + clicked_content_index);
//
//            switch (m_from_board_num) {
//                case 1:
//                    tryGetFreeComment(clicked_content_index);
//                    break;
//                case 2:
//                    tryGetSecretComment(clicked_content_index);
//                    break;
//                case 3:
//                    tryGetAlumniComment(clicked_content_index);
//                    break;
//                case 4:
//                    tryGetFreshmenComment(clicked_content_index);
//                    break;
//                default:
//                    break;
//
//            }
//
//        }


    }

//    private void tryGetFreeComment(int content_index) {
//        showProgressDialog();
//
//        final InPostService inPostService = new InPostService(this);
//        inPostService.getFreeComment(content_index);
//    }
//    private void tryGetSecretComment(int content_index) {
//        showProgressDialog();
//
//        final InPostService inPostService = new InPostService(this);
//        inPostService.getSecretComment(content_index);
//    }
//    private void tryGetAlumniComment(int content_index) {
//        showProgressDialog();
//
//        final InPostService inPostService = new InPostService(this);
//        inPostService.getAlumniComment(content_index);
//    }
//    private void tryGetFreshmenComment(int content_index) {
//        showProgressDialog();
//
//        final InPostService inPostService = new InPostService(this);
//        inPostService.getFreshmenComment(content_index);
//    }


    public void ViewBinding() {

        chk_in_post_anonymous = findViewById(R.id.chk_in_post_anonymous);

        et_in_post_comment = findViewById(R.id.et_in_post_comment);
        iv_in_post_register_comment = findViewById(R.id.iv_in_post_register_comment);

        tv_in_post_nickname = findViewById(R.id.tv_in_post_nickname);
        tv_in_post_time = findViewById(R.id.tv_in_post_time);
        tv_in_post_title = findViewById(R.id.tv_in_post_title);
        tv_in_post_content = findViewById(R.id.tv_in_post_content);
        tv_in_post_like_num = findViewById(R.id.tv_in_post_like_num);
        tv_in_post_comment_num = findViewById(R.id.tv_in_post_comment_num);
    }

//    @Override
//    public void realtimeHotPostSuccess(RealTimeHotPostResponse realTimeHotPostResponse) {
//        hideProgressDialog();
//        switch (clicked) {
//            case "realtime_hot_post_1":
//                tv_in_post_nickname.setText(realTimeHotPostResponse.getRealTimeHotPostResults().get(0).getContentWriter());
//                tv_in_post_time.setText(realTimeHotPostResponse.getRealTimeHotPostResults().get(0).getWriteDay());
//                tv_in_post_title.setText(realTimeHotPostResponse.getRealTimeHotPostResults().get(0).getContentTitle());
//                tv_in_post_content.setText(realTimeHotPostResponse.getRealTimeHotPostResults().get(0).getContentInf());
//                tv_in_post_like_num.setText(realTimeHotPostResponse.getRealTimeHotPostResults().get(0).getCountLike());
//                tv_in_post_comment_num.setText(realTimeHotPostResponse.getRealTimeHotPostResults().get(0).getCountComment());
//                tv_in_post_scrap_num.setText(Integer.toString(realTimeHotPostResponse.getRealTimeHotPostResults().get(0).getCountScrab()));
//                break;
//            case "realtime_hot_post_2":
//                tv_in_post_nickname.setText(realTimeHotPostResponse.getRealTimeHotPostResults().get(1).getContentWriter());
//                tv_in_post_time.setText(realTimeHotPostResponse.getRealTimeHotPostResults().get(1).getWriteDay());
//                tv_in_post_title.setText(realTimeHotPostResponse.getRealTimeHotPostResults().get(1).getContentTitle());
//                tv_in_post_content.setText(realTimeHotPostResponse.getRealTimeHotPostResults().get(1).getContentInf());
//                tv_in_post_like_num.setText(realTimeHotPostResponse.getRealTimeHotPostResults().get(1).getCountLike());
//                tv_in_post_comment_num.setText(realTimeHotPostResponse.getRealTimeHotPostResults().get(1).getCountComment());
//                tv_in_post_scrap_num.setText(Integer.toString(realTimeHotPostResponse.getRealTimeHotPostResults().get(1).getCountScrab()));
//                break;
//        }
//
//    }
//    @Override
//    public void hotPostSuccess(CommonBoardResponse commonBoardResponse) {
//        hideProgressDialog();
//        switch (clicked) {
//            case "hot_post_1":
//                tv_in_post_nickname.setText(commonBoardResponse.getCommonBoardResults().get(0).getContentWriter());         // 글쓴이 서버에서 안넘어옴
//                tv_in_post_time.setText(commonBoardResponse.getCommonBoardResults().get(0).getWriteDay());
//                tv_in_post_title.setText(commonBoardResponse.getCommonBoardResults().get(0).getContentTitle());
//                tv_in_post_content.setText(commonBoardResponse.getCommonBoardResults().get(0).getContentInf());
//                tv_in_post_like_num.setText(Integer.toString(commonBoardResponse.getCommonBoardResults().get(0).getCountLike()));
//                tv_in_post_comment_num.setText(Integer.toString(commonBoardResponse.getCommonBoardResults().get(0).getCountComment()));
//                tv_in_post_scrap_num.setText("0");
//                // scrap_num이 API에서 넘어오지 않아 임시로 0으로 설정함
//                break;
//            case "hot_post_2":
//                tv_in_post_nickname.setText(commonBoardResponse.getCommonBoardResults().get(1).getContentWriter());         // 글쓴이 서버에서 안넘어옴
//                tv_in_post_time.setText(commonBoardResponse.getCommonBoardResults().get(1).getWriteDay());
//                tv_in_post_title.setText(commonBoardResponse.getCommonBoardResults().get(1).getContentTitle());
//                tv_in_post_content.setText(commonBoardResponse.getCommonBoardResults().get(1).getContentInf());
//                tv_in_post_like_num.setText(Integer.toString(commonBoardResponse.getCommonBoardResults().get(1).getCountLike()));
//                tv_in_post_comment_num.setText(Integer.toString(commonBoardResponse.getCommonBoardResults().get(1).getCountComment()));
//                tv_in_post_scrap_num.setText("0");
//                // scrap_num이 API에서 넘어오지 않아 임시로 0으로 설정함
//                break;
//            case "hot_post_3":
//                tv_in_post_nickname.setText(commonBoardResponse.getCommonBoardResults().get(2).getContentWriter());         // 글쓴이 서버에서 안넘어옴
//                tv_in_post_time.setText(commonBoardResponse.getCommonBoardResults().get(2).getWriteDay());
//                tv_in_post_title.setText(commonBoardResponse.getCommonBoardResults().get(2).getContentTitle());
//                tv_in_post_content.setText(commonBoardResponse.getCommonBoardResults().get(2).getContentInf());
//                tv_in_post_like_num.setText(Integer.toString(commonBoardResponse.getCommonBoardResults().get(2).getCountLike()));
//                tv_in_post_comment_num.setText(Integer.toString(commonBoardResponse.getCommonBoardResults().get(2).getCountComment()));
//                tv_in_post_scrap_num.setText("0");
//                // scrap_num이 API에서 넘어오지 않아 임시로 0으로 설정함
//                break;
//            case "hot_post_4":
//                tv_in_post_nickname.setText(commonBoardResponse.getCommonBoardResults().get(3).getContentWriter());         // 글쓴이 서버에서 안넘어옴
//                tv_in_post_time.setText(commonBoardResponse.getCommonBoardResults().get(3).getWriteDay());
//                tv_in_post_title.setText(commonBoardResponse.getCommonBoardResults().get(3).getContentTitle());
//                tv_in_post_content.setText(commonBoardResponse.getCommonBoardResults().get(3).getContentInf());
//                tv_in_post_like_num.setText(Integer.toString(commonBoardResponse.getCommonBoardResults().get(3).getCountLike()));
//                tv_in_post_comment_num.setText(Integer.toString(commonBoardResponse.getCommonBoardResults().get(3).getCountComment()));
//                tv_in_post_scrap_num.setText("0");
//                // scrap_num이 API에서 넘어오지 않아 임시로 0으로 설정함
//                break;
//        }
//    }


    /**
     * 이건 레이아웃 따로 파야 함
     */
//    @Override
//    public void recentLectureReviewSuccess(RecentLectureReviewResponse recentLectureReviewBoardResponse) {
//        hideProgressDialog();
//        switch (clicked) {
//            case "review_1":
//                tv_in_post_nickname.setText(recentLectureReviewBoardResponse.getRecentLectureReviewBoardResults().get(0).get().getRecentLectureReviewResults().get(0).getboardResponse.getBoardResults().get(0).getContentWriter());         // 글쓴이 서버에서 안넘어옴
//                tv_in_post_time.setText(boardResponse.getBoardResults().get(0).getWriteDay());
//                tv_in_post_title.setText(boardResponse.getBoardResults().get(0).getContentTitle());
//                tv_in_post_content.setText(boardResponse.getBoardResults().get(0).getContentInf());
//                tv_in_post_like_num.setText(Integer.toString(boardResponse.getBoardResults().get(0).getCountLike()));
//                tv_in_post_comment_num.setText(Integer.toString(boardResponse.getBoardResults().get(0).getCountComment()));
//                tv_in_post_scrap_num.setText("0");
//                // scrap_num이 API에서 넘어오지 않아 임시로 0으로 설정함
//                break;
//            case "review_2":
//                tv_in_post_nickname.setText(boardResponse.getBoardResults().get(1).getContentWriter());         // 글쓴이 서버에서 안넘어옴
//                tv_in_post_time.setText(boardResponse.getBoardResults().get(1).getWriteDay());
//                tv_in_post_title.setText(boardResponse.getBoardResults().get(1).getContentTitle());
//                tv_in_post_content.setText(boardResponse.getBoardResults().get(1).getContentInf());
//                tv_in_post_like_num.setText(Integer.toString(boardResponse.getBoardResults().get(1).getCountLike()));
//                tv_in_post_comment_num.setText(Integer.toString(boardResponse.getBoardResults().get(1).getCountComment()));
//                tv_in_post_scrap_num.setText("0");
//                // scrap_num이 API에서 넘어오지 않아 임시로 0으로 설정함
//                break;
//            case "review_3":
//                tv_in_post_nickname.setText(boardResponse.getBoardResults().get(2).getContentWriter());         // 글쓴이 서버에서 안넘어옴
//                tv_in_post_time.setText(boardResponse.getBoardResults().get(2).getWriteDay());
//                tv_in_post_title.setText(boardResponse.getBoardResults().get(2).getContentTitle());
//                tv_in_post_content.setText(boardResponse.getBoardResults().get(2).getContentInf());
//                tv_in_post_like_num.setText(Integer.toString(boardResponse.getBoardResults().get(2).getCountLike()));
//                tv_in_post_comment_num.setText(Integer.toString(boardResponse.getBoardResults().get(2).getCountComment()));
//                tv_in_post_scrap_num.setText("0");
//                // scrap_num이 API에서 넘어오지 않아 임시로 0으로 설정함
//                break;
//            case "review_4":
//                tv_in_post_nickname.setText(boardResponse.getBoardResults().get(3).getContentWriter());         // 글쓴이 서버에서 안넘어옴
//                tv_in_post_time.setText(boardResponse.getBoardResults().get(3).getWriteDay());
//                tv_in_post_title.setText(boardResponse.getBoardResults().get(3).getContentTitle());
//                tv_in_post_content.setText(boardResponse.getBoardResults().get(3).getContentInf());
//                tv_in_post_like_num.setText(Integer.toString(boardResponse.getBoardResults().get(3).getCountLike()));
//                tv_in_post_comment_num.setText(Integer.toString(boardResponse.getBoardResults().get(3).getCountComment()));
//                tv_in_post_scrap_num.setText("0");
//                // scrap_num이 API에서 넘어오지 않아 임시로 0으로 설정함
//                break;
//        }
//    }

//    @Override
//    public void freeBoardSuccess(CommonBoardResponse commonBoardResponse) {
//        hideProgressDialog();
//        switch (clicked) {
//            case "frag_home_favorite_free_board":
//
//                tv_in_post_nickname.setText(commonBoardResponse.getCommonBoardResults().get(0).getContentWriter());         // 글쓴이 서버에서 안넘어옴
//                tv_in_post_time.setText(commonBoardResponse.getCommonBoardResults().get(0).getWriteDay());
//                tv_in_post_title.setText(commonBoardResponse.getCommonBoardResults().get(0).getContentTitle());
//                tv_in_post_content.setText(commonBoardResponse.getCommonBoardResults().get(0).getContentInf());
//                tv_in_post_like_num.setText(Integer.toString(commonBoardResponse.getCommonBoardResults().get(0).getCountLike()));
//                tv_in_post_comment_num.setText(Integer.toString(commonBoardResponse.getCommonBoardResults().get(0).getCountComment()));
//                tv_in_post_scrap_num.setText("0");
//                break;
//            case "frag_home_favorite_secret_board":
//                break;
//            case "frag_home_favorite_alumni_board":
//                break;
//            case "frag_home_favorite_freshmen_board":
//                break;
//        }
//    }
//    @Override
//    public void secretBoardSuccess(CommonBoardResponse commonBoardResponse) {
//        hideProgressDialog();
//        switch (clicked) {
//            case "frag_home_favorite_free_board":
//                break;
//            case "frag_home_favorite_secret_board":
//                tv_in_post_nickname.setText(commonBoardResponse.getCommonBoardResults().get(0).getContentWriter());         // 글쓴이 서버에서 안넘어옴
//                tv_in_post_time.setText(commonBoardResponse.getCommonBoardResults().get(0).getWriteDay());
//                tv_in_post_title.setText(commonBoardResponse.getCommonBoardResults().get(0).getContentTitle());
//                tv_in_post_content.setText(commonBoardResponse.getCommonBoardResults().get(0).getContentInf());
//                tv_in_post_like_num.setText(Integer.toString(commonBoardResponse.getCommonBoardResults().get(0).getCountLike()));
//                tv_in_post_comment_num.setText(Integer.toString(commonBoardResponse.getCommonBoardResults().get(0).getCountComment()));
//                tv_in_post_scrap_num.setText("0");
//                break;
//            case "frag_home_favorite_alumni_board":
//                break;
//            case "frag_home_favorite_freshmen_board":
//                break;
//        }
//    }
//    @Override
//    public void alumniBoardSuccess(CommonBoardResponse commonBoardResponse) {
//        hideProgressDialog();
//        switch (clicked) {
//            case "frag_home_favorite_free_board":
//                break;
//            case "frag_home_favorite_secret_board":
//                break;
//            case "frag_home_favorite_alumni_board":
//                tv_in_post_nickname.setText(commonBoardResponse.getCommonBoardResults().get(0).getContentWriter());         // 글쓴이 서버에서 안넘어옴
//                tv_in_post_time.setText(commonBoardResponse.getCommonBoardResults().get(0).getWriteDay());
//                tv_in_post_title.setText(commonBoardResponse.getCommonBoardResults().get(0).getContentTitle());
//                tv_in_post_content.setText(commonBoardResponse.getCommonBoardResults().get(0).getContentInf());
//                tv_in_post_like_num.setText(Integer.toString(commonBoardResponse.getCommonBoardResults().get(0).getCountLike()));
//                tv_in_post_comment_num.setText(Integer.toString(commonBoardResponse.getCommonBoardResults().get(0).getCountComment()));
//                tv_in_post_scrap_num.setText("0");
//                break;
//            case "frag_home_favorite_freshmen_board":
//                break;
//        }
//
//    }
//    @Override
//    public void freshmenBoardSuccess(CommonBoardResponse commonBoardResponse) {
//        hideProgressDialog();
//        switch (clicked) {
//            case "frag_home_favorite_free_board":
//                break;
//            case "frag_home_favorite_secret_board":
//                break;
//            case "frag_home_favorite_alumni_board":
//                break;
//            case "frag_home_favorite_freshmen_board":
//                tv_in_post_nickname.setText(commonBoardResponse.getCommonBoardResults().get(0).getContentWriter());         // 글쓴이 서버에서 안넘어옴
//                tv_in_post_time.setText(commonBoardResponse.getCommonBoardResults().get(0).getWriteDay());
//                tv_in_post_title.setText(commonBoardResponse.getCommonBoardResults().get(0).getContentTitle());
//                tv_in_post_content.setText(commonBoardResponse.getCommonBoardResults().get(0).getContentInf());
//                tv_in_post_like_num.setText(Integer.toString(commonBoardResponse.getCommonBoardResults().get(0).getCountLike()));
//                tv_in_post_comment_num.setText(Integer.toString(commonBoardResponse.getCommonBoardResults().get(0).getCountComment()));
//                tv_in_post_scrap_num.setText("0");
//                break;
//        }
//    }
//    @Override
//    public void exactFreePostSuccess(CommonBoardResponse commonBoardResponse) {
//        hideProgressDialog();
//        tv_in_post_nickname.setText(commonBoardResponse.getCommonBoardResults().get(m_clicked_free_pos).getContentWriter());         // 글쓴이 서버에서 안넘어옴
//        tv_in_post_time.setText(commonBoardResponse.getCommonBoardResults().get(m_clicked_free_pos).getWriteDay());
//        tv_in_post_title.setText(commonBoardResponse.getCommonBoardResults().get(m_clicked_free_pos).getContentTitle());
//        tv_in_post_content.setText(commonBoardResponse.getCommonBoardResults().get(m_clicked_free_pos).getContentInf());
//        tv_in_post_like_num.setText(Integer.toString(commonBoardResponse.getCommonBoardResults().get(m_clicked_free_pos).getCountLike()));
//        tv_in_post_comment_num.setText(Integer.toString(commonBoardResponse.getCommonBoardResults().get(m_clicked_free_pos).getCountComment()));
//        tv_in_post_scrap_num.setText("0");
//    }
//    @Override
//    public void exactSecretPostSuccess(CommonBoardResponse commonBoardResponse) {
//        hideProgressDialog();
//        tv_in_post_nickname.setText(commonBoardResponse.getCommonBoardResults().get(m_clicked_secret_pos).getContentWriter());         // 글쓴이 서버에서 안넘어옴
//        tv_in_post_time.setText(commonBoardResponse.getCommonBoardResults().get(m_clicked_secret_pos).getWriteDay());
//        tv_in_post_title.setText(commonBoardResponse.getCommonBoardResults().get(m_clicked_secret_pos).getContentTitle());
//        tv_in_post_content.setText(commonBoardResponse.getCommonBoardResults().get(m_clicked_secret_pos).getContentInf());
//        tv_in_post_like_num.setText(Integer.toString(commonBoardResponse.getCommonBoardResults().get(m_clicked_secret_pos).getCountLike()));
//        tv_in_post_comment_num.setText(Integer.toString(commonBoardResponse.getCommonBoardResults().get(m_clicked_secret_pos).getCountComment()));
//        tv_in_post_scrap_num.setText("0");
//    }
//    @Override
//    public void exactAlumniPostSuccess(CommonBoardResponse commonBoardResponse) {
//        hideProgressDialog();
//        tv_in_post_nickname.setText(commonBoardResponse.getCommonBoardResults().get(m_clicked_alumni_pos).getContentWriter());         // 글쓴이 서버에서 안넘어옴
//        tv_in_post_time.setText(commonBoardResponse.getCommonBoardResults().get(m_clicked_alumni_pos).getWriteDay());
//        tv_in_post_title.setText(commonBoardResponse.getCommonBoardResults().get(m_clicked_alumni_pos).getContentTitle());
//        tv_in_post_content.setText(commonBoardResponse.getCommonBoardResults().get(m_clicked_alumni_pos).getContentInf());
//        tv_in_post_like_num.setText(Integer.toString(commonBoardResponse.getCommonBoardResults().get(m_clicked_alumni_pos).getCountLike()));
//        tv_in_post_comment_num.setText(Integer.toString(commonBoardResponse.getCommonBoardResults().get(m_clicked_alumni_pos).getCountComment()));
//        tv_in_post_scrap_num.setText("0");
//    }
//    @Override
//    public void exactFreshmenPostSuccess(CommonBoardResponse commonBoardResponse) {
//        hideProgressDialog();
//        tv_in_post_nickname.setText(commonBoardResponse.getCommonBoardResults().get(m_clicked_freshmen_pos).getContentWriter());         // 글쓴이 서버에서 안넘어옴
//        tv_in_post_time.setText(commonBoardResponse.getCommonBoardResults().get(m_clicked_freshmen_pos).getWriteDay());
//        tv_in_post_title.setText(commonBoardResponse.getCommonBoardResults().get(m_clicked_freshmen_pos).getContentTitle());
//        tv_in_post_content.setText(commonBoardResponse.getCommonBoardResults().get(m_clicked_freshmen_pos).getContentInf());
//        tv_in_post_like_num.setText(Integer.toString(commonBoardResponse.getCommonBoardResults().get(m_clicked_freshmen_pos).getCountLike()));
//        tv_in_post_comment_num.setText(Integer.toString(commonBoardResponse.getCommonBoardResults().get(m_clicked_freshmen_pos).getCountComment()));
//        tv_in_post_scrap_num.setText("0");
//    }
//    @Override
//    public void freeCommentSuccess(CommentResponse commentResponse) {
//        hideProgressDialog();
//
//        System.out.println("freecommentSuccess의 코드: " + commentResponse.getCode());
//        System.out.println("freecommentSuccess의 메시지: " + commentResponse.getMessage());
//        int size = commentResponse.getCommentResults().size();
//
//        if ((commentResponse.getCode() == 100) && (size > 0)) {
//            for (int i = 0; i < commentResponse.getCommentResults().size(); i++) {
//                CommentItem commentItem = new CommentItem();
//
//                commentItem.setCommentIdx(commentResponse.getCommentResults().get(i).getCommentIdx());
//                commentItem.setCommentInf(commentResponse.getCommentResults().get(i).getCommentInf());
//                commentItem.setCommentCountLike(commentResponse.getCommentResults().get(i).getCommentCountLike());
//                commentItem.setCommentWriteDay(commentResponse.getCommentResults().get(i).getCommentWriteDay());
//                commentItem.setCommentWriter(commentResponse.getCommentResults().get(i).getCommentWriter());
//
//                m_comment_item_list.add(commentItem);
//            }
//            comment_adapter.notifyDataSetChanged();
//        }
//    }
//    @Override
//    public void secretCommentSuccess(CommentResponse commentResponse) {
//        hideProgressDialog();
//
//        System.out.println(commentResponse.getCode());
//        System.out.println(commentResponse.getMessage());
//        int size = commentResponse.getCommentResults().size();
//
//        if ((commentResponse.getCode() == 100) && (size > 0)) {
//            for (int i = 0; i < commentResponse.getCommentResults().size(); i++) {
//                CommentItem commentItem = new CommentItem();
//
//                commentItem.setCommentIdx(commentResponse.getCommentResults().get(i).getCommentIdx());
//                commentItem.setCommentInf(commentResponse.getCommentResults().get(i).getCommentInf());
//                commentItem.setCommentCountLike(commentResponse.getCommentResults().get(i).getCommentCountLike());
//                commentItem.setCommentWriteDay(commentResponse.getCommentResults().get(i).getCommentWriteDay());
//                commentItem.setCommentWriter(commentResponse.getCommentResults().get(i).getCommentWriter());
//
//                m_comment_item_list.add(commentItem);
//            }
//            comment_adapter.notifyDataSetChanged();
//        }
//    }
//    @Override
//    public void alumniCommentSuccess(CommentResponse commentResponse) {
//        hideProgressDialog();
//
//        System.out.println(commentResponse.getCode());
//        System.out.println(commentResponse.getMessage());
//        int size = commentResponse.getCommentResults().size();
//
//        if ((commentResponse.getCode() == 100) && (size > 0)) {
//            for (int i = 0; i < commentResponse.getCommentResults().size(); i++) {
//                CommentItem commentItem = new CommentItem();
//
//                commentItem.setCommentIdx(commentResponse.getCommentResults().get(i).getCommentIdx());
//                commentItem.setCommentInf(commentResponse.getCommentResults().get(i).getCommentInf());
//                commentItem.setCommentCountLike(commentResponse.getCommentResults().get(i).getCommentCountLike());
//                commentItem.setCommentWriteDay(commentResponse.getCommentResults().get(i).getCommentWriteDay());
//                commentItem.setCommentWriter(commentResponse.getCommentResults().get(i).getCommentWriter());
//
//                m_comment_item_list.add(commentItem);
//            }
//            comment_adapter.notifyDataSetChanged();
//        }
//    }
//    @Override
//    public void freshmenCommentSuccess(CommentResponse commentResponse) {
//        hideProgressDialog();
//
//        System.out.println(commentResponse.getCode());
//        System.out.println(commentResponse.getMessage());
//        int size = commentResponse.getCommentResults().size();
//
//        if ((commentResponse.getCode() == 100) && (size > 0)) {
//            for (int i = 0; i < commentResponse.getCommentResults().size(); i++) {
//                CommentItem commentItem = new CommentItem();
//
//                commentItem.setCommentIdx(commentResponse.getCommentResults().get(i).getCommentIdx());
//                commentItem.setCommentInf(commentResponse.getCommentResults().get(i).getCommentInf());
//                commentItem.setCommentCountLike(commentResponse.getCommentResults().get(i).getCommentCountLike());
//                commentItem.setCommentWriteDay(commentResponse.getCommentResults().get(i).getCommentWriteDay());
//                commentItem.setCommentWriter(commentResponse.getCommentResults().get(i).getCommentWriter());
//
//                m_comment_item_list.add(commentItem);
//            }
//            comment_adapter.notifyDataSetChanged();
//        }
//    }
//    @Override
//    public void commentAddSuccess(CommentAddResponse commentAddResponse) {
//        hideProgressDialog();
//
//        System.out.println("response Code: " +commentAddResponse.getCode());
//
//        switch(commentAddResponse.getCode()) {
//
//            case 100:
//            showCustomToast("댓글이 등록되었습니다");
//            et_in_post_comment.setText("");
//
//            comment_adapter.notifyDataSetChanged();
//
//            System.out.println(commentAddResponse.getCode());
//            System.out.println(commentAddResponse.getMessage());
//            break;
//        }
//    }
//
//    public void customOnClick(View view) {
//        switch (view.getId()) {
//            case R.id.iv_in_post_register_comment:
//                if (m_from_frag_home) {
//                    showProgressDialog();
//
//                    String input_comment = et_in_post_comment.getText().toString();
//
//                    int user_status;
//                    if (chk_in_post_anonymous.isChecked()) {
//                        user_status = 0;
//                    } else {
//                        user_status = 1;
//                    }
//                    tryPostComment(input_comment, user_status);
//                    et_in_post_comment.setText("");
//
//
//                }else{
//                    showProgressDialog();
//
//                    String input_comment = et_in_post_comment.getText().toString();
//
//                    int user_status;
//                    if (chk_in_post_anonymous.isChecked()) {
//                        user_status = 0;
//                    } else {
//                        user_status = 1;
//                    }
//                    tryPostComment(input_comment, user_status);
//
//                    et_in_post_comment.setText("");
//
//                    Intent intent;
//
//                    switch (m_from_board_num) {
//                        case 1:
//                            intent = new Intent(InPostActivity.this, FreeBoardActivity.class);
//                            startActivity(intent);
//                            finish();
//                            break;
//                        case 2:
//                            intent = new Intent(InPostActivity.this, SecretBoardActivity.class);
//                            startActivity(intent);
//                            finish();
//                            break;
//                        case 3:
//                            intent = new Intent(InPostActivity.this, AlumniBoardActivity.class);
//                            startActivity(intent);
//                            finish();
//                            break;
//                        case 4:
//                            intent = new Intent(InPostActivity.this, FreshmenBoardActivity.class);
//                            startActivity(intent);
//                            finish();
//                            break;
//                        default:
//                            break;
//                    }
//                }
//        }
//    }
//
//    public void tryPostComment(String comment, int userStatus) {
//        HashMap<String, Object> params = new HashMap<>();
//        params.put("commentInf", comment);
//        params.put("userStatus", userStatus);
//
//        InPostService inPostService = new InPostService(this);
//        inPostService.postNewComment(m_index_of_this_post, params);
//
//        System.out.println("지금 여기서 m_index_of_this_post : " + m_index_of_this_post);
//    }
//
//    @Override
//    public void onBackPressed() {
//        Intent intent;
//        switch (m_from_board_num) {
//            case 1:
//                intent = new Intent(InPostActivity.this, FreeBoardActivity.class);
//                startActivity(intent);
//                finish();
//                break;
//            case 2:
//                intent = new Intent(InPostActivity.this, SecretBoardActivity.class);
//                startActivity(intent);
//                finish();
//                break;
//            case 3:
//                intent = new Intent(InPostActivity.this, AlumniBoardActivity.class);
//                startActivity(intent);
//                finish();
//                break;
//            case 4:
//                intent = new Intent(InPostActivity.this, FreshmenBoardActivity.class);
//                startActivity(intent);
//                finish();
//                break;
//            default:
//                intent = new Intent(InPostActivity.this, MainActivity.class);
//                startActivity(intent);
//                finish();
//                break;
//
//
//        }
//    }
//    @Override
//    public void validateSuccess(String text) {
//
//    }
//
//    @Override
//    public void validateFailure(String message) {
//        hideProgressDialog();
//    }
}
