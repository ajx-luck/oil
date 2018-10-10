package com.breast.oil.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class VersionInfo {
    @Id
    @GeneratedValue
    private Long id;
    @Column(nullable = false)
    public String urlPath;
    @Column(nullable = false)
    public String versionCode;
}
