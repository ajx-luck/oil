package com.breast.oil.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by B04e on 2018/3/22.
 */
@Entity
public class HtmlInfo {
    @Id
    @GeneratedValue
    private Long id;
    @Column(nullable = false)
    private String urlPath;
    @Column(nullable = false)
    private Long createTime;
    @Column(nullable = false)
    private String ip;
    @Column(nullable = false)
    private String wechatId;
    @Column(nullable = true)
    private String keyWord;
    @Column(nullable = true)
    private String eKeywordid;

    public HtmlInfo(){

    }

    public HtmlInfo(String urlPath, Long createTime, String ip, String wechatId, String keyWord, String e_keywordid) {
        this.urlPath = urlPath;
        this.createTime = createTime;
        this.ip = ip;
        this.wechatId = wechatId;
        this.keyWord = keyWord;
        this.eKeywordid = e_keywordid;
    }

    public String getUrlPath() {
        return urlPath;
    }

    public void setUrlPath(String urlPath) {
        this.urlPath = urlPath;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getWechatId() {
        return wechatId;
    }

    public void setWechatId(String wechatId) {
        this.wechatId = wechatId;
    }

    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }

    public String geteKeywordid() {
        return eKeywordid;
    }

    public void seteKeywordid(String eKeywordid) {
        this.eKeywordid = eKeywordid;
    }
}
