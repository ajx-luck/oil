package com.breast.oil.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class UserInfo {
    @Id
    @GeneratedValue
    private Long id;
    @Column(nullable = false)
    public String username;
    @Column(nullable = false)
    public String tonken;
    @Column(nullable = false)
    public String device;
    @Column(nullable = false)
    public Long lastTime;
    @Column(nullable = false)
    public Long startTime;
    @Column(nullable = false)
    public String secret;
    @Column(nullable = false)
    public String pushkey;

}
