package tim.com.libnetwork.network.okhttp;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import tim.com.libnetwork.network.okhttp.call.JHCall;
import tim.com.libnetwork.network.okhttp.call.JHCallFactory;

/**
 * ${description}
 *
 * @author weiqiliu
 * @version 1.0 2020/4/13
 */
public class RequestCall {
    private OkHttpRequest okHttpRequest;
    private OkHttpClient client = WonderfulOkhttpUtils.getInstance().getOkHttpClient();

    public RequestCall(OkHttpRequest okHttpRequest) {
        this.okHttpRequest = okHttpRequest;
    }

    public void execute(Callback callback) {
        if (callback != null) {
            callback.onBefore();
        }
        WonderfulOkhttpUtils.getInstance().execute(this, callback);
    }


    private Request generateRequest(Callback callback) {
        return okHttpRequest.generateRequest(callback);
    }

    public RequestCall newClient(OkHttpClient client){
        this.client = client;
        return this;
    }

    public JHCall getCall() {
        JHCallFactory jhCallFactory = WonderfulOkhttpUtils.getInstance().getJhCallFactory();
        Call realCall = client.newCall(generateRequest(null));
        return jhCallFactory.factory(realCall);
    }
}
