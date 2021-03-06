package tim.com.libnetwork.network.okhttp.get;

import android.net.Uri;
import android.util.Log;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import tim.com.libnetwork.network.okhttp.RequestBuilder;
import tim.com.libnetwork.network.okhttp.RequestCall;

/**
 * ${description}
 *
 * @author weiqiliu
 * @version 1.0 2020/4/13
 */
public class GetBuilder extends RequestBuilder {

    @Override
    public RequestCall build() {
        return new GetRequest(url, params, headers).build();
    }

    private String appendParams(String url, Map<String, String> params) {
        if (url == null || params == null || params.isEmpty())
        {
            return url;
        }
        Uri.Builder builder = Uri.parse(url).buildUpon();
        Set<String> keys = params.keySet();
        Iterator<String> iterator = keys.iterator();
        while (iterator.hasNext())
        {
            String key = iterator.next();
            builder.appendQueryParameter(key, params.get(key));
        }
        return builder.build().toString();
    }

    @Override
    public GetBuilder url(String url) {
        this.url = url;
        return this;
    }


    @Override
    public GetBuilder addParams(String key, String value) {
        if (this.params == null) params = new HashMap<>();
        params.put(key, value);
        return this;
    }

    @Override
    public GetBuilder addHeader(String key, String value) {
        if (this.headers == null) headers = new HashMap<>();
        headers.put(key, value);
        return this;
    }
}
