package tim.com.libnetwork.utils;

import android.bluetooth.BluetoothAdapter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;

public class ToolUtils {
    // 上次点击的时间
    private static long lastClickTime;

    //获取view的宽度
    public static int getViewWidth(View view) {
        int width = 0;
        try { //记得以前调用这个方法会在某些手机中抛异常，所以保险为主
            int w = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
            int h = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
            view.measure(w, h);
            width = view.getMeasuredWidth();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return width;
    }

    /**
     * 是否是快速点击
     *
     * @return true 快速点击 false 常规点击
     */
    public static boolean isFastDoubleClick() {
        return isFastDoubleClick(600);
    }

    public static boolean isFastDoubleClick(long fastTime) {
        long time = System.currentTimeMillis();
        if (Math.abs(time - lastClickTime) > fastTime) {
            lastClickTime = time;
            return false;
        }
        return true;
    }

    //蓝牙是否打开
    public static boolean isCloseBlue() {
        BluetoothAdapter blueadapter = BluetoothAdapter.getDefaultAdapter();
        return !blueadapter.isEnabled();
    }
}
