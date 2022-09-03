package tim.com.libnetwork.network.okhttp.call;

import com.hula.myapplication.app.net.RealHCall;

import okhttp3.Call;

public class RealCallFactory implements JHCallFactory {
    @Override
    public JHCall factory(Call call) {
        return new RealHCall(call);
    }
}
