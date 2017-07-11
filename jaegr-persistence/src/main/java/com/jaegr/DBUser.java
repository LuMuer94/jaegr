package com.jaegr;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@XmlRootElement
public class DBUser extends DBIdentified {
    @Column(unique=true)
    private String name;
    private String passwordHash;
    private String passwordSalt;
    private Set<DBUser> friends;
    private Set<DBNote> notes;
    private Set<DBGroup> groups;
    private Set<DBGroup> ownedGroups;
    private boolean disabled;
    private boolean isAdmin;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ManyToMany
    public Set<DBUser> getFriends() {
        if (friends == null) {
            friends = new HashSet<>();
        }
        return friends;
    }

    public boolean addFriend(DBUser friend){
        return this.friends.add(friend);
    }

    public void setFriends(Set<DBUser> friends) {
        this.friends = friends;
    }

    @OneToMany(mappedBy = "user")
    public Set<DBNote> getNotes() {
        if(notes == null){
            notes = new HashSet<>();
        }
        return notes;
    }

    public void setNotes(Set<DBNote> notes) {
        this.notes = notes;
    }

    public boolean addNote(DBNote note){
        if(notes == null){
            notes = new HashSet<>();
        }
        return notes.add(note);
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    @ManyToMany(mappedBy = "users")
    public Set<DBGroup> getGroups() {
        if (groups == null) {
            groups = new HashSet<>();
        }
        return groups;
    }

    public void setGroups(Set<DBGroup> groups) {
        this.groups = groups;
    }

    public boolean addGroup(DBGroup group){
        if (groups == null) {
            groups = new HashSet<>();
        }
        return this.groups.add(group);
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