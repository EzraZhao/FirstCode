package com.ezra.servicetest;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

/**
 * Created by Ezra on 2017/3/2.
 */

public class DownLoadTask extends AsyncTask<Void, Integer, Boolean> {

    private ProgressDialog progressDialog;
    private Context context;

    @Override
    protected void onPreExecute() {
        //显示进度对话框
        progressDialog.show();
    }

    //执行具体的耗时任务
    @Override
    protected Boolean doInBackground(Void... params) {
        try {
            while (true) {
                int downloadPercent = doDownload();//这是一个虚构的方法
                publishProgress(downloadPercent);
                if (downloadPercent >= 100) {
                    break;
                }
            }
        } catch (Exception e) {
            return false;
        }
        return true;
    }



    //进行UI操作
    @Override
    protected void onProgressUpdate(Integer... values) {
        //在这里更新下载进度
        progressDialog.setMessage("Downloaded" + values[0] + "%");
    }

    //执行任务的收尾工作
    @Override
    protected void onPostExecute(Boolean aBoolean) {
        progressDialog.dismiss();//关闭进度对话框
        //在这里提示下载结果
        if (aBoolean) {
            Toast.makeText(context, "Download succeeded", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Download failed", Toast.LENGTH_SHORT).show();
        }
    }

    private int doDownload() {
        return 0;
    }
}
