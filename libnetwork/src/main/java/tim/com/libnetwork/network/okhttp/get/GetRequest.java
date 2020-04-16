package tim.com.libnetwork.network.okhttp.get;

import java.util.Map;

import okhttp3.Request;
import okhttp3.RequestBody;
import tim.com.libnetwork.network.okhttp.OkHttpRequest;

/**
 * ${description}
 *
 * @author weiqiliu
 * @version 1.0 2020/4/13
 */
public class GetRequest extends OkHttpRequest {

    public GetRequest(String url, Map<String, String> params, Map<String, String> headers) {
        super(url, params, headers);
    }

    @Override
    protected RequestBody buildRequestBody() {
        return null;
    }

    @Override
    protected Request buildRequest(Request.Builder builder, RequestBody requestBody) {
        return builder.get().build();
    }
}
