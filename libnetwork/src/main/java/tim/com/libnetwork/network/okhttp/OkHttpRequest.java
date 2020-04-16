package tim.com.libnetwork.network.okhttp;

import android.text.TextUtils;

import java.util.Map;

import okhttp3.Headers;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * ${description}
 *
 * @author weiqiliu
 * @version 1.0 2020/4/13
 */
public abstract class OkHttpRequest {
    protected String url;
    protected Map<String, String> params;
    protected Map<String, String> headers;
    protected Request.Builder builder = new Request.Builder();

    protected OkHttpRequest(String url, Map<String, String> params, Map<String, String> headers) {
        this.url = url;
        this.params = params;
        this.headers = headers;
        if (url == null) {
            try {
                throw new Exception("url can not be null!");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    protected abstract RequestBody buildRequestBody();

    protected abstract Request buildRequest(Request.Builder builder, RequestBody requestBody);

    public RequestCall build() {
        return new RequestCall(this);
    }

    public Request generateRequest(Callback callback) {
        RequestBody requestBody = wrapRequestBody(buildRequestBody(), callback);
        prepareBuilder();
        return buildRequest(builder, requestBody);
    }

    protected RequestBody wrapRequestBody(RequestBody requestBody, final Callback callback) {
        return requestBody;
    }

    private void prepareBuilder() {
        builder.url(url);
        appendHeaders();
    }

    protected void appendHeaders() {
        Headers.Builder headerBuilder = new Headers.Builder();
        if (headers == null || headers.isEmpty()) return;

        for (String key : headers.keySet()) {
            String value=headers.get(key);
            if(!TextUtils.isEmpty(value)) {
                headerBuilder.add(key, headers.get(key));
            }
        }
        builder.headers(headerBuilder.build());
    }
}
