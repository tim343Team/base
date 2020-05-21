package tim.com.libnetwork.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

/**
 * ${description}
 *
 * @author weiqiliu
 * @version 1.0 2020/5/21
 */
public abstract class BaseLazyFragment extends BaseFragment {
    /**
     * 是否正在加载数据
     */
    protected boolean isLoad = false;
    private boolean isFirst = true;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tryToLoadData();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        tryToLoadData();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (getUserVisibleHint() && !isFirst && isNeedLoad) {
            RefreshloadData();
        }
    }

    private void tryToLoadData() {
        if (!isInit) return;
        if (!isNeedLoad) return;
        if (getUserVisibleHint()) {
            rootView.post(new Runnable() {
                @Override
                public void run() {
                    loadData();
                    isFirst = false;
                }
            });
            isLoad = true;
        } else {
            if (isLoad) stopLoad();
        }
    }

    @Override
    public void onDestroyView() {
        isInit = false;
        isLoad = false;
        super.onDestroyView();
    }

    /**
     * 隐藏时停止 加载数据
     */
    protected void stopLoad() {
    }
}
