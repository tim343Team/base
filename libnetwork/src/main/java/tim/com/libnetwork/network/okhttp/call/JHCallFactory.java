package tim.com.libnetwork.network.okhttp.call;

import okhttp3.Call;

/**
 * ${description}
 *
 * @author Picasso
 * @version 1.0 2022/9/03
 */
public interface JHCallFactory {
    JHCall factory(Call call);
}
