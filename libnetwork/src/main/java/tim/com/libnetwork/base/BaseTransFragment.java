package tim.com.libnetwork.base;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;

/**
 * FrameLayout中的fragment使用，监听fragment的状态，是第一次加载还是刷新
 *
 * @author weiqiliu
 * @version 1.0 2020/5/6
 */
public abstract class BaseTransFragment extends BaseFragment {
    private boolean isFirst = true;

    protected List<Fragment> fragments = new ArrayList<>();

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (getUserVisibleHint() && !isFirst && isNeedLoad) {
            RefreshloadData();
        }
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        if (!hidden) {
//            if (immersionBar != null) initImmersionBar();
            if (!isNeedLoad || rootView == null) return;
            rootView.post(new Runnable() {
                @Override
                public void run() {
                    if(isFirst){
                        loadData();
                    }
                    RefreshloadData();
                    isFirst = false;
                }
            });
        }
    }

    protected abstract String getmTag();

}
