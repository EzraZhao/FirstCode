package com.ezra.downloadservicetest;

/**
 * 回调接口，对下载过程中的各种状态进行监听和回调
 * Created by Ezra on 2017/3/2.
 */

public interface DownloadListener {

    void onProgress(int progress);
    void onSuccess();
    void onFailed();
    void onPaused();
    void onCanceled();
}
