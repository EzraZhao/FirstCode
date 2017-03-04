package com.ezra.httptest;

/**
 * Created by Ezra on 2017/3/1.
 */

public interface HttpCallbackListener {

    void onFinish(String responser);
    void onError(Exception e);
}
