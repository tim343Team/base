//package tim.com.libnetwork.network.okhttp.call;
//
//import androidx.lifecycle.Lifecycle
//import androidx.lifecycle.LifecycleEventObserver
//import androidx.lifecycle.LifecycleOwner
//import com.hongsong.live.lite.utils.Utils
//import okhttp3.Request
//import okio.Timeout
//import retrofit2.Call
//import retrofit2.Callback
//import retrofit2.Response
//import java.lang.ref.WeakReference
//import java.util.concurrent.Executor
//
//class RealHCall<T>(val call: Call<T>) : HCall<T>, LifecycleEventObserver {
//    companion object {
//        val mainCallBackExecutor by lazy {
//            Executor {
//                Utils.runOnUi(it)
//            }
//        }
//    }
//
//    private var reference: WeakReference<Lifecycle>? = null
//    //默认callBack是在主线程执行
//    private var callBackExecutor = mainCallBackExecutor
//
//    override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
//        if (event == Lifecycle.Event.ON_DESTROY) {
//            cancel()
//        }
//    }
//
//    override fun bindLifecycle(lifecycleOwner: LifecycleOwner): RealHCall<T> {
//        Utils.runOnUi {
//            val lifecycle = lifecycleOwner.lifecycle
//            reference = WeakReference(lifecycle)
//            lifecycle.addObserver(this)
//        }
//        return this
//    }
//
//    override fun clone(): Call<T> {
//        return call.clone()
//    }
//
//    override fun execute(): Response<T> {
//        val response = call.execute()
//        release()
//        return response
//    }
//
//    private fun release() {
//        Utils.runOnUi {
//            //removeObserver必须放在Main线程
//            reference?.get()?.removeObserver(this)
//            reference = null
//        }
//    }
//
//    override fun enqueue(callback: Callback<T>) {
//        call.enqueue(object : Callback<T> {
//            override fun onResponse(call: Call<T>, response: Response<T>) {
//                callBackExecutor.execute {
//                    callback.onResponse(call, response)
//                    release()
//                }
//
//            }
//
//            override fun onFailure(call: Call<T>, t: Throwable) {
//                callBackExecutor.execute {
//                    callback.onFailure(call, t)
//                    release()
//                }
//            }
//
//        })
//    }
//
//    override fun isExecuted(): Boolean {
//        return call.isExecuted
//    }
//
//    override fun cancel() {
//        call.cancel()
//        release()
//    }
//
//    override fun isCanceled(): Boolean {
//        return call.isCanceled
//    }
//
//    override fun request(): Request {
//        return call.request()
//    }
//
//    override fun timeout(): Timeout {
//        return call.timeout()
//    }
//
//    override fun callbackExecutor(mExecutor: Executor): HCall<T> {
//        callBackExecutor = mExecutor
//        return this
//    }
//
//
//}