package tim.com.libnetwork.network.okhttp;

import java.util.HashMap;
import java.util.Map;

/**
 * 设置请求头
 *
 * @author weiqiliu
 * @version 1.0 2022/8/13
 */
public class RemoteConfig {
    private static Map<String,String> header=new HashMap<>();

    public static Map<String, String> getHeader() {
        return header;
    }

    public static void addHeader(String headerName,String headerValue){
        header.put(headerName,headerValue);
    }
}
