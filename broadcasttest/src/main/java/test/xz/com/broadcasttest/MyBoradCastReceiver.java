package test.xz.com.broadcasttest;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by xz on 2016/12/6.
 * 自定义广播之标准广播接收器
 *
 * 有序广播：
 * 设置接收顺序AndroidManifest.xml在intent-filter标签中添加priorityy属性，比如设置为100
 * 优先级高的广播可终止传递abortBroadcast();
 */

public class MyBoradCastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "received in MyBoradCastReceiver", Toast.LENGTH_SHORT).show();
    }
}
