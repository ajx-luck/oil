package com.breast.oil.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Comparator;

/**
 * Created by B04e on 2017/11/27.
 */
@Entity
public class WebInfo {
    @Id
    @GeneratedValue
    private Long id;
    @Column(nullable = false)
    private String urlPath;
    @Column(nullable = false)
    private Long createTime;
    @Column(nullable = false)
    private Long price;
    @Column(nullable = false)
    private String ip;
    @Column(nullable = false)
    private String wechatId;
    @Column(nullable = true)
    private String keyWord;
    @Column(nullable = true)
    private String creative;
    @Column(nullable = true)
    private String keywordid;
    @Column(nullable = false)
    private String refer;

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

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Long getId() {
        return id;
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

    public String getRefer() {
        return refer;
    }

    public void setRefer(String refer) {
        this.refer = refer;
    }
}
