package com.jaegr;


import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;
import java.util.Set;

/**
 * Created by Leon on 09.06.2017.
 */
@Entity
@XmlRootElement
public class DBGroup extends DBIdentified {
    private String name;

    private Set<DBNote> notes;

    private Set<DBUser> users;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ManyToMany
    public Set<DBUser> getUsers() {
        return users;
    }

    public void setUsers(Set<DBUser> users) {
        this.users = users;
    }

    @OneToMany(mappedBy = "group")
    public Set<DBNote> getNotes() {
        return notes;
    }

    public void setNotes(Set<DBNote> notes) {
        this.notes = notes;
    }
}
