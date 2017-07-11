package com.jaegr.model;

import org.h2.command.ddl.CreateUser;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by jonas on 16.06.17.
 */
@XmlRootElement
public class CreateUserParam {
    private String name;
    private String password;

    public CreateUserParam(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}