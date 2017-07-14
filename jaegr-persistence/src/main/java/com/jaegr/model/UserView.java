package com.jaegr.model;

import com.jaegr.DBUser;

/**
 * Created by jonas on 30.06.17.
 */
public class UserView {
    private String name;
    private long id;
    private boolean disabled;
    private boolean isAdmin;

    public UserView(DBUser user) {
        this.name = user.getName();
        this.id = user.getId();
        this.disabled = user.isDisabled();
        this.isAdmin = user.isAdmin();
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

    public boolean isAdmin() {
        return isAdmin;
    }
}
