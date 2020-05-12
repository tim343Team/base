package tim.com.libnetwork.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * ${description}
 *
 * @author weiqiliu
 * @version 1.0 2020/5/11
 */
public class DisableSolidViewPager extends ViewPager {

    private boolean isCanScroll = false;

    public DisableSolidViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DisableSolidViewPager(Context context) {
        super(context);
    }

    public void setCanScroll(boolean isCanScroll){
        this.isCanScroll = isCanScroll;
    }
    @Override
    public boolean onTouchEvent(MotionEvent arg0) {
        if(isCanScroll){
            return super.onTouchEvent(arg0);
        }else{
            return false;
        }
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent arg0) {
        if(isCanScroll){
            return super.onInterceptTouchEvent(arg0);
        }else{
            return false;
        }
    }

    @Override
    public void setCurrentItem(int item, boolean smoothScroll) {
        super.setCurrentItem(item, smoothScroll);
    }

    @Override
    public void setCurrentItem(int item) {
        super.setCurrentItem(item, false);
    }
}
