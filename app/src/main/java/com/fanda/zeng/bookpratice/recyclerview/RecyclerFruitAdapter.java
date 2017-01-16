package com.fanda.zeng.bookpratice.recyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.fanda.zeng.bookpratice.R;
import com.fanda.zeng.bookpratice.listview.Fruit;

import java.util.List;

/**
 * Created by zfd on 2017/1/16 0016.
 * 水果适配器
 */

public class RecyclerFruitAdapter extends RecyclerView.Adapter<RecyclerFruitAdapter.FruitViewHolder> {
    private List<Fruit> mDatas;

    public RecyclerFruitAdapter(List<Fruit> mDatas) {
        this.mDatas = mDatas;
    }

    @Override
    public FruitViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_fruit, null);

        final FruitViewHolder holder = new FruitViewHolder(view);


        return holder;
    }

    @Override
    public void onBindViewHolder(FruitViewHolder holder, int position) {
        holder.tv_fruit_name.setText(mDatas.get(position).getName());
        holder.iv_fruit_image.setImageResource(mDatas.get(position).getImageId());
    }

    @Override
    public int getItemCount() {
        return mDatas.isEmpty() ? 0 : mDatas.size();
    }

    class FruitViewHolder extends RecyclerView.ViewHolder {
        TextView tv_fruit_name;
        ImageView iv_fruit_image;
        View fruitView;

        public FruitViewHolder(View itemView) {
            super(itemView);
            fruitView = itemView;
            tv_fruit_name = (TextView) itemView.findViewById(R.id.tv_fruit_name);
            iv_fruit_image = (ImageView) itemView.findViewById(R.id.iv_fruit_image);

            fruitView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(v.getContext(), mDatas.get(getAdapterPosition()).getName(), Toast.LENGTH_SHORT).show();
                }
            });

            iv_fruit_image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(v.getContext(), "imageClick...", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

}
