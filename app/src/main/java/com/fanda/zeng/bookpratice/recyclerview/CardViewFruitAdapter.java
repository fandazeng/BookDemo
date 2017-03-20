package com.fanda.zeng.bookpratice.recyclerview;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.fanda.zeng.bookpratice.R;
import com.fanda.zeng.bookpratice.listview.Fruit;
import com.fanda.zeng.bookpratice.material.FruitDetailActivity;

import java.util.List;

/**
 * Created by 曾凡达 on 2017/3/20.
 * des CardView版的水果列表
 */

public class CardViewFruitAdapter extends RecyclerView.Adapter<CardViewFruitAdapter.CardViewViewHolder> {

    private List<Fruit> mDatas;
    private Context mContext;

    public CardViewFruitAdapter(List<Fruit> mDatas) {
        this.mDatas = mDatas;
    }

    @Override
    public CardViewViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mContext == null) {
            mContext = parent.getContext();
        }
//        return new CardViewViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_fruit_card, null));//不能这样加载，margin会无效
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_fruit_card, parent, false);
        final CardViewViewHolder holder = new CardViewViewHolder(view);
        holder.mCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fruit fruit = mDatas.get(holder.getAdapterPosition());

                FruitDetailActivity.openActivity(mContext,fruit.getName(),fruit.getImageId());
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(CardViewViewHolder holder, int position) {
        holder.mFruitName.setText(mDatas.get(position).getName());
        Glide.with(mContext).load(mDatas.get(position).getImageId()).into(holder.mFruitImage);
    }

    @Override
    public int getItemCount() {
        return mDatas.isEmpty() ? 0 : mDatas.size();
    }

    class CardViewViewHolder extends RecyclerView.ViewHolder {

        TextView mFruitName;
        ImageView mFruitImage;
        CardView mCardView ;

        public CardViewViewHolder(View itemView) {
            super(itemView);
            mFruitImage = (ImageView) itemView.findViewById(R.id.iv_fruit_image);
            mFruitName = (TextView) itemView.findViewById(R.id.tv_fruit_name);
            mCardView = (CardView) itemView;
        }
    }
}
