package com.breast.oil.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
@Entity
public class WubaUid {
    @Id
    @GeneratedValue
    private Long id;
    @Column(nullable = true)
    private String uid;

    @Column(nullable = true)
    private Long createtimes;
    @Column(nullable = true)
    private Long updatetimes;

    @Column(nullable = true)
    private Integer usetime;

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

    public Integer getUsetime() {
        return usetime;
    }

    public void setUsetime(Integer usetime) {
        this.usetime = usetime;
    }
}
