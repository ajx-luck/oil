package com.breast.oil.domain;

import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;


import javax.persistence.*;
import java.util.Date;

/**
 * Created by B04e on 2017/12/19.
 */
@Entity
public class KeyWord {
    @Id
    @GeneratedValue
    private Long id;
    @Column(nullable = false,unique = true)
    private String keyWord;
    @Column(nullable = true)
    private String e_keywordid;
    @Column(nullable = true)
    public Long createTime;

    /**
     * web点击次数
     */
    @Column(nullable = true)
    public Long web;
    /**
     * wx点击次数
     */
    @Column(nullable = true)
    public Long wxClick;


    public KeyWord(){
        this.createTime = new Date().getTime();
    }

    public KeyWord(String keyWord, String e_keywordid, Long createTime) {
        this(keyWord,e_keywordid,createTime,1L,0L);
    }

    public KeyWord(String keyWord, String e_keywordid, Long createTime, Long web, Long wxClick) {
        this.keyWord = keyWord;
        this.e_keywordid = e_keywordid;
        this.createTime = createTime;
        this.web = web;
        this.wxClick = wxClick;
    }

    public String getE_keywordid() {
        return e_keywordid;
    }

    public void setE_keywordid(String e_keywordid) {
        this.e_keywordid = e_keywordid;
    }

    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }


    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Long getWeb() {
        return web;
    }

    public void setWeb(Long web) {
        this.web = web;
    }

    public Long getWxClick() {
        return wxClick;
    }

    public void setWxClick(Long wxClick) {
        this.wxClick = wxClick;
    }
}
