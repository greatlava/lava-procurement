package com.hh.bidding.utils;

public class StringPathUtils {
    public static String cutToTheEndStr(String originalString) {
        if (originalString.endsWith(",")) {
            return originalString.substring(0, originalString.length() - 1);
        }
        return originalString;
    }

    public static String replaceHttpToNull(String originalString) {
        return originalString.replace("http://localhost:9610/static/", "");
    }

    public static String subStringLastString(String originalString) {
        // 找到最后一个斜杠的索引
        int lastSlashIndex = originalString.lastIndexOf("/");
        if (lastSlashIndex != -1) {
            // 删除最后一个斜杠及其之前的内容
            return originalString.substring(lastSlashIndex + 1);
        } else {
            return "No slash found in the original string.";
        }
    }
}
