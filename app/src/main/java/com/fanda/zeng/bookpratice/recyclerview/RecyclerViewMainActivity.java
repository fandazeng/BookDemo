package com.fanda.zeng.bookpratice.recyclerview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.fanda.zeng.bookpratice.R;
import com.fanda.zeng.bookpratice.activity.BaseActivity;
import com.fanda.zeng.bookpratice.listview.Fruit;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/1/16 0016.
 */

public class RecyclerViewMainActivity extends BaseActivity implements AdapterView.OnItemClickListener {

    private List<Fruit> mDataList ;
    private RecyclerView mRecyclerView ;
    private RecyclerFruitAdapter mFruitAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclerview_main);
        initView();
    }

    private void initView() {
        initFruits();
        mRecyclerView = (RecyclerView) findViewById(R.id.lv_fruit_recycler_list);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mFruitAdapter = new RecyclerFruitAdapter(mDataList);
        mRecyclerView.setAdapter(mFruitAdapter);

    }

    private void initFruits() {
        mDataList = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            Fruit apple = new Fruit("Apple", R.mipmap.apple_pic);
            mDataList.add(apple);
            Fruit banana = new Fruit("Banana", R.mipmap.banana_pic);
            mDataList.add(banana);
            Fruit orange = new Fruit("Orange", R.mipmap.orange_pic);
            mDataList.add(orange);
            Fruit watermelon = new Fruit("Watermelon", R.mipmap.watermelon_pic);
            mDataList.add(watermelon);
            Fruit pear = new Fruit("Pear", R.mipmap.pear_pic);
            mDataList.add(pear);
            Fruit grape = new Fruit("Grape", R.mipmap.grape_pic);
            mDataList.add(grape);
            Fruit pineapple = new Fruit("Pineapple", R.mipmap.pineapple_pic);
            mDataList.add(pineapple);
            Fruit strawberry = new Fruit("Strawberry", R.mipmap.strawberry_pic);
            mDataList.add(strawberry);
            Fruit cherry = new Fruit("Cherry", R.mipmap.cherry_pic);
            mDataList.add(cherry);
            Fruit mango = new Fruit("Mango", R.mipmap.mango_pic);
            mDataList.add(mango);
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(this,mDataList.get(position).getName(),Toast.LENGTH_SHORT).show();
    }
}
