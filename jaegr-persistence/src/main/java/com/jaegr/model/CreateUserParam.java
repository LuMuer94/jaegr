package com.jaegr.model;

import org.h2.command.ddl.CreateUser;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by jonas on 16.06.17.
 */
@XmlRootElement
public class CreateUserParam {
    @NotNull
    private String name;
    private String password;

    public CreateUserParam() {
    }

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