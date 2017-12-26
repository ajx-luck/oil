package com.breast.oil.domain;

/**
 * Created by B04e on 2017/12/26.
 */
public class OssObject {
    private String OSSAccessKeyId;
    private String policy;
    private String Signature;
    /**
     * 文件名
     */
    private String key;

    public OssObject(){

    }

    public OssObject(String ossId, String policy, String signature, String key) {
        this.OSSAccessKeyId = ossId;
        this.policy = policy;
        this.Signature = signature;
        this.key = key;
    }


    public String getPolicy() {
        return policy;
    }

    public void setPolicy(String policy) {
        this.policy = policy;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getOSSAccessKeyId() {
        return OSSAccessKeyId;
    }

    public void setOSSAccessKeyId(String OSSAccessKeyId) {
        this.OSSAccessKeyId = OSSAccessKeyId;
    }

    public String getSignature() {
        return Signature;
    }

    public void setSignature(String signature) {
        Signature = signature;
    }
}
