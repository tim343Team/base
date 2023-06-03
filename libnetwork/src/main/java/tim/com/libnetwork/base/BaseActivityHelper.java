package tim.com.libnetwork.base;

import android.graphics.Color;
import android.view.View;
import tim.com.libnetwork.utils.SystemUI;

public class BaseActivityHelper {
    private HBaseActivity activity;

    public BaseActivityHelper(HBaseActivity activity) {
        this.activity = activity;
    }

    public void applyStatusBarStyle(int statusBarStyle) {
        if (statusBarStyle == HBaseActivity.STATUSBAR_NONE_STYLE) {
            return;
        }
        if ((statusBarStyle & HBaseActivity.STATUSBAR_TRANSLUCENT_STYLE) == HBaseActivity.STATUSBAR_TRANSLUCENT_STYLE) {
            View decorView = activity.getWindow().getDecorView();
            decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            activity.getWindow().setStatusBarColor(Color.TRANSPARENT);
        } else if ((statusBarStyle & HBaseActivity.STATUSBAR_WINDOW_COLOR_STYLE) == HBaseActivity.STATUSBAR_WINDOW_COLOR_STYLE) {
            SystemUI.setStatusBarColor(activity.getWindow(), Color.WHITE);
        }

        if ((statusBarStyle & HBaseActivity.STATUSBAR_DARK) == HBaseActivity.STATUSBAR_DARK) {
            SystemUI.setStatusBarStyle(activity.getWindow(), true);
        } else if ((statusBarStyle & HBaseActivity.STATUSBAR_NO_DARK) == HBaseActivity.STATUSBAR_NO_DARK) {
            SystemUI.setStatusBarStyle(activity.getWindow(), false);
        }
    }
}
