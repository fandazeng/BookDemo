package com.fanda.zeng.bookpratice.transition;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.ViewGroup;
import android.view.animation.BounceInterpolator;
import android.widget.RadioGroup;

import com.fanda.zeng.bookpratice.R;
import com.fanda.zeng.bookpratice.activity.BaseActivity;
import com.transitionseverywhere.ChangeBounds;
import com.transitionseverywhere.Scene;
import com.transitionseverywhere.Slide;
import com.transitionseverywhere.TransitionInflater;
import com.transitionseverywhere.TransitionManager;
import com.transitionseverywhere.TransitionSet;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;

/**
 * Created by David on 2017/5/12.
 * 场景动画演示类
 */

public class SceneTransitionActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener {

    @BindView(R.id.scene_root)
    ViewGroup scene_root;

    @BindView(R.id.rg_root)
    RadioGroup rg_root;

    Scene scene_first;
    Scene scene_second;
    Scene scene_third;

    TransitionManager customTransitionManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transition_scene);
        ButterKnife.bind(this);
        rg_root.setOnCheckedChangeListener(this);
        initScenes();
    }

    private void initScenes() {
//        scene_first = new Scene(scene_root, scene_root.findViewById(R.id.container));没起到作用，可能API不同
        scene_first = Scene.getSceneForLayout(scene_root, R.layout.include_scene_first, this);
        scene_second = Scene.getSceneForLayout(scene_root, R.layout.include_scene_second, this);
        scene_third = Scene.getSceneForLayout(scene_root, R.layout.include_scene_third, this);

        customTransitionManager = TransitionInflater.from(this).inflateTransitionManager(R.transition.custom_transition_manager, scene_root);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
        switch (checkedId) {
            case R.id.rb_first_scene:
                TransitionManager.go(scene_first);
                break;
            case R.id.rb_second_scene:
                TransitionSet transitionSet = new TransitionSet();
                Slide slide = new Slide(Gravity.LEFT);
                slide.addTarget(R.id.transition_title);
                transitionSet.addTransition(slide);
                transitionSet.addTransition(new ChangeBounds());
                transitionSet.setOrdering(TransitionSet.ORDERING_SEQUENTIAL);
                transitionSet.setDuration(350);

                TransitionManager.go(scene_second, transitionSet);
//                TransitionManager.go(scene_second, TransitionInflater.from(this).inflateTransition(R.transition.slide_and_changebounds));

                break;

            case R.id.rb_third_scene:
//                TransitionManager.go(scene_third);
//                customTransitionManager.go(scene_third);
                customTransitionManager.transitionTo(scene_third);
                break;
        }
    }
}
