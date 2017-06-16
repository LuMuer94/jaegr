package com.jaegr;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

/**
 * Created by Leon on 09.06.2017.
 */
@Entity
@XmlRootElement
public class DBNote extends DBIdentified {
    private DBGroup group;

    private DBUser user;

    private String title;

    private Date date;

    @ManyToOne
    public DBGroup getGroup() {
        return group;
    }

    public void setGroup(DBGroup group) {
        this.group = group;
    }

    @ManyToOne
    public DBUser getUser() {
        return user;
    }

    public void setUser(DBUser user) {
        this.user = user;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Temporal(TemporalType.TIMESTAMP)
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
