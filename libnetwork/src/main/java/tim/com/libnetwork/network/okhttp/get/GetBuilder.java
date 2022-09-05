package tim.com.libnetwork.network.okhttp.get;

import android.net.Uri;
import android.util.Log;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import tim.com.libnetwork.app.MyApplication;
import tim.com.libnetwork.network.okhttp.RemoteConfig;
import tim.com.libnetwork.network.okhttp.RequestBuilder;
import tim.com.libnetwork.network.okhttp.RequestCall;
import tim.com.libnetwork.utils.SharedPreferencesUtils;

/**
 * ${description}
 *
 * @author weiqiliu
 * @version 1.0 2020/4/13
 */
public class GetBuilder extends RequestBuilder {

    @Override
    public RequestCall build() {
        Map<String, String> map = RemoteConfig.getHeader();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            addHeader(entry.getKey(), entry.getValue());
        }
        addHeader("language", SharedPreferencesUtils.getCurrentLanguages(MyApplication.context));
        //把params里的参数拼接到url里
        Uri.Builder builder = Uri.parse(url).buildUpon();
        if (params != null) {
            Set<String> keys = params.keySet();
            Iterator<String> iterator = keys.iterator();
            while (iterator.hasNext()) {
                String key = iterator.next();
                builder.appendQueryParameter(key, (params.get(key)));
            }
        }
        return new GetRequest(builder.build().toString(), params, headers).build();
    }

    private String appendParams(String url, Map<String, String> params) {
        if (url == null || params == null || params.isEmpty()) {
            return url;
        }
        Uri.Builder builder = Uri.parse(url).buildUpon();
        Set<String> keys = params.keySet();
        Iterator<String> iterator = keys.iterator();
        while (iterator.hasNext()) {
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

    @Override
    public RequestBuilder addFile(String name, String filename, File file) {
        return null;
    }
}
