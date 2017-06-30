package com.jaegr.model;

import com.jaegr.DBUser;

/**
 * Created by jonas on 30.06.17.
 */
public class UserView {
    private String name;
    private long id;
    private boolean disabled;

    public UserView(DBUser user) {
        this.name = user.getName();
        this.id = user.getId();
        this.disabled = user.isDisabled();
    }

    public String getName() {
        return name;
    }

    public long getId() {
        return id;
    }

    public boolean isDisabled() {
        return disabled;
    }
}
