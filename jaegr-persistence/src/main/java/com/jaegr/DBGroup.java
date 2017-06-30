package com.jaegr;

import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import java.util.Set;


//ToDo: 1 admin
/**
 * Created by jonas on 30.06.17.
 */
public class DBGroup extends DBIdentified {
    private Set<DBNote> notes;
    private Set<DBUser> users;
    private String name;
    private Set<DBUser> admins;

    @OneToMany(mappedBy = "group")
    public Set<DBNote> getNotes() {
        return notes;
    }

    public void setNotes(Set<DBNote> notes) {
        this.notes = notes;
    }

    @ManyToMany
    public Set<DBUser> getUsers() {
        return users;
    }

    public void setUsers(Set<DBUser> users) {
        this.users = users;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<DBUser> getAdmins() {
        return admins;
    }

    public void setAdmins(Set<DBUser> admins) {
        this.admins = admins;
    }
}
