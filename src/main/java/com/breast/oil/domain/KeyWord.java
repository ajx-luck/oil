package com.breast.oil.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
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
    @Column(nullable = false)
    private String keyWordDesc;
    @Column(nullable = true)
    public Long createTime;

    public KeyWord(){
        this.createTime = new Date().getTime();
    }

    public KeyWord(String keyWord, String keyWordDesc, Long createTime) {
        this.keyWord = keyWord;
        this.keyWordDesc = keyWordDesc;
        this.createTime = createTime;
    }

    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }

    public String getKeyWordDesc() {
        return keyWordDesc;
    }

    public void setKeyWordDesc(String keyWordDesc) {
        this.keyWordDesc = keyWordDesc;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

}
