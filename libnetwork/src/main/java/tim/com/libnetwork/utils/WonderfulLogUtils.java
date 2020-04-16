package tim.com.libnetwork.utils;

import android.util.Log;

import tim.com.libnetwork.app.MyApplication;

/**
 * ${description}
 *
 * @author weiqiliu
 * @version 1.0 2020/4/13
 */
public class WonderfulLogUtils {
    public static void logi(String TAG, String content) {
        Log.i(TAG, content);
    }

    public static void show(String TAG, String str) {
        str = str.trim();
        int index = 0;
        int maxLength = 4000;
        String sub;
        while (index < str.length()) {
            // java的字符不允许指定超过总的长度end
            if (str.length() <= index + maxLength) {
                sub = str.substring(index);
            } else {
                sub = str.substring(index, maxLength);
            }

            index += maxLength;
            Log.i(TAG, sub.trim());

        }
    }
}
