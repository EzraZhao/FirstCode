package com.ezra.litepaltest;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by Ezra on 2017/2/24.
 *
 * 将Toast进行封装，避免因用户不小心连续点击产生长时间不消失，
 * 同时对Toast进行优化，减少参数。
 *
 * genymotion
 */

public class Util {

        private static Toast toast;

        public static void showToast(Context context, String content) {

                if (toast ==null) {
                        toast = Toast.makeText(context, content, Toast.LENGTH_SHORT);
                } else {

                        toast.setText(content);
                }
                toast.show();
        }

}
