package tim.com.libnetwork.network.okhttp.call;

import okhttp3.Call;

public interface JHCallFactory {
    JHCall factory(Call call);
}
