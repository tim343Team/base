package tim.com.libnetwork.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

abstract class HBaseActivity : AppCompatActivity() {
    companion object {
        //不设置
        const val STATUSBAR_NONE_STYLE = 0X0000

        //沉浸式
        const val STATUSBAR_TRANSLUCENT_STYLE = 0X0001

        //WINDOW_COLOR 保持一致
        const val STATUSBAR_WINDOW_COLOR_STYLE = 0X0002

        //图标暗色
        const val STATUSBAR_DARK = 0X0004

        //图标亮色
        const val STATUSBAR_NO_DARK = 0X0010

    }

    public var statusStyle = STATUSBAR_WINDOW_COLOR_STYLE

    public var statusTextStyle = STATUSBAR_DARK

    protected val mBaseActivityHelper = BaseActivityHelper(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBaseActivityHelper.applyStatusBarStyle(statusBarStyle())
    }

    protected open fun statusBarStyle(): Int {
        return statusStyle or statusTextStyle
    }
}