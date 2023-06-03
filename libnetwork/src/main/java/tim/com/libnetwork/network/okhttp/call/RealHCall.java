package tim.com.libnetwork.network.okhttp.call;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleEventObserver;
import androidx.lifecycle.LifecycleOwner;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Request;
import okhttp3.Response;
import okio.Timeout;
import tim.com.libnetwork.network.okhttp.call.JHCall;
import tim.com.libnetwork.utils.HUtils;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.concurrent.Executor;

/**
 * ${description}
 */
public class RealHCall implements JHCall, LifecycleEventObserver {

    private Call call;
    private WeakReference<Lifecycle> reference;

    public RealHCall(Call call) {
        this.call = call;
    }

    @Override
    public void onStateChanged(LifecycleOwner source, Lifecycle.Event event) {
        if (event == Lifecycle.Event.ON_DESTROY) {
            cancel();
        }
    }

    @Override
    public RealHCall bindLifecycle(LifecycleOwner lifecycleOwner) {
        HUtils.runOnUi(() -> {
            Lifecycle lifecycle = lifecycleOwner.getLifecycle();
            reference = new WeakReference<>(lifecycle);
            lifecycle.addObserver(this);
        });
        return this;
    }

    @Override
    public Call clone() {
        return call.clone();
    }

    @Override
    public Response execute() throws IOException {
        Response response = call.execute();
        release();
        return response;
    }

    private void release() {
        HUtils.runOnUi(() -> {
            //removeObserver必须放在Main线程
            if (reference != null) {
                Lifecycle lifecycle = reference.get();
                if (lifecycle != null) {
                    lifecycle.removeObserver(this);
                }
                reference = null;
            }
        });
    }

    @Override
    public void enqueue(Callback responseCallback) {
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                responseCallback.onResponse(call, response);
                release();
            }

            @Override
            public void onFailure(Call call, IOException e) {
                responseCallback.onFailure(call, e);
                release();
            }
        });
    }

    @Override
    public boolean isExecuted() {
        return call.isExecuted();
    }

    @Override
    public void cancel() {
        call.cancel();
        release();
    }

    @Override
    public boolean isCanceled() {
        return call.isCanceled();
    }

    @Override
    public Request request() {
        return call.request();
    }

    @Override
    public Timeout timeout() {
        return call.timeout();
    }

    @Override
    public JHCall callbackExecutor(Executor mExecutor) {
        // can not imp
        return this;
    }
}
