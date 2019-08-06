package com.breast.oil.domain;

import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class DouyinAccount {
    @Id
    @GeneratedValue
    private Long id;
    @Column(nullable = true)
    private String phone;
    @Type(type = "text")
    @Column(nullable = true)
    private String android;
    @Type(type = "text")
    @Column(nullable = true)
    private String ios;
    @Column(nullable = true)
    private Long createtimes;
    @Column(nullable = true)
    private Long updatetimes;
    @Column(nullable = true)
    private Long followtimes;
    @Column(nullable = true)
    private Long androidusetimes;
    @Column(nullable = true)
    private Long iosusetimes;
    @Column(nullable = true)
    private String filename;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAndroid() {
        return android;
    }

    public void setAndroid(String android) {
        this.android = android;
    }

    public String getIos() {
        return ios;
    }

    public void setIos(String ios) {
        this.ios = ios;
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

    public Long getAndroidusetimes() {
        return androidusetimes;
    }

    public void setAndroidusetimes(Long androidusetimes) {
        this.androidusetimes = androidusetimes;
    }

    public Long getIosusetimes() {
        return iosusetimes;
    }

    public void setIosusetimes(Long iosusetimes) {
        this.iosusetimes = iosusetimes;
    }

    public Long getFollowtimes() {
        return followtimes;
    }

    public void setFollowtimes(Long followtimes) {
        this.followtimes = followtimes;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }
}
