package com.breast.oil.utils;

/**
 * Created by B04e on 2017/11/28.
 */
public class FormatUtils {

    public static String formatMoney(long money){
        return String.format("%d元%d角%d分",money/100,money%100/10,money%10);
    }
}
