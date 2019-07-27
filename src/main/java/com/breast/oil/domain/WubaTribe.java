package com.breast.oil.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class WubaTribe {
    @Id
    @GeneratedValue
    private Long id;
    @Column(nullable = true)
    private String aid;
    @Column(nullable = true)
    private String tribe;
    @Column(nullable = true)
    private Long createtimes;
    @Column(nullable = true)
    private Long updatetimes;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAid() {
        return aid;
    }

    public void setAid(String aid) {
        this.aid = aid;
    }

    public String getTribe() {
        return tribe;
    }

    public void setTribe(String tribe) {
        this.tribe = tribe;
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
}
