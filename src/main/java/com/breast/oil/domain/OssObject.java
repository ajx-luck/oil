package com.breast.oil.domain;

/**
 * Created by B04e on 2017/12/26.
 */
public class OssObject {
    private String ossId;
    private String policy;
    private String signature;
    /**
     * 文件名
     */
    private String key;

    public OssObject(){

    }

    public OssObject(String ossId, String policy, String signature, String key) {
        this.ossId = ossId;
        this.policy = policy;
        this.signature = signature;
        this.key = key;
    }

    public String getOssId() {
        return ossId;
    }

    public void setOssId(String ossId) {
        this.ossId = ossId;
    }

    public String getPolicy() {
        return policy;
    }

    public void setPolicy(String policy) {
        this.policy = policy;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }


}
