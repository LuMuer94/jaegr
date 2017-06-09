package com.jaegr;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Set;

/**
 * Created by Leon on 09.06.2017.
 */
@Entity
@XmlRootElement
public class DBUser extends DBIdentified {
    private String name;
    @ManyToMany
    private Set<DBGroup> groups;
    @OneToMany
    private Set<DBNote> notes;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<DBGroup> getGroups() {
        return groups;
    }

    public void setGroups(Set<DBGroup> groups) {
        this.groups = groups;
    }

    public Set<DBNote> getNotes() {
        return notes;
    }

    public void setNotes(Set<DBNote> notes) {
        this.notes = notes;
    }
}

