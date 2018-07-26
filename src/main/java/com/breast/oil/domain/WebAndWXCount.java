package com.breast.oil.domain;

import com.breast.oil.utils.FormatUtils;

/**
 * Created by B04e on 2017/12/19.
 */
public class WebAndWXCount {
    private String keyWord;
    private String keyWordDesc;
    private String wechatId;
    private Long wxCount;
    private Long webCount;
    private String percentage;

    public WebAndWXCount(String keyWord, String keyWordDesc, Long wxCount, Long webCount) {
        this.keyWord = keyWord;
        this.wxCount = wxCount;
        this.webCount = webCount;
        this.keyWordDesc = keyWordDesc;
        percentage = FormatUtils.percentage(wxCount, webCount);
    }

    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }

    public String getWechatId() {
        return wechatId;
    }

    public void setWechatId(String wechatId) {
        this.wechatId = wechatId;
    }

    public Long getWxCount() {
        return wxCount;
    }

    public void setWxCount(Long wxCount) {
        this.wxCount = wxCount;
    }

    public Long getWebCount() {
        return webCount;
    }

    public void setWebCount(Long webCount) {
        this.webCount = webCount;
    }

    public String getPercentage() {
        return percentage;
    }

    public String getKeyWordDesc() {
        return keyWordDesc;
    }

    public void setKeyWordDesc(String keyWordDesc) {
        this.keyWordDesc = keyWordDesc;
    }
}
