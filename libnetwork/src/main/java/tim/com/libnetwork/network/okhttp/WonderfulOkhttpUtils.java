package tim.com.libnetwork.network.okhttp;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import androidx.annotation.NonNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import tim.com.libnetwork.network.okhttp.call.JHCall;
import tim.com.libnetwork.network.okhttp.call.JHCallFactory;
import tim.com.libnetwork.network.okhttp.call.RealCallFactory;
import tim.com.libnetwork.network.okhttp.get.GetBuilder;
import tim.com.libnetwork.network.okhttp.post.PostFormBuilder;
import tim.com.libnetwork.network.okhttp.post.PostJsonBuilder;
import tim.com.libnetwork.utils.WonderfulLogUtils;
import tim.com.libnetwork.utils.WonderfulStringUtils;

/**
 * 网络请求封装
 * <p>
 * eg:WonderfulOkhttpUtils.post().url("url").addHeader("x-auth-token", token)
 * .addParams("symbol", symbol).build().execute(new StringCallback(){
 *
 * @author weiqiliu
 * @version 1.0 2020/4/13
 * @Override public void onError(Request request, Exception e) {
 * super.onError(request, e);
 * }
 * @Override public void onResponse(String response) {
 * }
 * })
 */
public class WonderfulOkhttpUtils {
    private static WonderfulOkhttpUtils mInstance;
    private OkHttpClient mOkHttpClient;
    private JHCallFactory jhCallFactory;
    private Handler handler;

    public WonderfulOkhttpUtils() {
        handler = new Handler(Looper.getMainLooper());
    }

    public JHCallFactory getJhCallFactory() {
        return jhCallFactory;
    }

    //初始化默认的OkHttpClient和JHCallFactory，建议在项目里的application里初始化
    public void deafultConfig() {
        this.mOkHttpClient = new OkHttpClient.Builder()
                .connectTimeout(50, TimeUnit.SECONDS).readTimeout(50, TimeUnit.SECONDS)
                .cookieJar(new CookieJar() {
                    private final HashMap<String, List<Cookie>> cookieStore = new HashMap<>();

                    @Override
                    public void saveFromResponse(@NonNull HttpUrl httpUrl, @NonNull List<Cookie> list) {
                        cookieStore.put(httpUrl.host(), list);
                    }

                    @NonNull
                    @Override
                    public List<Cookie> loadForRequest(@NonNull HttpUrl httpUrl) {
                        List<Cookie> cookies = cookieStore.get(httpUrl.host());
                        return cookies != null ? cookies : new ArrayList<>();
                    }
                })
                .build();
        this.jhCallFactory = new RealCallFactory();

    }

    //初始化自定义的OkHttpClient和JHCallFactory，建议在项目里的application里初始化
    public void config(OkHttpClient okHttpClient, JHCallFactory factory) {
        this.mOkHttpClient = okHttpClient;
        this.jhCallFactory = factory;
    }

    public static WonderfulOkhttpUtils getInstance() {
        if (mInstance == null) {
            synchronized (WonderfulOkhttpUtils.class) {
                if (mInstance == null) {
                    mInstance = new WonderfulOkhttpUtils();
                }
            }
        }
        return mInstance;
    }

    public static PostFormBuilder post() {
        return new PostFormBuilder();
    }

    public static GetBuilder get() {
        return new GetBuilder();
    }

    public static PostJsonBuilder postJson() {
        return new PostJsonBuilder();
    }

    public OkHttpClient getOkHttpClient() {
        return mOkHttpClient;
    }

    public void execute(RequestCall requestCall, Callback callback) {
        if (callback == null) {
            Log.e("callback is null", "!!!!");
            callback = Callback.CALLBACK_DEFAULT;
        }
        JHCall call = requestCall.getCall();
        String name = call.request().url().toString();
        final Callback finalCallback = callback;
        call.enqueue(new okhttp3.Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                sendFailResultCallback(call.request(), e, finalCallback);
            }

            @Override
            public void onResponse(Call call, okhttp3.Response response) throws IOException {
                if (response.code() >= 400 && response.code() <= 599) {
                    if (response.code() == 404) {
                        WonderfulLogUtils.logi("miao", name);
                    }
                    try {
                        sendFailResultCallback(call.request(), new RuntimeException(response.body().string()), finalCallback);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    return;
                }
                try {
                    Object o = finalCallback.parseNetworkResponse(response);
                    sendSuccessResultCallback(o, finalCallback);
                } catch (IOException e) {
                    sendFailResultCallback(response.request(), e, finalCallback);
                }
            }
        });
    }

    public void sendFailResultCallback(final Request request, final Exception e, final Callback callback) {
        if (callback == null) return;
        handler.post(new Runnable() {
            @Override
            public void run() {
                callback.onError(request, e);
                callback.onAfter();
            }
        });
    }

    public void sendSuccessResultCallback(final Object object, final Callback callback) {
        if (callback == null) return;
        handler.post(new Runnable() {
            @Override
            public void run() {
                callback.onResponse(object);
                callback.onAfter();
            }
        });
    }

    public Handler getHandler() {
        return handler;
    }
}
