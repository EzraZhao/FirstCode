package com.ezra.httptest;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * Created by Ezra on 2017/3/1.
 */

public class HttpUtil {

    public static void sentHttpRequest(final String address, final HttpCallbackListener listener) {

        if (!isNetworkAvailable()) {
            Toast.makeText(MyApplication.getContext(),
                    "network is unavailable", Toast.LENGTH_SHORT).show();
            return;
        }

        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection connection = null;

                try {
                    URL url = new URL(address);
                    connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setReadTimeout(8000);
                    connection.setConnectTimeout(8000);
                    connection.setDoInput(true);
                    connection.setDoOutput(true);

                    InputStream in = connection.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                    StringBuilder response = new StringBuilder();
                    String line = null;
                    while ((line = reader.readLine()) != null) {
                        response.append(line);
                    }
                    if (listener != null) {
                        //回调onFinish方法
                        listener.onFinish(response.toString());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    if (listener != null) {
                        //回调onError方法
                        listener.onError(e);
                    }
                } finally {
                    if (connection != null) {
                        connection.disconnect();
                    }
                }
            }
        }).start();
    }

    private static boolean isNetworkAvailable() {
        Context context = MyApplication.getContext();
        if (context != null) {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
            if (mNetworkInfo != null) {
                return mNetworkInfo.isAvailable();
            }
        }
        return false;
    }

    public static void sendOkHttpRequest(String address, okhttp3.Callback callback) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                                    .url(address)
                                    .build();
        client.newCall(request).enqueue(callback);
    }

}
