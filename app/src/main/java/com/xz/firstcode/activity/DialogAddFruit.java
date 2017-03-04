package com.xz.firstcode.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

import com.xz.firstcode.ActivityCollector;
import com.xz.firstcode.R;

/**
 * Created by Ezra on 2017/2/10.
 */

public class DialogAddFruit extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_layout);
        ActivityCollector.addActivity(this);
    }
}
