package tim.com.libnetwork.network.okhttp;

import okhttp3.Call;
import okhttp3.Request;

/**
 * ${description}
 *
 * @author weiqiliu
 * @version 1.0 2020/4/13
 */
public class RequestCall {
    private OkHttpRequest okHttpRequest;
    private Request request; //通过配置请求的 地址、http方法、请求头 等信息
    private Call call;

    public RequestCall(OkHttpRequest okHttpRequest) {
        this.okHttpRequest = okHttpRequest;
    }

    public void execute(Callback callback) {
        generateCall(callback);
        if (callback != null) {
            callback.onBefore(request);
        }
        WonderfulOkhttpUtils.getInstance().execute(this, callback);
    }

    private Call generateCall(Callback callback) {
        request = generateRequest(callback);
        call = WonderfulOkhttpUtils.getInstance().getOkHttpClient().newCall(request);
        return call;
    }

    private Request generateRequest(Callback callback) {
        return okHttpRequest.generateRequest(callback);
    }

    public Call getCall() {
        if(call == null){
            return WonderfulOkhttpUtils.getInstance().getOkHttpClient().newCall(request);
        } else {
            return call;
        }
    }
}
