package com.fanda.zeng.bookpratice.transition;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.fanda.zeng.bookpratice.R;
import com.fanda.zeng.bookpratice.activity.BaseActivity;
import com.transitionseverywhere.ChangeBounds;
import com.transitionseverywhere.ChangeImageTransform;
import com.transitionseverywhere.TransitionManager;
import com.transitionseverywhere.TransitionSet;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by 曾凡达 on 2017/5/14.
 * des
 */

public class ImageTransformActivity extends BaseActivity {

    @BindView(R.id.iv_apple)
    ImageView iv_apple;

    @BindView(R.id.transitions_container)
    ViewGroup transitions_container;

    boolean isClick;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_transform);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.iv_apple)
    public void onClick(View view) {
        isClick = !isClick;
        TransitionManager.beginDelayedTransition(transitions_container, new TransitionSet().addTransition(new ChangeBounds()).addTransition(new ChangeImageTransform()));
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) iv_apple.getLayoutParams();
        params.height = isClick ? LinearLayout.LayoutParams.MATCH_PARENT : 300;
        params.width = isClick ? LinearLayout.LayoutParams.MATCH_PARENT : 300;
        iv_apple.setScaleType(isClick ? ImageView.ScaleType.FIT_CENTER : ImageView.ScaleType.FIT_CENTER);

        iv_apple.setLayoutParams(params);
    }

}
