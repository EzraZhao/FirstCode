package com.xz.firstcode.activity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import com.xz.firstcode.ActivityCollector;
import com.xz.firstcode.R;

/**
 * Created by xz on 2016/11/23.
 */

public class SecondActivity extends Activity {
    @Override
    public void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.second_layout);
        Log.d("Now", getClass().getSimpleName());
        ActivityCollector.addActivity(this);

        Button button = (Button) findViewById(R.id.button_2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent();
//                intent.putExtra("data_return", "Hello MainActivity");
//                setResult(RESULT_OK, intent);
//                finish();

                //直接退出程序
                ActivityCollector.finishAll();
            }
        });



    }
}
