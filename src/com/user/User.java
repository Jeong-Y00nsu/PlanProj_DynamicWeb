package com.user;

import lombok.Data;

import java.io.Serializable;


@Data
public class User implements Serializable {

    private String id;
    private String pw;

    private String name;

    private String planId;

    private String salt;

    public User(){}
    public User(String id, String pw, String name, String planId, String salt) {
        this.id = id;
        this.pw = pw;
        this.name = name;
        this.planId = planId;
        this.salt = salt;
    }
}
