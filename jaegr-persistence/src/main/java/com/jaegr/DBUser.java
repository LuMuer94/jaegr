package com.jaegr;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


//ToDo: ensure name is unique(not working right now)

@Entity
@XmlRootElement
public class DBUser extends DBIdentified {
    private String name;

    private String passwordHash;
    private String passwordSalt;
    private Set<DBUser> friends;
    private Set<DBNote> notes;
    private Set<DBGroup> groups;
    private Set<DBGroup> ownedGroups;
    private boolean disabled;
    private boolean isAdmin;

    @Column(unique=true)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ManyToMany
    public Set<DBUser> getFriends() {
        return friends;
    }

    public void setFriends(Set<DBUser> friends) {
        this.friends = friends;
    }

    @OneToMany(mappedBy = "user")
    public Set<DBNote> getNotes() {
        return notes;
    }

    public void setNotes(Set<DBNote> notes) {
        this.notes = notes;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    @ManyToMany(mappedBy = "users")
    public Set<DBGroup> getGroups() {
        return groups;
    }

    public void setGroups(Set<DBGroup> groups) {
        this.groups = groups;
    }

    public boolean isDisabled() {
        return disabled;
    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }

    @OneToMany(mappedBy = "owner")
    public Set<DBGroup> getOwnedGroups() {
        return ownedGroups;
    }

    public void setOwnedGroups(Set<DBGroup> ownedGroups) {
        this.ownedGroups = ownedGroups;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public String getPasswordSalt() {
        return passwordSalt;
    }

    public void setPasswordSalt(String passwordSalt) {
        this.passwordSalt = passwordSalt;
    }
}