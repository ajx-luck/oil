package com.breast.oil.domain;

import com.breast.oil.utils.TimeUtils;

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
    @Column(nullable = true)
    private String displayTime;
    @Column(nullable = false)
    private String ip;
    @Column(nullable = false)
    private String wechatId;
    @Column(nullable = true)
    private String keyWord;
    @Column(nullable = true)
    private String eKeywordid;
    @Column(nullable = true)
    private String urlRefer;
    @Column(nullable = true)
    private String provice;
    /**
     * 百度入口url
     */
    @Column(nullable = true)
    private String strartUrl;
    /**
     * 定位的位置
     */
    @Column(nullable = true)
    private String city;

    public HtmlInfo() {

    }

    public HtmlInfo(String urlPath, Long createTime, String ip, String wechatId, String keyWord, String e_keywordid, String city, String provice, String urlRefer, String strartUrl) {
        this.urlPath = urlPath;
        this.createTime = createTime;
        this.ip = ip;
        this.wechatId = wechatId;
        this.keyWord = keyWord;
        this.eKeywordid = e_keywordid;
        this.displayTime = TimeUtils.timesToDate(createTime);
        this.city = city;
        this.provice = provice;
        this.urlRefer = urlRefer;
        this.strartUrl = strartUrl;
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

    public String getDisplayTime() {
        return displayTime;
    }

    public void setDisplayTime(String displayTime) {
        this.displayTime = displayTime;
    }


}
