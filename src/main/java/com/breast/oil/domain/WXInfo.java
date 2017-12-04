package com.breast.oil.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by B04e on 2017/12/4.
 */
@Entity
public class WXInfo {
    @Id
    @GeneratedValue
    private Long id;
    @Column(nullable = false)
    private String wechatId;
    @Column(nullable = false)
    private String ip;
    @Column(nullable = false)
    private String urlPath;
    @Column(nullable = false)
    private String keyWord;

    public WXInfo(String wechatId, String ip, String urlPath, String keyWord) {
        this.wechatId = wechatId;
        this.ip = ip;
        this.urlPath = urlPath;
        this.keyWord = keyWord;
    }

    public String getWechatId() {
        return wechatId;
    }

    public void setWechatId(String wechatId) {
        this.wechatId = wechatId;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getUrlPath() {
        return urlPath;
    }

    public void setUrlPath(String urlPath) {
        this.urlPath = urlPath;
    }

    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }

    public Long getId() {
        return id;
    }
}
