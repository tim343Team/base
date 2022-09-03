package tim.com.libnetwork.network.okhttp;

import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Request;
import okhttp3.Response;

/**
 * ${description}
 *
 * @author weiqiliu
 * @version 1.0 2020/4/13
 */
public abstract class Callback<T>  {
    protected Gson gson = new Gson();

    public abstract T parseNetworkResponse(Response response) throws IOException;

    public void onBefore() {
    }


    public void onAfter() {
    }

    public void inProgress(float progress) {

    }

    public abstract void onError(Request request, Exception e);

    public abstract void onResponse(T response);

    public static Callback CALLBACK_DEFAULT = new Callback() {

        @Override
        public Object parseNetworkResponse(Response response) throws IOException {
            return null;
        }

        @Override
        public void onError(Request request, Exception e) {

        }

        @Override
        public void onResponse(Object response) {

        }
    };
}
