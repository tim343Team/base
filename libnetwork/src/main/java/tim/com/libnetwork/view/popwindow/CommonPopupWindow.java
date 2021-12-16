package tim.com.libnetwork.view.popwindow;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.PopupWindow;

/**
 * 通用popwindows
 *
 * eg:
 *       CommonPopupWindow commonPopupWindow= new CommonPopupWindow(getContext(), R.layout.pop_add, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT) {
 *             @Override
 *             protected void initView() {
 *                 Button button = (Button) contentView.findViewById(R.id.button1);
 *                 button.setOnClickListener(new View.OnClickListener() {
 *                     @Override
 *                     public void onClick(View view) {
 *                         Toast.makeText(getContext(), "button is pressed",
 *                                 Toast.LENGTH_SHORT).show();
 *                     }
 *                 });
 *             }
 *
 *             @Override
 *             protected void initEvent() {
 *             }
 *         };
 *         commonPopupWindow.showBashOfAnchor(imgAdd,new LayoutGravity(LayoutGravity.TO_LEFT),0,0);
 *
 * @author weiqiliu
 * @version 1.0 2020/5/11
 */
public abstract class CommonPopupWindow {
    protected Context context;
    protected View contentView;
    protected PopupWindow mInstance;

    public CommonPopupWindow(Context c, int layoutRes, int w, int h) {
        context=c;
        contentView= LayoutInflater.from(c).inflate(layoutRes, null, false);
        initView();
        initEvent();
        mInstance=new PopupWindow(contentView, w, h, true);
        initWindow();
    }

    public View getContentView() { return contentView; }
    public PopupWindow getPopupWindow() { return mInstance; }

    protected abstract void initView();
    protected abstract void initEvent();

    protected void initWindow() {
        mInstance.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        mInstance.setOutsideTouchable(true);
        mInstance.setTouchable(true);
    }

    public void showBashOfAnchor(View anchor, LayoutGravity layoutGravity, int xmerge, int ymerge) {
        int[] offset=layoutGravity.getOffset(anchor, mInstance);
        mInstance.showAsDropDown(anchor, offset[0]+xmerge, offset[1]+ymerge);
    }

    public void showAsDropDown(View anchor, int xoff, int yoff) {
        mInstance.showAsDropDown(anchor, xoff, yoff);
    }

    public void showAtLocation(View parent, int gravity, int x, int y) {
        mInstance.showAtLocation(parent, gravity, x, y);
    }

    public void dismiss(){
        mInstance.dismiss();
    }
}
