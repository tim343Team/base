package tim.com.libnetwork.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * ${description}
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
        if (getUserVisibleHint() && isFirst && isNeedLoad) {
            loadData();
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
                    RefreshloadData();
                    isFirst = false;
                }
            });
        }
    }

    protected abstract String getmTag();

}
