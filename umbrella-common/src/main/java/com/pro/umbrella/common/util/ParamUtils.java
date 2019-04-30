package com.pro.umbrella.common.util;

import com.google.common.base.Splitter;
import com.google.common.base.Strings;

import java.util.List;
import java.util.stream.Collectors;

public class ParamUtils {
    public static final Splitter SPLITTER = Splitter.on(",").trimResults().omitEmptyStrings();

    public static Double toDouble(String s) {
        if (!Strings.isNullOrEmpty(s)) {
            return Double.parseDouble(s);
        } else {
            return null;
        }
    }

    public static List<String> toList(String s) {
        if (!Strings.isNullOrEmpty(s)) {
            return SPLITTER.splitToList(s);
        } else {
            return null;
        }
    }

    public static List<Integer> toIntList(String s) {
        if (!Strings.isNullOrEmpty(s)) {
            List<String> strings = SPLITTER.splitToList(s);
            return strings.stream().map(Integer::parseInt).collect(Collectors.toList());
        } else {
            return null;
        }
    }

    public static Long toLong(String s) {
        if (!Strings.isNullOrEmpty(s)) {
            return Long.parseLong(s);
        } else {
            return null;
        }
    }

    public static Integer toInt(String s) {
        if (!Strings.isNullOrEmpty(s)) {
            return Integer.parseInt(s);
        } else {
            return null;
        }
    }
}
