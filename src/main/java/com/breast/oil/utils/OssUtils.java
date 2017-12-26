package com.breast.oil.utils;

import com.aliyun.oss.common.auth.ServiceSignature;
import org.apache.commons.codec.binary.Base64;

/**
 * Created by B04e on 2017/12/26.
 */
public class OssUtils {
    static final String POLICY="{\"expiration\":\"2115-01-27T10:56:19Z\",\"conditions\":[[\"content-length-range\", 0, 1048576]]}";
    private static Base64 mBase64 = new Base64();


    public static String getBase64Policy(){
        return mBase64.encodeToString(POLICY.getBytes());
    }

    public static String getSignPolicy(String key, String policy){
        return ServiceSignature.create().computeSignature(key,policy);
    }
}
