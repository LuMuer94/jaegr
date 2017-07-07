package com.jaegr;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.HashSet;
import java.util.Set;


//ToDo: 1 admin
/**
 * Created by jonas on 30.06.17.
 */
@Entity
@XmlRootElement
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
        if (users == null) {
            users = new HashSet<DBUser>();
        }
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
        if (admins == null) {
            admins = new HashSet<DBUser>();
        }
        return admins;
    }

    public void setAdmins(Set<DBUser> admins) {
        this.admins = admins;
    }

    public boolean removeUser(DBUser user){
        return this.users.remove(user);
    }
}
