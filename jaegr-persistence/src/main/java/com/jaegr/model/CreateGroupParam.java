package com.jaegr.model;

import javax.validation.constraints.NotNull;

/**
 * Created by jonas on 07.07.17.
 */
public class CreateGroupParam {
    @NotNull
    private String name;

    public CreateGroupParam() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
