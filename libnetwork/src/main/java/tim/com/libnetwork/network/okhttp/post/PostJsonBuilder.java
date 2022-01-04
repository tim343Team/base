package tim.com.libnetwork.network.okhttp.post;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import okhttp3.MediaType;
import tim.com.libnetwork.network.okhttp.RequestBuilder;
import tim.com.libnetwork.network.okhttp.RequestCall;

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
