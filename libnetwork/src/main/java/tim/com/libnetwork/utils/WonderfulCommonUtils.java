package tim.com.libnetwork.utils;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;

import java.lang.reflect.Method;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Administrator on 2017/9/1.
 */

public class WonderfulCommonUtils {

    /**
     * 将指定内容粘贴到剪贴板
     *
     * @param content 剪切内容
     */
    public static void copyText(final Context context, final String content) {
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                /**
                 *要执行的操作
                 */
                ClipboardManager cm = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData myClip = ClipData.newPlainText(null, content);
                cm.setPrimaryClip(myClip);
            }
        };
        Timer timer = new Timer();
        timer.schedule(task, 800);//3秒后执行TimeTask的run方法
    }

    /**
     * 获取版本号
     */
    public static String getVersionName(Context context) {
        PackageManager manager = context.getPackageManager();
        try {
            //第二个参数代表额外的信息，例如获取当前应用中的所有的Activity
            PackageInfo packageInfo = manager.getPackageInfo(context.getPackageName(), PackageManager.GET_ACTIVITIES);
//            ActivityInfo[] activities = packageInfo.activities;
//            showActivities(activities);
            return packageInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取手机序列号
     *
     * @return
     */
    public static String getSerialNumber() {
        String serialnum = null;
        try {
            Class<?> c = Class.forName("android.os.SystemProperties");
            Method get = c.getMethod("get", String.class, String.class);
            serialnum = (String) (get.invoke(c, "ro.serialno", "unknown"));
        } catch (Exception ignored) {
        }
        return serialnum;
    }

    public static  int getStatusBarHeight(Context context) {
        Resources resources = context.getResources();
        int resourceId = resources.getIdentifier("status_bar_height", "dimen", "android");
        int height = resources.getDimensionPixelSize(resourceId);
        return height;
    }
}
