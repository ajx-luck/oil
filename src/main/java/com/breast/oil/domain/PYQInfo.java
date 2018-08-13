package com.breast.oil.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class PYQInfo {
    @Id
    @GeneratedValue
    private Long id;
    @Column(nullable = true)
    public String content;
    @Column(nullable = true)
    public String sindex;
    @Column(nullable = true)
    public String asum;
    @Column(nullable = false)
    public String username;
    @Column(nullable = true)
    public String names;

    public PYQInfo(){

    }

    public PYQInfo(String username, String names) {
        this.username = username;
        this.names = names;
    }

    public PYQInfo(String content, String index, String count, String username) {
        this.content = content;
        this.sindex = index;
        this.asum = count;
        this.username = username;
    }
}
