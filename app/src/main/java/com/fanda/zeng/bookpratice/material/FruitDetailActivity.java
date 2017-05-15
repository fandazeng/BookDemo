package com.fanda.zeng.bookpratice.material;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.transition.ChangeBounds;
import android.transition.Slide;
import android.transition.TransitionSet;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.fanda.zeng.bookpratice.R;
import com.fanda.zeng.bookpratice.activity.BaseActivity;
import com.fanda.zeng.bookpratice.util.TransitionHelper;

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

    public static void openActivity(Activity activity, String name, int imageId) {
        Intent intent = new Intent(activity, FruitDetailActivity.class);
        intent.putExtra(FruitDetailActivity.FRUIT_NAME, name);
        intent.putExtra(FruitDetailActivity.FRUIT_IMAGE_ID, imageId);
        activity.startActivity(intent);
    }

    /**
     * 内容变换转场跳转
     *
     * @param activity
     * @param name
     * @param imageId
     */
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public static void openTransitionActivity(Activity activity, String name, int imageId) {
        Intent intent = new Intent(activity, FruitDetailActivity.class);
        intent.putExtra(FruitDetailActivity.FRUIT_NAME, name);
        intent.putExtra(FruitDetailActivity.FRUIT_IMAGE_ID, imageId);
        Pair<View, String>[] pairs = TransitionHelper.createSafeTransition(activity, false);
        ActivityOptionsCompat compat = ActivityOptionsCompat.makeSceneTransitionAnimation(activity, pairs);
        activity.startActivity(intent, compat.toBundle());
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public static void openShareTransitionActivity(Activity activity, String name, int imageId, Pair... pairs) {
        Intent intent = new Intent(activity, FruitDetailActivity.class);
        intent.putExtra(FruitDetailActivity.FRUIT_NAME, name);
        intent.putExtra(FruitDetailActivity.FRUIT_IMAGE_ID, imageId);
        ActivityOptionsCompat compat = ActivityOptionsCompat.makeSceneTransitionAnimation(activity, pairs);
        activity.startActivity(intent, compat.toBundle());
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fruit_detail);
//        setupWindowAnimations();
        setupShareWindowAnimations();
        initDatas();
        initViews();
        setDatas();
    }

    private void setupShareWindowAnimations() {

        TransitionSet transitionSet= null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            transitionSet = new TransitionSet();
            //改变view的位置
//            ChangePositionTransition changePositionTransition=new ChangePositionTransition();
//            ColorTransition colorTransition=new ColorTransition(getResources().getColor(R.color.colorPrimary), getResources().getColor(R.color.colorAccent));
//            //view做RevealTransition
//            CircleShareElemEnterTransition shareElemEnterRevealTransition=new CircleShareElemEnterTransition(rightTop);
//
//            transitionSet.addTransition(shareElemEnterRevealTransition);
//            transitionSet.addTransition(colorTransition);
//            transitionSet.addTransition(changePositionTransition);
//
//            transitionSet.addTarget(R.id.rightTop);
//            transitionSet.setDuration(500);
            getWindow().setSharedElementEnterTransition(new ChangeBounds());
        }


    }

    private void setupWindowAnimations() {
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            TransitionSet transitionSet = new TransitionSet();

            Slide slide2 = new Slide();
            slide2.setSlideEdge(Gravity.BOTTOM);
            slide2.setDuration(500);

            transitionSet.addTransition(slide2);
            getWindow().setEnterTransition(transitionSet);

        }
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
        fruitImageId = getIntent().getIntExtra(FRUIT_IMAGE_ID, 0);

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
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    finishAfterTransition();//用这个结束方法
                } else {
                    finish();
                }
                break;
        }
        return true;
    }

}
