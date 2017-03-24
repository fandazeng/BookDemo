package com.fanda.zeng.bookpratice.service;

/**
 * Created by 曾凡达 on 2017/3/22.
 * des 下载监听回调接口
 */

public interface DownloadListener {
    void onProgress(int progress);

    void onSuccess();

    void onFailed();

    void onPaused();

    void onCanceled();

}
