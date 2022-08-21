package tim.com.libnetwork.base

import android.graphics.Color
import android.view.View
import tim.com.libnetwork.utils.SystemUI

class BaseActivityHelper(val activity: HBaseActivity) {

     fun applyStatusBarStyle(statusBarStyle: Int) {
        if (statusBarStyle == HBaseActivity.STATUSBAR_NONE_STYLE) {
            return
        }
        if (statusBarStyle and HBaseActivity.STATUSBAR_TRANSLUCENT_STYLE == HBaseActivity.STATUSBAR_TRANSLUCENT_STYLE) {
            val docorView = activity.window.decorView
            docorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
            activity.window.statusBarColor = Color.TRANSPARENT
        } else if (statusBarStyle and HBaseActivity.STATUSBAR_WINDOW_COLOR_STYLE == HBaseActivity.STATUSBAR_WINDOW_COLOR_STYLE) {
            SystemUI.setStatusBarColor(activity.window, Color.WHITE)
        }

        if (statusBarStyle and HBaseActivity.STATUSBAR_DARK == HBaseActivity.STATUSBAR_DARK) {
            SystemUI.setStatusBarStyle(activity.window, true)
        } else if (statusBarStyle and HBaseActivity.STATUSBAR_NO_DARK == HBaseActivity.STATUSBAR_NO_DARK) {
            SystemUI.setStatusBarStyle(activity.window, false)
        }

    }
}