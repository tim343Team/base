package tim.com.libnetwork.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * ${description}
 *
 * @author weiqiliu
 * @version 1.0 2020/5/6
 */
public abstract class BaseFragment extends Fragment {
    protected View rootView;
    Unbinder unbinder;
    protected boolean isInit = false;
    protected boolean isNeedLoad = true;
    protected boolean isSetTitle = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = null;
        int activityLayoutId = getLayoutId();
        if (activityLayoutId != 0) {
            rootView = inflater.inflate(getLayoutId(), null);
        }
        if (rootView == null) {
            rootView = getLayoutView();
        }
        rootView = inflater.inflate(getLayoutId(), null);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
//                if (isImmersionBarEnabled()) {
//            initImmersionBar();
//        }
        init();
        isInit = true;
        initViews(savedInstanceState);
        obtainData();
        fillWidget();
    }

    protected boolean isImmersionBarEnabled() {
        return true;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    public void displayLoadingPopup() {
        if (getActivity() != null) ((BaseActivity) getActivity()).displayLoadingPopup();
    }

    public void hideLoadingPopup() {
        if (getActivity() != null) ((BaseActivity) getActivity()).hideLoadingPopup();
    }

    @Override
    public View getView() {
        return rootView;
    }

    @Override
    public void onDestroyView() {
//        if (immersionBar != null) immersionBar.destroy();
        unbinder.unbind();
        initDestroy();
        super.onDestroyView();
    }

    protected void finish() {
        getActivity().finish();
    }

    public BaseActivity getmActivity() {
        return (BaseActivity) super.getActivity();
    }

    protected abstract int getLayoutId();

    protected abstract View getLayoutView();

    protected abstract void init();

    protected abstract void initViews(Bundle savedInstanceState);

    protected abstract void obtainData();

    protected abstract void fillWidget();

    protected abstract void loadData();

    protected abstract void RefreshloadData();

    protected abstract void initDestroy();

}
