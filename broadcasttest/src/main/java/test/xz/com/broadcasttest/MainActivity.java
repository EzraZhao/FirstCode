package test.xz.com.broadcasttest;

import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * 广播测试：
 * 注册广播两种方式：在代码中注册（动态注册）在AndroidManifest.xml中注册（静态注册）
 * 本次测试通过广播监听网络变化
 * 查询系统网络状态需声明权限
 */

public class MainActivity extends AppCompatActivity {

    private IntentFilter intentFilter;
    private NetworkChangeReceive networkChangeReceive;

    private LocalReceiver localReceiver;
    private LocalBroadcastManager localBroadcastManager;

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //动态注册的广播接收器一定要取消注册才行
        unregisterReceiver(networkChangeReceive);
        localBroadcastManager.unregisterReceiver(localReceiver);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //动态注册广播
        intentFilter = new IntentFilter();
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        networkChangeReceive = new NetworkChangeReceive();
        registerReceiver(networkChangeReceive,intentFilter);

        //发送自定义广播
        Button sendBroadcast = (Button) findViewById(R.id.send_broadcast);
        sendBroadcast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("test.xz.com.broadcasttest.MY_BROADCAST");
                //由于广播使用的是Intent传递，所以可以在Inten中客同时用作传递数据
                //将sendBroadcast(intent)改为sendOrderBroadcast(intent)即可发送有序广播
                sendBroadcast(intent);
            }
        });

        //本地广播
        //获取实例
        localBroadcastManager = LocalBroadcastManager.getInstance(this);
        Button sendLocalBroadcast = (Button) findViewById(R.id.send_localbroadcast);
        sendLocalBroadcast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("test.xz.com.broadcasttest.LOCAL_BROADCAST");
                localBroadcastManager.sendBroadcast(intent);
            }
        });

        //动态注册本地广播监听器
        intentFilter = new IntentFilter();
        intentFilter.addAction("test.xz.com.broadcasttest.LOCAL_BROADCAST");
        localReceiver = new LocalReceiver();
        localBroadcastManager.registerReceiver(localReceiver,intentFilter);
    }
}
