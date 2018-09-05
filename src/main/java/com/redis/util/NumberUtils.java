package com.redis.util;

/**
 * @author kun.wang
 * @date 2018/9/4
 */
public class NumberUtils {
    private NumberUtils() {

    }

    public Integer defaultInteger(final Integer value) {
        if (value == null) {
            return 0;
        }
        return value;
    }

    public Integer defaultIfNull(final Integer value, final Integer defaultValue) {
        if (value != null) {
            return value;
        }
        return defaultInteger(defaultValue);
    }
}
