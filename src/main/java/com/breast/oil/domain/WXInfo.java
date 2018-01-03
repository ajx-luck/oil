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
    @Column(nullable = true)
    public Long createTime;
    //点击的来源的
    @Column(nullable = true)
    public String type;
    //创意
    @Column(nullable = false)
    private String creative;
    @Column(nullable = false)
    private String keywordid;

    public WXInfo(String wechatId, String ip, String urlPath, String keyWord,String creative,String keywordid, String type,Long createTime) {
        this.wechatId = wechatId;
        this.ip = ip;
        this.urlPath = urlPath;
        this.keyWord = keyWord;
        this.type = type;
        this.createTime = createTime;
        this.creative = creative;
        this.keywordid = keywordid;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
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

    public String getCreative() {
        return creative;
    }

    public void setCreative(String creative) {
        this.creative = creative;
    }

    public String getKeywordid() {
        return keywordid;
    }

    public void setKeywordid(String keywordid) {
        this.keywordid = keywordid;
    }
}
