package com.breast.oil.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class ContactsInfo {
    @Id
    @GeneratedValue
    private Long id;
    @Column(nullable = false)
    public String username;
    @Column(nullable = false)
    public Long isread;
    @Column(nullable = false)
    public String phonenumber;
    @Column(nullable = true)
    public String contactsname;

    public ContactsInfo(){

    }

    public ContactsInfo(String username, Long isread, String phonenumber) {
        this.username = username;
        this.isread = isread;
        this.phonenumber = phonenumber;
    }
}
