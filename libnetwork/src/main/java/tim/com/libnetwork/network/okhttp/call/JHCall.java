package tim.com.libnetwork.network.okhttp.call;

import androidx.lifecycle.LifecycleOwner;

import java.util.concurrent.Executor;

import okhttp3.Call;

/**
 * ${description}
 *
 * @author Picasso
 * @version 1.0 2022/9/03
 */
public interface JHCall extends Call {
    JHCall bindLifecycle(LifecycleOwner lifecycleOwner);

    JHCall callbackExecutor(Executor executor);
}
