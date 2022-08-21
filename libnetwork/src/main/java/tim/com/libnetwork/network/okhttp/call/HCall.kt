package tim.com.libnetwork.network.okhttp.call;

import androidx.lifecycle.LifecycleOwner
import retrofit2.Call
import java.util.concurrent.Executor

interface HCall<T> :Call<T> {
    //绑定生命周期
    fun bindLifecycle(lifecycleOwner: LifecycleOwner) :HCall<T>
    //设置callbackExecutor
    fun callbackExecutor(mExecutor: Executor):HCall<T>

}