package com.breast.oil.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class MessageInfo {
    @Id
    @GeneratedValue
    private Long id;
    @Column(nullable = false)
    public String username;
    @Column(nullable = false)
    public String device;
    //接受者
    @Column(nullable = true)
    public String receivepushkey;
    //发送者
    @Column(nullable = false)
    public String sendpushkey;
    @Column(nullable = false)
    public String sendnickname;
    @Column(nullable = false)
    public String messagecontent;
    @Column(nullable = false)
    public Long sendTime;
    @Column(nullable = false)
    public Long isread;

}
