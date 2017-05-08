package com.fanda.zeng.bookpratice.util;

import android.app.Activity;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.util.Pair;
import android.view.View;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by David on 2017/5/8.
 * Transition辅助类
 */

public class TransitionHelper {

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public static Pair<View, String>[] createSafeTransition(@NonNull Activity activity, boolean includeStatusBar, @NonNull Pair... otherParts) {

        //处理UI界面崩溃等问题
        View decor = activity.getWindow().getDecorView();
        View statusBar = null;
        if (includeStatusBar) {
            statusBar = decor.findViewById(android.R.id.statusBarBackground);
        }
        View navBar = decor.findViewById(android.R.id.navigationBarBackground);

        List<Pair> participants = new ArrayList<>(3);
        addNonNullParticipants(statusBar, participants);
        addNonNullParticipants(navBar, participants);
        if (otherParts != null && !(otherParts.length == 1 && otherParts[0] == null)) {
            participants.addAll(Arrays.asList(otherParts));
        }
        return participants.toArray(new Pair[participants.size()]);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private static void addNonNullParticipants(View view, List<Pair> pairList) {

        if (view == null) {
            return;
        }
        pairList.add(new Pair<>(view, view.getTransitionName()));
    }
}
