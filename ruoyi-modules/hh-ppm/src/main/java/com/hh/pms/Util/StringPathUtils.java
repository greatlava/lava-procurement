package com.hh.pms.Util;

public class StringPathUtils {
    public static String cutToTheEndStr(String originalString) {
        if (originalString.endsWith(",")) {
            return originalString.substring(0, originalString.length() - 1);
        }
        return originalString;
    }
}
