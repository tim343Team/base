package com.hula.myapplication.app.net;

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Request
import okhttp3.Response
import okio.Timeout
import tim.com.libnetwork.network.okhttp.call.JHCall
import tim.com.libnetwork.utils.HUtils
import java.io.IOException
import java.lang.ref.WeakReference
import java.util.concurrent.Executor

/**
 * ${description}
 *
 * @author Picasso
 * @version 1.0 2022/9/03
 */
class RealHCall(val call: Call) : JHCall, LifecycleEventObserver {

    private var reference: WeakReference<Lifecycle>? = null


    override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
        if (event == Lifecycle.Event.ON_DESTROY) {
            cancel()
        }
    }

    override fun bindLifecycle(lifecycleOwner: LifecycleOwner): RealHCall {
        HUtils.runOnUi {
            val lifecycle = lifecycleOwner.lifecycle
            reference = WeakReference(lifecycle)
            lifecycle.addObserver(this)
        }
        return this
    }

    override fun clone(): Call {
        return call.clone()
    }

    override fun execute(): Response {
        val response = call.execute()
        release()
        return response
    }

    private fun release() {
        HUtils.runOnUi {
            //removeObserver必须放在Main线程
            reference?.get()?.removeObserver(this)
            reference = null
        }
    }

    override fun enqueue(responseCallback: Callback) {
        call.enqueue(object : Callback {
            override fun onResponse(call: Call, response: Response) {
                responseCallback.onResponse(call, response)
                release()

            }

            override fun onFailure(call: Call, e: IOException) {
                responseCallback.onFailure(call, e)
                release()
            }

        })
    }

    override fun isExecuted(): Boolean {
        return call.isExecuted()
    }

    override fun cancel() {
        call.cancel()
        release()
    }

    override fun isCanceled(): Boolean {
        return call.isCanceled()
    }

    override fun request(): Request {
        return call.request()
    }

    override fun timeout(): Timeout {
        return call.timeout()
    }

    override fun callbackExecutor(mExecutor: Executor): JHCall {
        //can not imp
        return this
    }

}