package com.breast.oil.domain;

import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
@Entity
public class WubaUser {
    @Id
    @GeneratedValue
    private Long id;
    @Column(nullable = true)
    private String uid;
    @Type(type = "text")
    @Column(nullable = true)
    private String wubacook1;
    @Type(type = "text")
    @Column(nullable = true)
    private String wubacook2;
    @Column(nullable = true)
    private Long createtimes;
    @Column(nullable = true)
    private Long updatetimes;
    @Column(nullable = true)
    private Integer login;

    @Column(nullable = true)
    private String followjson;

    @Column(nullable = true)
    private Integer block;

    @Column(nullable = true)
    private String mobile;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getWubacook1() {
        return wubacook1;
    }

    public void setWubacook1(String wubacook1) {
        this.wubacook1 = wubacook1;
    }

    public String getWubacook2() {
        return wubacook2;
    }

    public void setWubacook2(String wubacook2) {
        this.wubacook2 = wubacook2;
    }

    public Long getCreatetimes() {
        return createtimes;
    }

    public void setCreatetimes(Long createtimes) {
        this.createtimes = createtimes;
    }

    public Long getUpdatetimes() {
        return updatetimes;
    }

    public void setUpdatetimes(Long updatetimes) {
        this.updatetimes = updatetimes;
    }

    public Integer getLogin() {
        return login;
    }

    public void setLogin(Integer login) {
        this.login = login;
    }

    public String getFollowjson() {
        return followjson;
    }

    public void setFollowjson(String followjson) {
        this.followjson = followjson;
    }

    public Integer getBlock() {
        return block;
    }

    public void setBlock(Integer block) {
        this.block = block;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
