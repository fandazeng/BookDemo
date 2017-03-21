package com.fanda.zeng.bookpratice.chapter8;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.widget.VideoView;

import com.fanda.zeng.bookpratice.R;
import com.fanda.zeng.bookpratice.activity.BaseActivity;

import java.io.File;

/**
 * Created by 曾凡达 on 2017/3/22.
 * des  视频播放
 */

public class PlayVideoActivity extends BaseActivity implements View.OnClickListener {
    private VideoView videoView;
    private Button play;
    private Button pause;
    private Button replay;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_video);
        initViews();
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        } else {
            initVideoPath();
        }
    }

    private void initViews() {
        play = (Button) findViewById(R.id.btn_play);
        pause = (Button) findViewById(R.id.btn_pause);
        replay = (Button) findViewById(R.id.btn_replay);
        videoView = (VideoView) findViewById(R.id.video_view);

        play.setOnClickListener(this);
        pause.setOnClickListener(this);
        replay.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_play:
                if (!videoView.isPlaying()) {
                    videoView.start();//播放
                }
                break;
            case R.id.btn_pause:
                if (videoView.isPlaying()) {
                    videoView.pause();//暂停
                }
                break;

            case R.id.btn_replay:
                if (videoView.isPlaying()) {
                    videoView.resume();//重播
                }

                break;
        }
    }

    private void initVideoPath() {
        File file = new File(Environment.getExternalStorageDirectory(), "/DCIM/Camera/VID_20170210_174431.mp4");
        videoView.setVideoPath(file.getPath());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (videoView != null) {
            videoView.suspend();//释放资源
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    initVideoPath();
                } else {
                    Toast.makeText(this, "拒绝权限将无法使用程序", Toast.LENGTH_SHORT).show();
                    finish();
                }
                break;
        }
    }
}
