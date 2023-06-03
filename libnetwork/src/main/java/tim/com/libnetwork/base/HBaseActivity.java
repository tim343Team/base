package tim.com.libnetwork.base;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

public abstract class HBaseActivity extends AppCompatActivity {
    //不设置
    public static final int STATUSBAR_NONE_STYLE = 0X0000;
    //沉浸式
    public static final int STATUSBAR_TRANSLUCENT_STYLE = 0X0001;
    //WINDOW_COLOR 保持一致
    public static final int STATUSBAR_WINDOW_COLOR_STYLE = 0X0002;
    //图标暗色
    public static final int STATUSBAR_DARK = 0X0004;
    //图标亮色
    public static final int STATUSBAR_NO_DARK = 0X0010;

    public int statusStyle = STATUSBAR_WINDOW_COLOR_STYLE;
    public int statusTextStyle = STATUSBAR_DARK;

    protected BaseActivityHelper mBaseActivityHelper = new BaseActivityHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBaseActivityHelper.applyStatusBarStyle(statusBarStyle());
    }

    protected int statusBarStyle() {
        return statusStyle | statusTextStyle;
    }
}
