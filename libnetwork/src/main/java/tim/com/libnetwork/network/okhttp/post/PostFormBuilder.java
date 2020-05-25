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
public class PostFormBuilder extends RequestBuilder {
    private List<FileInput> files = new ArrayList<>();
    private String body;
    private MediaType mime;

    @Override
    public PostFormBuilder url(String url) {
        this.url = url;
        return this;
    }


    @Override
    public RequestCall build() {
        addHeader("Accept-Language", "zh-CN,zh");
        PostFormRequest postFormRequest = new PostFormRequest(url, params, headers, files);
        RequestCall call = postFormRequest.build();
        return call;
    }

    @Override
    public RequestBuilder addParams(String key, String value) {
        if (this.params == null) params = new HashMap<>();
        params.put(key, value);
        return this;
    }

    public PostFormBuilder addFile(String name, String filename, File file) {
        files.add(new FileInput(name, filename, file));
        return this;
    }

    public PostFormBuilder addFile(String name, List<File> MultipartFile) {
        for (File file : MultipartFile) {
            files.add(new FileInput(name, file.getName(), file));
        }
        return this;
    }

    @Override
    public RequestBuilder addHeader(String key, String val) {
        if (this.headers == null) headers = new HashMap<>();
        headers.put(key, val);
        return this;
    }

    public static class FileInput {
        public String key;
        public String filename;
        public File file;

        public FileInput(String name, String filename, File file) {
            this.key = name;
            this.filename = filename;
            this.file = file;
        }
    }
}
