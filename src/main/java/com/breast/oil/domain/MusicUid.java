package com.breast.oil.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class MusicUid {
    @Id
    @GeneratedValue
    private Long id;
    @Column(nullable = false, unique = true)
    private String uid;

    @Column(nullable = true)
    private Long createtimes;
    @Column(nullable = true)
    private Long updatetimes;

    @Column(nullable = true)
    private Integer usetime;

    @Column(nullable = true)
    private Integer gender;

    @Column(nullable = true)
    private String nickname;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}
