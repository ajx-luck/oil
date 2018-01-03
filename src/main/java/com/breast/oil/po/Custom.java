package com.breast.oil.po;

import com.breast.oil.utils.FormatUtils;

/**
 * Created by B04e on 2018/1/3.
 */
public class Custom {
    private String keywordid;
    private Integer weixin;
    private Long web;
    private String keyWord;
    private String percent;

    public Custom(String keywordid, Integer weixin, Long web, String keyWord) {
        this.keywordid = keywordid;
        this.weixin = weixin;
        this.web = web;
        this.keyWord = keyWord;
        percent = FormatUtils.percentage(weixin,web);
    }

    public String getKeywordid() {
        return keywordid;
    }

    public void setKeywordid(String keywordid) {
        this.keywordid = keywordid;
    }

    public Integer getWeixin() {
        return weixin;
    }

    public void setWeixin(Integer weixin) {
        this.weixin = weixin;
    }


    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }

    public Long getWeb() {
        return web;
    }

    public void setWeb(Long web) {
        this.web = web;
    }

    public String getPercent() {
        return percent;
    }

    public void setPercent(String percent) {
        this.percent = percent;
    }
}
