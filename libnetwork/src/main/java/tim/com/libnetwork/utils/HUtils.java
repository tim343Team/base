package tim.com.libnetwork.utils;

import android.os.Handler;
import android.os.Looper;

public class HUtils {
    private static final Handler handler = new Handler(Looper.getMainLooper());

    public static void runOnUi(Runnable runnable) {
        if (Thread.currentThread() != Looper.getMainLooper().getThread()) {
            handler.post(runnable);
        } else {
            runnable.run();
        }
    }

    public static void runCatch(Runnable runnable) {
        try {
            runnable.run();
        } catch (Exception ignored) {
        }
    }
}
