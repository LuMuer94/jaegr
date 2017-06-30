package com.jaegr.model;

import com.jaegr.DBGroup;

import java.util.Set;

/**
 * Created by jonas on 30.06.17.
 */
public class GroupView {
    private String groupName;
    private long groupId;

    private UserView admin;
    private Set<UserView> users;

    public GroupView(DBGroup group) {
        //ToDo
    }

    public String getGroupName() {
        return groupName;
    }

    public long getGroupId() {
        return groupId;
    }

    public UserView getAdmin() {
        return admin;
    }

    public Set<UserView> getUsers() {
        return users;
    }
}
