package tim.com.libnetwork.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

/**
 * ${description}
 *
 * @author weiqiliu
 * @version 1.0 2020/2/20
 */
public class SharedPreferencesUtils {

//    public static void setParam(Context context, String key, Object object) {
//        setParam(context, FileName.PHOTO_SYN, key, object);
//    }
//
//    /**
//     * 保存数据的方法，我们需要拿到保存数据的具体类型，然后根据类型调用不同的保存方法
//     *
//     * @param context
//     * @param key
//     * @param object
//     */
//    public static void setParam(Context context, FileName fileName, String key, Object object) {
//        String type = object.getClass().getSimpleName();
//        SharedPreferences sp = context.getSharedPreferences(fileName.toString(), Context.MODE_PRIVATE);
//        SharedPreferences.Editor editor = sp.edit();
//        if ("String".equals(type)) {
//            editor.putString(key, (String) object);
//        } else if ("Integer".equals(type)) {
//            editor.putInt(key, (Integer) object);
//        } else if ("Boolean".equals(type)) {
//            editor.putBoolean(key, (Boolean) object);
//        } else if ("Float".equals(type)) {
//            editor.putFloat(key, (Float) object);
//        } else if ("Long".equals(type)) {
//            editor.putLong(key, (Long) object);
//        } else {
//            Gson gson = new Gson();
//            String json = gson.toJson(object);
//            editor.putString(key, json);
//        }
//        editor.apply();
//    }
}
