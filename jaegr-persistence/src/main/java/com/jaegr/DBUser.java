package com.jaegr;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;
import java.util.Set;

@Entity
@XmlRootElement
public class DBUser extends DBIdentified {
    private String name;

    private String passwordHash;

    private Set<DBGroup> groups;

    private Set<DBNote> notes;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ManyToMany(mappedBy = "users")
    public Set<DBGroup> getGroups() {
        return groups;
    }

    public void setGroups(Set<DBGroup> groups) {
        this.groups = groups;
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
}

