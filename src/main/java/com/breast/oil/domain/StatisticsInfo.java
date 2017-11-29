package com.breast.oil.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by B04e on 2017/11/28.
 */
@Entity
public class StatisticsInfo {
    @Id
    @GeneratedValue
    private Long id;
    @Column(nullable = false)
    public String url;
    @Column(nullable = false)
    public String start;
    @Column(nullable = false)
    public String  end;
    @Column(nullable = false)
    public int wechatAdd;
    @Column(nullable = false)
    public String wechatId;
    @Column(nullable = false)
    public String result;
    @Column(nullable = false)
    public String average;
    @Column(nullable = false)
    public Long createTime;
    public String startTime;
    public String endTime;

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public int getAdd() {
        return wechatAdd;
    }

    public void setAdd(int add) {
        this.wechatAdd = add;
    }

    public String getAverage() {
        return average;
    }

    public void setAverage(String average) {
        this.average = average;
    }

    public String getWechatId() {
        return wechatId;
    }

    public void setWechatId(String wechatId) {
        this.wechatId = wechatId;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public int getWechatAdd() {
        return wechatAdd;
    }

    public void setWechatAdd(int wechatAdd) {
        this.wechatAdd = wechatAdd;
    }


}
