package tim.com.libnetwork.view.signview;

import android.content.Context;
import android.graphics.Color;
import android.widget.ImageView;

/**
 * ${description}
 *
 * @author weiqiliu
 * @version 1.0 2020/6/8
 */
public abstract class CoverAdapter<T> {

    public abstract void onDisplayImage(Context context, ImageView imageView, T t);

    public ImageView generateImageView(Context context) {
        //可以在这里更改Imageview的类型
        CircleImageView circleImageView = new CircleImageView(context);
//        ImageView imageView = new ImageView(context);
        circleImageView.setBorderColor(Color.parseColor("#dcdcdc"));
        circleImageView.setBorderWidth(4);
        return circleImageView;
    }
}
