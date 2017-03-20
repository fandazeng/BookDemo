package com.fanda.zeng.bookpratice.material;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.fanda.zeng.bookpratice.R;
import com.fanda.zeng.bookpratice.activity.BaseActivity;

/**
 * Created by 曾凡达 on 2017/3/20.
 * des 水果详情界面
 */

public class FruitDetailActivity extends BaseActivity {

    public static final String FRUIT_NAME = "fruit_name";
    public static final String FRUIT_IMAGE_ID = "fruit_image_id";

    private Toolbar toolbar;
    private CollapsingToolbarLayout collapsingToolbarLayout;
    private TextView fruitContent;
    private ImageView fruitImageView;

    private String fruitName;
    private int fruitImageId;

    public static void openActivity(Context context ,String name, int imageId) {
        Intent intent = new Intent(context, FruitDetailActivity.class);
        intent.putExtra(FruitDetailActivity.FRUIT_NAME,name);
        intent.putExtra(FruitDetailActivity.FRUIT_IMAGE_ID,imageId);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fruit_detail);
        initDatas();
        initViews();
        setDatas();
    }

    private void setDatas() {
        collapsingToolbarLayout.setTitle(fruitName);
        Glide.with(this).load(fruitImageId).into(fruitImageView);
        fruitContent.setText(getFruitContent(fruitName));
    }

    private String getFruitContent(String fruitName) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < 500; i++) {
            builder.append(fruitName);
        }
        return builder.toString();
    }

    private void initDatas() {
        fruitName = getIntent().getStringExtra(FRUIT_NAME);
        fruitImageId = getIntent().getIntExtra(FRUIT_IMAGE_ID,0);

    }

    private void initViews() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        fruitContent = (TextView) findViewById(R.id.tv_fruit_content);
        fruitImageView = (ImageView) findViewById(R.id.iv_fruit_image_view);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return true;
    }
}
