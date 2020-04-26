package tim.com.libnetwork.utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Date;

/**
 * ${description}
 *
 * @author weiqiliu
 * @version 1.0 2020/4/13
 */
public class WonderfulStringUtils {
    /**
     * 高精度小数转换为字符串，避免出现科学计数法
     */
    public static String getLongFloatString(String value, int length) {
        String format4 = new DecimalFormat("#0.00000000").format(value);
        BigDecimal bigDecimal = new BigDecimal(format4);
        return bigDecimal.setScale(length, BigDecimal.ROUND_DOWN).stripTrailingZeros().toPlainString();
    }

    public static String getLongFloatString(double value, int length) {
        String format4 = new DecimalFormat("#0.00000000").format(value);
        BigDecimal bigDecimal = new BigDecimal(format4);
        return bigDecimal.setScale(length, BigDecimal.ROUND_DOWN).stripTrailingZeros().toPlainString();
    }

    // date要转换的date类型的时间
    public static long dateToLong(Date date) {
        return date.getTime();
    }

    /**
     * 判断文本是否为空为null
     */
    public static boolean isEmpty(String... strs) {
        for (String str : strs) {
            if (str == null || "".equals(str) || "null".equals(str.toLowerCase())) return true;
        }
        return false;
    }
}
