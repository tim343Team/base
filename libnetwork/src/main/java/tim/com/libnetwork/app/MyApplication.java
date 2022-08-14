package tim.com.libnetwork.app;

import android.app.Application;
import android.content.Context;

/**
 * ${description}
 *
 * @author weiqiliu
 * @version 1.0 2020/4/13
 */
public class MyApplication extends Application {
    public static Context context;

    @Override
    public void onCreate(){
        super.onCreate();
        context=getApplicationContext();
    }
}
