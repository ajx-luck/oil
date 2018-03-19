package com.breast.oil.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.DecimalFormat;

/**
 * Created by B04e on 2017/11/28.
 */
public class FormatUtils {

    public static String formatMoney(long money){
        return String.format("%d元%d角%d分",money/100,money%100/10,money%10);
    }

    public static String percentage(long numerator ,long denominator){
        double p = Double.parseDouble(numerator+"")*100/denominator;
        DecimalFormat df=new DecimalFormat("#.00");
        return df.format(p)+"%";
    }

    public static String decode(String str){
        String result = "";
        try {
            result = URLDecoder.decode(str,"utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result;
    }
}
