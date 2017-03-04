package com.xz.firstcode;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * 用于所有活动的管理类
 *
 * Created by xz on 2016/11/28.
 */

public class ActivityCollector {

    public static List<Activity> activities = new ArrayList<>();

    public static void addActivity(Activity activity) {
        activities.add(activity);
    }

    public static void removeActivity(Activity activity) {
        activities.remove(activity);
    }

    public static void finishAll() {
        for (Activity activity: activities ) {
            if(!activity.isFinishing()) {
                activity.finish();
            }
        }
    }

}
