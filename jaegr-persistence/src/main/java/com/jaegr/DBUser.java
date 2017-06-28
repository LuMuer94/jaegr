package com.jaegr;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@XmlRootElement
public class DBUser extends DBIdentified {
    private String name;

    private String passwordHash;

    private Set<DBUser> friends;

    private Set<DBNote> notes;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    //Fetch type to prevent Hibernate LazyInitializationException
    @ManyToMany(fetch= FetchType.EAGER)
    public Set<DBUser> getFriends() {
        if (friends == null) {
            friends = new HashSet<DBUser>();
        }

        return friends;
    }

    public void setFriends(Set<DBUser> friends) {
        this.friends = friends;
    }

    @OneToMany(mappedBy = "user")
    public Set<DBNote> getNotes() {
        if (notes == null) {
            notes = new HashSet<DBNote>();
        }

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
}