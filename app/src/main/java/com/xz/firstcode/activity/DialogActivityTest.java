package com.xz.firstcode.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.widget.TextView;

import com.xz.firstcode.ActivityCollector;
import com.xz.firstcode.R;

/**
 * Created by xz on 2016/11/24.
 */

public class DialogActivityTest extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_layout);
        ActivityCollector.addActivity(this);
    }
}
