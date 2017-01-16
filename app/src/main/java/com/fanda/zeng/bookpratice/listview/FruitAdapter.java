package com.fanda.zeng.bookpratice.listview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.fanda.zeng.bookpratice.R;

import java.util.List;

/**
 * Created by zfd on 2017/1/16 0016.
 * 水果适配器
 */

public class FruitAdapter extends ArrayAdapter<Fruit> {

    private List<Fruit> mDatas;
    private Context mContext;
    private int resourceId;

    public FruitAdapter(Context context, int resource, List<Fruit> objects) {
        super(context, resource, objects);
        this.mContext = context;
        this.resourceId = resource;
        this.mDatas = objects;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view ;
        FruitViewHolder holder ;
        if (convertView == null) {
            holder = new FruitViewHolder();
            view = LayoutInflater.from(mContext).inflate(resourceId, null);
            holder.tv_fruit_name = (TextView) view.findViewById(R.id.tv_fruit_name);
            holder.iv_fruit_image = (ImageView) view.findViewById(R.id.iv_fruit_image);
            view.setTag(holder);
        } else {
            view = convertView;
            holder = (FruitViewHolder) view.getTag();
        }
        holder.tv_fruit_name.setText(mDatas.get(position).getName());
        holder.iv_fruit_image.setImageResource(mDatas.get(position).getImageId());

        return view;
    }

    class FruitViewHolder {
        TextView tv_fruit_name;
        ImageView iv_fruit_image;
    }

}
