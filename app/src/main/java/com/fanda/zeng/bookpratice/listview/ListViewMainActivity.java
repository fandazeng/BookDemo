package com.fanda.zeng.bookpratice.listview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.fanda.zeng.bookpratice.R;
import com.fanda.zeng.bookpratice.activity.BaseActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/1/16 0016.
 */

public class ListViewMainActivity extends BaseActivity implements AdapterView.OnItemClickListener {

    private List<Fruit> mDataList ;
    private ListView mListView ;
    private FruitAdapter mFruitAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview_main);
        initView();
    }

    private void initView() {
        initFruits();
        mListView = (ListView) findViewById(R.id.lv_fruit_list);
        mListView.setOnItemClickListener(this);
        mFruitAdapter = new FruitAdapter(this, R.layout.item_fruit, mDataList);
        mListView.setAdapter(mFruitAdapter);
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
