package com.jaegr.model;

import com.jaegr.DBGroup;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by jonas on 30.06.17.
 */
public class GroupView {
    private String name;
    private long id;

    private UserView admin;
    private Set<UserView> users;

    public GroupView(DBGroup group) {
        name = group.getName();
        id = group.getId();
        admin = new UserView(group.getOwner());

        users = group.getUsers().stream()
                .map(u -> new UserView(u))
                .collect(Collectors.toSet());
    }

    public UserView getAdmin() {
        return admin;
    }

    public Set<UserView> getUsers() {
        return users;
    }

    public String getName() {
        return name;
    }

    public long getId() {
        return id;
    }
}
