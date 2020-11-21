package com.example.mediaproject_agora.src.main;

import com.example.mediaproject_agora.src.main.fragments.fragment_message.MessageListResponse;
import com.example.mediaproject_agora.src.main.interfaces.InMessageRoomActivityView;
import com.example.mediaproject_agora.src.main.interfaces.InMessageRoomRetrofitInterface;
import com.example.mediaproject_agora.src.main.interfaces.InPostActivityView;
import com.example.mediaproject_agora.src.main.interfaces.InPostRetrofitInterface;
import com.example.mediaproject_agora.src.main.models.DefaultResponse;
import com.example.mediaproject_agora.src.main.models.InPostCommentResponse;
import com.example.mediaproject_agora.src.main.models.InPostPostResponse;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.mediaproject_agora.src.ApplicationClass.X_ACCESS_TOKEN;
import static com.example.mediaproject_agora.src.ApplicationClass.getRetrofit;

//Comment Adapter로부터의 접근을 위해 public 붙여줌
public class InMessageRoomService {
    private final InMessageRoomActivityView mInMessageRoomActivityView;
    private HashMap<String, Object> mParams;

    public InMessageRoomService(InMessageRoomActivityView mInMessageRoomActivityView) {
        this.mInMessageRoomActivityView = mInMessageRoomActivityView;
    }

    public InMessageRoomService(InMessageRoomActivityView mInMessageRoomActivityView, HashMap<String, Object> mParams) {
        this.mInMessageRoomActivityView = mInMessageRoomActivityView;
        this.mParams = mParams;
    }

    // 특정 쪽지방 안의 쪽지내역 다 가져오기
    void getSpecificMessageRoom(int message_room_idx) {

        final InMessageRoomRetrofitInterface inMessageRoomRetrofitInterface = getRetrofit().create(InMessageRoomRetrofitInterface.class);
        inMessageRoomRetrofitInterface.getSpecificMessageRoom(X_ACCESS_TOKEN, message_room_idx).enqueue(new Callback<MessageListResponse>() {
            @Override
            public void onResponse(Call<MessageListResponse> call, Response<MessageListResponse> response) {

                final MessageListResponse messageListResponse = response.body();
                if (messageListResponse == null) {
                    mInMessageRoomActivityView.validateFailure(null);
                    return;
                }
                mInMessageRoomActivityView.getSpecificMessageRoomSuccess(messageListResponse);
            }

            @Override
            public void onFailure(Call<MessageListResponse> call, Throwable t) {
                mInMessageRoomActivityView.validateFailure(null);
            }
        });
    }

    // 특정 쪽지방 삭제하기
    void deleteMessageRoom(int message_room_idx) {

        final InMessageRoomRetrofitInterface inMessageRoomRetrofitInterface = getRetrofit().create(InMessageRoomRetrofitInterface.class);
        inMessageRoomRetrofitInterface.deleteMessageRoom(X_ACCESS_TOKEN, message_room_idx).enqueue(new Callback<DefaultResponse>() {
            @Override
            public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {

                final DefaultResponse defaultResponse = response.body();
                if (defaultResponse == null) {
                    mInMessageRoomActivityView.validateFailure(null);
                    return;
                }
                mInMessageRoomActivityView.deleteMessageRoomSuccess(defaultResponse);
            }

            @Override
            public void onFailure(Call<DefaultResponse> call, Throwable t) {
                mInMessageRoomActivityView.validateFailure(null);
            }
        });
    }

    // 쪽지 보내기
    void sendMessage() {

        final InMessageRoomRetrofitInterface inMessageRoomRetrofitInterface = getRetrofit().create(InMessageRoomRetrofitInterface.class);
        inMessageRoomRetrofitInterface.sendMessage(X_ACCESS_TOKEN, mParams).enqueue(new Callback<DefaultResponse>() {
            @Override
            public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {

                final DefaultResponse defaultResponse = response.body();
                if (defaultResponse == null) {
                    mInMessageRoomActivityView.validateFailure(null);
                    return;
                }
                mInMessageRoomActivityView.sendMessageSuccess(defaultResponse);
            }

            @Override
            public void onFailure(Call<DefaultResponse> call, Throwable t) {
                mInMessageRoomActivityView.validateFailure(null);
            }
        });
    }


}


//    void getTest() {
//        final SpecificBoardRetrofitInterface specificBoardRetrofitInterface = getRetrofit().create(SpecificBoardRetrofitInterface.class);
//        specificBoardRetrofitInterface.getTest().enqueue(new Callback<DefaultResponse>() {
//            @Override
//            public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {
//                final DefaultResponse defaultResponse = response.body();
//                if (defaultResponse == null) {
//                    mSpecificBoardActivityView.validateFailure(null);
//                    return;
//                }
//
//                mSpecificBoardActivityView.validateSuccess(defaultResponse.getMessage());
//            }
//
//            @Override
//            public void onFailure(Call<DefaultResponse> call, Throwable t) {
//                mSpecificBoardActivityView.validateFailure(null);
//            }
//        });
//    }

