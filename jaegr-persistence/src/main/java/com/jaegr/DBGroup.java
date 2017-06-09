package com.jaegr;


import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Set;

/**
 * Created by Leon on 09.06.2017.
 */
@Entity
@XmlRootElement
public class DBGroup extends DBIdentified {
    private String name;
    @ManyToMany
    private Set<DBUser> group;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<DBUser> getGroup() {
        return group;
    }

    public void setGroup(Set<DBUser> group) {
        this.group = group;
    }
}
