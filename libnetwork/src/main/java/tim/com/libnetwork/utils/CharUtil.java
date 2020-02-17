package tim.com.libnetwork.utils;

import java.util.regex.Pattern;

/**
 * ${description}
 *
 * @author weiqiliu
 * @version 1.0 2020/2/11
 */
public class CharUtil {
    public static class Patterns {
        public static Pattern MOBILE = Pattern.compile("^1(3|5|7|8)[0-9]{9}$");
        public static Pattern NUMBER = Pattern.compile("^1[0-9]{10}$");
        public static Pattern GRASS = Pattern.compile("[0-9|a-z|A-Z|:|_|(|)| |\\\\|/|\\||\\-|*|#]{4,20}");
    }

}
