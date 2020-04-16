package tim.com.libnetwork.network.okhttp;

import java.io.IOException;

import okhttp3.Request;
import okhttp3.Response;
import tim.com.libnetwork.utils.WonderfulLogUtils;

/**
 * ${description}
 *
 * @author weiqiliu
 * @version 1.0 2020/4/13
 */
public abstract class StringCallBack extends Callback<String> {
    @Override
    public String parseNetworkResponse(Response response) throws IOException {
        return response.body().string();
    }

    @Override
    public void onError(Request request, Exception e) {
        WonderfulLogUtils.logi("miao", e.toString().length() + "-666-");
    }

    @Override
    public void onResponse(String response) {

    }
}
