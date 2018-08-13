package com.breast.oil.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class SecretInfo {
    @Id
    @GeneratedValue
    private Long id;
    @Column(nullable = false)
    public String secret;
    //小时
    @Column(nullable = false)
    public Long availTime;
    @Column(nullable = false)
    public Boolean isUse;
}
