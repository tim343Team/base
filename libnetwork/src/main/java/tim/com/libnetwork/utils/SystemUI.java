package tim.com.libnetwork.utils;

import android.view.View;
import android.view.Window;

public class SystemUI {
    public static void setStatusBarStyle(Window window, boolean dark) {
        View decorView = window.getDecorView();
        int systemUi = decorView.getSystemUiVisibility();
        if (dark) {
            systemUi |= 8192;
        } else {
            systemUi &= -8193;
        }
        decorView.setSystemUiVisibility(systemUi);
    }

    public static void setStatusBarColor(final Window window, int color) {
        window.addFlags(-2147483648);
        window.setStatusBarColor(color);
    }
}
