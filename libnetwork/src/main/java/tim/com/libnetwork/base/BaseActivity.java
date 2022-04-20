package tim.com.libnetwork.base;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;

import androidx.appcompat.app.AppCompatActivity;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import tim.com.libnetwork.R;
import tim.com.libnetwork.utils.sysinfo.QMUIStatusBarHelper;

/**
 * $  Activity基类
 *
 * @author weiqiliu
 * @version 1.0 2020/4/20
 */
public abstract class BaseActivity extends AppCompatActivity {
    private PopupWindow loadingPopup;
    private Unbinder unbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        befSuperonCreate();
        super.onCreate(savedInstanceState);
        QMUIStatusBarHelper.setStatusBarLightMode(this);
        setFlag();
        initLanguage();
        setContentView(getActivityLayoutId());
        unbinder = ButterKnife.bind(this);
        init();
        initLoadingPopup();
        obtainData();
        initViews(savedInstanceState);
        fillWidget();
        getWindow().getDecorView().post(new Runnable() {
            @Override
            public void run() {
                loadData();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
        hideLoadingPopup();
    }

    protected void befSuperonCreate() {

    }

    protected void setFlag() {

    }


    private void initLanguage() {
        //TODO updateConfiguration 已于25hou弃用，会导致Android 7.0后语言支持的bug
//        Locale l = null;
//        int code = 1;
//        if (code == 1) {
//            l = Locale.CHINESE;
//            //new PostFormBuilder().addHeader("Accept-Language","zh-CN,zh");
//        } else if (code == 2) {
//            l = Locale.ENGLISH;
//            // new PostFormBuilder().addHeader("Accept-Language","en-us,en");
//        }
//        Resources resources = getResources();
//        Configuration config = resources.getConfiguration();
//        DisplayMetrics dm = resources.getDisplayMetrics();
//        config.locale = l;
//        resources.updateConfiguration(config, dm);
    }

    protected void  init() {

    }

    /**
     * 获取布局ID
     */
    protected abstract int getActivityLayoutId();

    /**
     * 初始化工作
     *
     * @param savedInstanceState
     */
    protected abstract void initViews(Bundle savedInstanceState);

    /**
     * 获取本地或传递的数据
     */
    protected abstract void obtainData();

    /**
     * 控件填充
     */
    protected abstract void fillWidget();

    /**
     * 初始数据加载
     */
    protected abstract void loadData();

    /**
     * 初始化加载dialog
     */
    private void initLoadingPopup() {
        View loadingView = getLayoutInflater().inflate(R.layout.pop_loading, null);
        loadingPopup = new PopupWindow(loadingView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        loadingPopup.setFocusable(true);
        loadingPopup.setClippingEnabled(false);
        loadingPopup.setBackgroundDrawable(new ColorDrawable());
    }

    /**
     * 显示加载框
     */
    public void displayLoadingPopup() {
        try {
            loadingPopup.showAtLocation(getWindow().getDecorView(), Gravity.CENTER, 0, 0);
        }catch (Exception e){

        }
    }

    /**
     * 隐藏加载框
     */
    public void hideLoadingPopup() {
        if (loadingPopup != null) {
            loadingPopup.dismiss();
        }

    }
}
