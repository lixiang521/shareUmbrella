package com.pro.umbrella.api.utils;

public class TransformUtils {

    public static String vbat(String originVbat) {
        return String.format("%.2f", transformVabt(originVbat));
    }

    public static double transformVabt(String originVbat) {
        double vbat = Integer.parseInt(originVbat, 16);
        return vbat / 4096 * 6.6;
    }
}
