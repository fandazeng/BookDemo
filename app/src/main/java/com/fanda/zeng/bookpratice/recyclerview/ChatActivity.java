package com.fanda.zeng.bookpratice.recyclerview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.fanda.zeng.bookpratice.R;
import com.fanda.zeng.bookpratice.activity.BaseActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zfd on 2017/1/17 0017.
 * 聊天界面
 */

public class ChatActivity extends BaseActivity implements View.OnClickListener {

    private RecyclerView mChatRecyclerView;
    private EditText mInputEdit;
    private Button mSend;

    private List<Msg> mChatList;
    private ChatAdapter mChatAdapter;

    private int count;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        initView();
    }

    private void initView() {
        initMsg();
        mChatRecyclerView = (RecyclerView) findViewById(R.id.recycler_chat);
        mChatRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mChatAdapter = new ChatAdapter(mChatList);
        mChatRecyclerView.setAdapter(mChatAdapter);
        mInputEdit = (EditText) findViewById(R.id.et_input_content);
        mSend = (Button) findViewById(R.id.btn_send);
        mSend.setOnClickListener(this);
    }

    /**
     * 初始化数据
     */
    private void initMsg() {
        mChatList = new ArrayList<>();
        Msg msg1 = new Msg("Hello guy.", Msg.TYPE_RECEIVE);
        mChatList.add(msg1);
        Msg msg2 = new Msg("Hello. Who is that?", Msg.TYPE_SEND);
        mChatList.add(msg2);
        Msg msg3 = new Msg("This is Tom. Nice talking to you. ", Msg.TYPE_RECEIVE);
        mChatList.add(msg3);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_send:
                String content = mInputEdit.getText().toString().trim();
                if (TextUtils.isEmpty(content)) {
                    Toast.makeText(this, "请输入内容", Toast.LENGTH_SHORT).show();
                } else {
                    Msg msg;
                    if (count % 2 == 0) {
                        msg = new Msg(content, Msg.TYPE_SEND);
                    } else {
                        msg = new Msg(content, Msg.TYPE_RECEIVE);
                    }
                    mChatList.add(msg);
                    //刷新数据，在最后一条
                    mChatAdapter.notifyItemInserted(mChatList.size() - 1);
                    //如果当前位置不在最后，滚动定位到最后一条
                    mChatRecyclerView.scrollToPosition(mChatList.size() - 1);
                    //清空之前发送的内容
                    mInputEdit.setText("");
                    count++;
                }
                break;
            default:
                break;
        }
    }
}
