package com.breast.oil.domain;

import com.breast.oil.utils.TimeUtils;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

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
    @Type(type="text")
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
    /**
     * 定位的位置
     */
    @Column(nullable = true)
    private String city;
    /**
     * 出价价钱
     */
    @Column(nullable = true)
    private String price;

    /**
     * 人群编号
     */
    @Column(nullable = true)
    private String audience;

    /**
     * 单元名称
     */
    @Column(nullable = true)
    private String dy;

    /**
     * 计划名称
     */
    @Column(nullable = true)
    private String jh;

    @Column(nullable = true)
    private String provice;
    /**
     * 百度入口url
     */
    @Column(nullable = true)
    private String strartUrl;

    public WebInfo(){

    }

    public WebInfo(String urlPath, Long createTime, String ip, String wechatId, String keyWord, String e_keywordid, String refer, String e_matchtype,
                   String e_creative, String e_adposition, String e_pagenum,String price,String audience,String dy,String jh,String provice,String strartUrl) {
        this.urlPath = urlPath;
        this.createTime = createTime;
        this.ip = ip;
        this.wechatId = wechatId;
        this.keyWord = keyWord;
        this.eKeywordid = e_keywordid;
        this.refer = refer;
        this.eMatchtype = e_matchtype;
        this.eCreative = e_creative;
        this.eAdposition = e_adposition;
        this.ePagenum = e_pagenum;
        this.displayTime = TimeUtils.timesToDate(createTime);
        this.price = price;
        this.audience = audience;
        this.dy = dy;
        this.jh = jh;
        this.provice = provice;
        this.strartUrl = strartUrl;
    }


    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Long getId() {
        return id;
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

    public String getDisplayTime() {
        return displayTime;
    }

    public void setDisplayTime(String displayTime) {
        this.displayTime = displayTime;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getAudience() {
        return audience;
    }

    public void setAudience(String audience) {
        this.audience = audience;
    }

    public String getDy() {
        return dy;
    }

    public void setDy(String dy) {
        this.dy = dy;
    }

    public String getJh() {
        return jh;
    }

    public void setJh(String jh) {
        this.jh = jh;
    }

    public String getProvice() {
        return provice;
    }

    public void setProvice(String provice) {
        this.provice = provice;
    }

    public String getStrartUrl() {
        return strartUrl;
    }

    public void setStrartUrl(String strartUrl) {
        this.strartUrl = strartUrl;
    }

    @Override
    public String toString() {
        return
                "urlPath=" + urlPath +
                "&createTime=" + createTime +
                "&displayTime=" + displayTime +
                "&ip=" + ip +
                "&wechatId=" + wechatId +
                "&keyWord=" + keyWord +
                "&eKeywordid=" + eKeywordid +
                "&refer=NULL" +
                "&eMatchtype=" + eMatchtype +
                "&eCreative=" + eCreative +
                "&eAdposition=" + eAdposition +
                "&ePagenum=" + ePagenum +
                "&city=" + city +
                "&price=" + price +
                "&audience=" + audience +
                "&dy=" + dy +
                "&jh=" + jh
                ;
    }
}
