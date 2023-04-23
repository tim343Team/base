package tim.com.libnetwork.utils;

import android.view.View;
import android.view.Window;

public class SystemUI {
    public static void setStatusBarStyle(Window window, boolean dark) {
        View decorView = window.getDecorView();
        int systemUi = decorView.getSystemUiVisibility();
        if (dark) {
            systemUi |= 8192;//当 SYSTEM_UI_FLAG_LIGHT_STATUS_BAR 标志被设置后，状态栏上的图标和文字颜色会变成黑色
        } else {
            systemUi &= -8193;//
        }
        decorView.setSystemUiVisibility(systemUi);
    }

    public static void setStatusBarColor(final Window window, int color) {
        window.addFlags(-2147483648);
        window.setStatusBarColor(color);
    }
}
