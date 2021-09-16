package tim.com.libnetwork.base;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;

import java.util.ArrayList;
import java.util.List;

/**
 * $在Activity中使用FrameLayout容器控制，显示，删除多个Fragment
 *
 * @author weiqiliu
 * @version 1.0 2020/5/6
 */
public abstract class BaseTransFragmentActivity extends BaseActivity{
    protected List<BaseTransFragment> fragments = new ArrayList<>();
    protected BaseFragment currentFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (savedInstanceState == null) initFragments();
        super.onCreate(savedInstanceState);
    }

    public void addFragment(BaseTransFragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(getContainerId(), fragment, fragment.getmTag()).commit();
    }

    public void removeFragment(BaseFragment fragment) {
        getSupportFragmentManager().beginTransaction().remove(fragment).commit();
        fragments.remove(fragment);
    }

    protected void showFragment(BaseTransFragment fragment) {
        if (currentFragment == fragment) return;
        currentFragment = fragment;
        hideFragments();
        if (!fragment.isAdded()) addFragment(fragment);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.show(fragment).commit();
    }

    protected void hideFragments() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        for (int i = 0; i < fragments.size(); i++) {
            if (!fragments.get(i).isHidden() && currentFragment != fragments.get(i)) {
                transaction.hide(fragments.get(i));
            }
        }
        transaction.commit();
    }

    protected void hideFragment(BaseFragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.hide(fragment);
        transaction.commit();
    }

    /**
     * 在此方法中恢复Fragment
     * xxxFragment=getSupportFragmentManager().findFragmentByTag(XXFragment.TAG);
     * fragments.add(xxxFragment);
     */
    protected abstract void recoverFragment();

    /**
     * 在此方法中初始化Fragment
     * fragments.add(new xxxFragment());
     */
    protected abstract void initFragments();

    /**
     * 必须实现的方法 ，设置FrameLayout Id
     */
    public abstract int getContainerId();
}
