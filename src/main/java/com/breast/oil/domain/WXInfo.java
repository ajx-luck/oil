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
    @Column(nullable = true)
    public Long createTime;
    //点击的来源的
    @Column(nullable = true)
    public String type;
    @Column(nullable = true)
    private String keyWord;
    @Column(nullable = true)
    private String eKeywordid;
    @Column(nullable = true)
    private String refer;
    /**
     * 广告被点击后替换为触发该创意的关键词匹配方式，精确匹配标识为1，高级短语匹配标识为2，广泛匹配标识为3。
     */
    @Column(nullable = true)
    private String eMatchtype;
    /**
     * 广告被点击后替换为所点击的创意ID，ID可在后续客户端上线的“转化URL解析”中匹配映射到本地的创意，并且整合数据展现。
     */
    @Column(nullable = true)
    private String eCreative;
    /**
     * 广告被点击后替换为所点击的创意展现位置和排名信息，标识方法为：位置+排名。有如下展现位置，分别用字母缩写标识为cl：pc左侧无底色，clg：pc左侧有底色，mt：无线上方，mb：无线下方，排名用具体数字标识。例如：">eAdposition=cl3代表该点击来自pc左侧无底色广告位第3名。
     */
    @Column(nullable = true)
    private String eAdposition;
    /**
     * 广告被点击后替换为所点击的创意展现在搜索结果页面的页码，为从1开始的整数，可与与展现排名配合，用于了解被点击创意在搜索结果中的整体排名情况。
     */
    @Column(nullable = true)
    private String ePagenum;

    public WXInfo(){

    }

    public WXInfo(String wechatId, String ip, String urlPath, Long createTime, String type, String keyWord, String e_keywordid, String refer, String e_matchtype, String e_creative, String e_adposition, String e_pagenum) {
        this.wechatId = wechatId;
        this.ip = ip;
        this.urlPath = urlPath;
        this.createTime = createTime;
        this.type = type;
        this.keyWord = keyWord;
        this.eKeywordid = e_keywordid;
        this.refer = refer;
        this.eMatchtype = e_matchtype;
        this.eCreative = e_creative;
        this.eAdposition = e_adposition;
        this.ePagenum = e_pagenum;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public String getRefer() {
        return refer;
    }

    public void setRefer(String refer) {
        this.refer = refer;
    }

    public String geteMatchtype() {
        return eMatchtype;
    }

    public void seteMatchtype(String eMatchtype) {
        this.eMatchtype = eMatchtype;
    }

    public String geteCreative() {
        return eCreative;
    }

    public void seteCreative(String eCreative) {
        this.eCreative = eCreative;
    }

    public String geteAdposition() {
        return eAdposition;
    }

    public void seteAdposition(String eAdposition) {
        this.eAdposition = eAdposition;
    }

    public String getePagenum() {
        return ePagenum;
    }

    public void setePagenum(String ePagenum) {
        this.ePagenum = ePagenum;
    }
}
