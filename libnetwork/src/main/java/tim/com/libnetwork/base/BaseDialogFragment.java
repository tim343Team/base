package tim.com.libnetwork.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * $ 弹窗基类 可自定义弹窗类型和弹窗显示位置
 *
 * @author weiqiliu
 * @version 1.0 2020/4/27
 */
public abstract class BaseDialogFragment extends DialogFragment {
    protected int theme;
    protected int gravity = Gravity.BOTTOM;
    protected int width = 0;
    protected Window window;
    protected Unbinder unbinder;
    protected View rootView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setBase();
        setStyle(DialogFragment.STYLE_NORMAL, theme);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(getLayoutId(), container, false);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        unbinder = ButterKnife.bind(this, view);
        prepareView(view);
        window = getDialog().getWindow();
        initView();
        fillWidget();
        loadData();
    }

    @Override
    public void onResume() {
        ViewGroup.LayoutParams params = getDialog().getWindow().getAttributes();
        params.width = width == 0 ? WindowManager.LayoutParams.MATCH_PARENT : width;
        params.height = WindowManager.LayoutParams.WRAP_CONTENT;
        getDialog().getWindow().setAttributes((WindowManager.LayoutParams) params);
        getDialog().getWindow().setGravity(gravity);
        super.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
        destroyView();
    }

    public void setTheme(int theme) {
        this.theme = theme;
    }

    public void setGravity(int gravity) {
        this.gravity = gravity;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    private void setBase() {

    }

    protected abstract int getLayoutId();

    protected abstract void prepareView(View view);

    protected abstract void destroyView();

    protected abstract void initView();

    protected abstract void fillWidget();

    protected abstract void loadData();
}
