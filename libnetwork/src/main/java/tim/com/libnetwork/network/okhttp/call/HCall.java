package tim.com.libnetwork.network.okhttp.call;

import androidx.lifecycle.LifecycleOwner;
import java.util.concurrent.Executor;
import retrofit2.Call;

public interface HCall<T> extends Call<T> {
    // 绑定生命周期
    HCall<T> bindLifecycle(LifecycleOwner lifecycleOwner);
    // 设置 callbackExecutor
    HCall<T> callbackExecutor(Executor mExecutor);
}
