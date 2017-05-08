package com.fanda.zeng.bookpratice.recyclerview;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
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
import com.fanda.zeng.bookpratice.material.DrawerLayoutActivity;
import com.fanda.zeng.bookpratice.material.FruitDetailActivity;
import com.fanda.zeng.bookpratice.util.TransitionHelper;

import java.util.List;

/**
 * Created by 曾凡达 on 2017/3/20.
 * des CardView版的水果列表
 */

public class CardViewFruitAdapter extends RecyclerView.Adapter<CardViewFruitAdapter.CardViewViewHolder> {

    private List<Fruit> mDatas;
    private Activity activity;

    public CardViewFruitAdapter(List<Fruit> mDatas, Activity activity) {
        this.mDatas = mDatas;
        this.activity = activity;
    }

    @Override
    public CardViewViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
      /*  if (mContext == null) {
            mContext = parent.getContext();
        }*/
//        return new CardViewViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_fruit_card, null));//不能这样加载，margin会无效
        View view = LayoutInflater.from(activity).inflate(R.layout.item_fruit_card, parent, false);
        final CardViewViewHolder holder = new CardViewViewHolder(view);
        holder.mCardView.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                Fruit fruit = mDatas.get(holder.getAdapterPosition());
                Pair<View, String>[] pairs = TransitionHelper.createSafeTransition(activity, false, new Pair<>(holder.mFruitImage, activity.getString(R.string.transition_fruit_icon)), new Pair<>(holder.mFruitName, activity.getString(R.string.transition_fruit_name)));
                FruitDetailActivity.openShareTransitionActivity(activity, fruit.getName(), fruit.getImageId(), pairs);
//                FruitDetailActivity.openTransitionActivity(activity, fruit.getName(), fruit.getImageId());
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(CardViewViewHolder holder, int position) {
        holder.mFruitName.setText(mDatas.get(position).getName());
        Glide.with(activity).load(mDatas.get(position).getImageId()).into(holder.mFruitImage);
    }

    @Override
    public int getItemCount() {
        return mDatas.isEmpty() ? 0 : mDatas.size();
    }

    class CardViewViewHolder extends RecyclerView.ViewHolder {

        TextView mFruitName;
        ImageView mFruitImage;
        CardView mCardView;

        public CardViewViewHolder(View itemView) {
            super(itemView);
            mFruitImage = (ImageView) itemView.findViewById(R.id.iv_fruit_image);
            mFruitName = (TextView) itemView.findViewById(R.id.tv_fruit_name);
            mCardView = (CardView) itemView;
        }
    }
}
