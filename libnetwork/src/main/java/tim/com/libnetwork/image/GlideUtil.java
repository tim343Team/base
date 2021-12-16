package tim.com.libnetwork.image;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

import tim.com.libnetwork.image.glide.GlideRoundTransform;

public class GlideUtil {

    public GlideUtil() {
    }

    public void loadSimple(Context context, String url, ImageView image) {
        RequestOptions options = new RequestOptions()
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.NONE);
        Glide.with(context).load(url).apply(options).into(image);
    }

    public void loadRound(int width, int height,Context context, String url, ImageView image) {
        RequestOptions options = new RequestOptions()
                .override(width, height)
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.NONE) //缓存
                .transform(new GlideRoundTransform(10)); //圆角
        Glide.with(context).load(url).apply(options).into(image);
    }

    public RequestOptions getRequestOptions(){
        return new RequestOptions();
    }

    public RequestManager getGlide(Context context){
        return Glide.with(context);
    }
}
