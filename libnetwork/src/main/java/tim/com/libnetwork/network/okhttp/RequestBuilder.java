package tim.com.libnetwork.network.okhttp;

import java.util.Map;

/**
 * ${description}
 *
 * @author weiqiliu
 * @version 1.0 2020/4/13
 */
public abstract class RequestBuilder {
    protected String url;
    protected Map<String, String> params;
    protected Map<String, String> headers;

    public abstract RequestBuilder url(String url);

    public abstract RequestCall build();

    public abstract RequestBuilder addParams(String key, String val);

    public abstract RequestBuilder addHeader(String key, String val);
}
