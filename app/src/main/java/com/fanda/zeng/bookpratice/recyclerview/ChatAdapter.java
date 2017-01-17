package com.fanda.zeng.bookpratice.recyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.fanda.zeng.bookpratice.R;

import java.util.List;

/**
 * Created by zfd on 2017/1/17 0017.
 * 聊天界面 适配器
 */

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ChatViewHolder> {

    private List<Msg> mMsgList;

    public ChatAdapter(List<Msg> mMsgList) {
        this.mMsgList = mMsgList;
    }

    @Override
    public ChatViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_chat, parent, false);
        return new ChatViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ChatViewHolder holder, int position) {
        Msg msg = mMsgList.get(position);
        if (Msg.TYPE_RECEIVE ==msg.getType()) {
            holder.tv_msg_left.setText(msg.getContent());
            holder.right_layout.setVisibility(View.GONE);
        } else {
            holder.tv_msg_right.setText(msg.getContent());
            holder.left_layout.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return mMsgList.isEmpty() ? 0 : mMsgList.size();
    }

    class ChatViewHolder extends RecyclerView.ViewHolder {

        TextView tv_msg_left;
        TextView tv_msg_right;
        View right_layout;
        View left_layout;

        public ChatViewHolder(View itemView) {
            super(itemView);
            tv_msg_left = (TextView) itemView.findViewById(R.id.tv_msg_left);
            tv_msg_right = (TextView) itemView.findViewById(R.id.tv_msg_right);
            right_layout =  itemView.findViewById(R.id.right_layout);
            left_layout =  itemView.findViewById(R.id.left_layout);
        }
    }

}
