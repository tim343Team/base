package tim.com.libnetwork.network.okhttp.post;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.MediaType;
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
public class PostJsonBuilder extends RequestBuilder {
    private List<PostFormBuilder.FileInput> files = new ArrayList<>();
    private String body;
    private MediaType mime;

    @Override
    public PostJsonBuilder url(String url) {
        this.url = url;
        return this;
    }

    public PostJsonBuilder body(String body) {
        this.body = body;
        return this;
    }

    public PostJsonBuilder mime(MediaType mime) {
        this.mime = mime;
        return this;
    }

    @Override
    public RequestCall build() {
        Map<String,String> map= RemoteConfig.getHeader();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            addHeader(entry.getKey(), entry.getValue());
        }
        addHeader("language", SharedPreferencesUtils.getCurrentLanguages(MyApplication.context));
        return new PostJsonRequest(url, params, headers, body, mime).build();
    }

    @Override
    public PostJsonBuilder addParams(String key, String val) {
        if (this.params == null) params = new HashMap<>();
        params.put(key, val);
        return this;
    }

//    public PostFormBuilder addFile(String name, String filename, File file) {
//        files.add(new PostFormBuilder.FileInput(name, filename, file));
//        return this;
//    }

    @Override
    public PostJsonBuilder addHeader(String key, String val) {
        if (this.headers == null) headers = new HashMap<>();
        headers.put(key, val);
        return this;
    }

    @Override
    public RequestBuilder addFile(String name, String filename, File file) {
        return null;
    }
}
