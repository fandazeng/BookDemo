package com.fanda.zeng.bookpratice.transition;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.fanda.zeng.bookpratice.R;
import com.fanda.zeng.bookpratice.activity.BaseActivity;
import com.transitionseverywhere.ChangeText;
import com.transitionseverywhere.TransitionManager;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by 曾凡达 on 2017/5/14.
 * des
 */

public class ChangeTexActivity extends BaseActivity {

    public static final String TEXT_1 = "Hi, i am text. Tap on me!";
    public static final String TEXT_2 = "Thanks! Another tap?";

    @BindView(R.id.tv_change_text)
    TextView tv_change_text;

    @BindView(R.id.transitions_container)
    ViewGroup transitions_container;

    boolean isClick;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_text);
        ButterKnife.bind(this);
        tv_change_text.setText(TEXT_1);
    }

    @OnClick(R.id.tv_change_text)
    public void onClick(View view) {
        isClick = !isClick;
        TransitionManager.beginDelayedTransition(transitions_container, new ChangeText().setChangeBehavior(ChangeText.CHANGE_BEHAVIOR_OUT_IN));
        tv_change_text.setText(isClick ? TEXT_1 : TEXT_2);

    }
}
