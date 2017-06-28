package com.jaegr;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Leon on 09.06.2017.
 */
@Entity
@XmlRootElement
public class DBNote extends DBIdentified{
    private Set<DBUser> recipients;

    private DBUser user;

    private String title;

    private Date date;

    //TODO Enum
    private boolean privacy;

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

    public boolean isPrivacy() {
        return privacy;
    }

    public void setPrivacy(boolean privacy) {
        this.privacy = privacy;
    }

    @ManyToMany
    public Set<DBUser> getRecipients() {
        if (recipients == null) {
            recipients = new HashSet<DBUser>();
        }
        return recipients;
    }

    public void setRecipients(Set<DBUser> recipients) {
        this.recipients = recipients;
    }


}