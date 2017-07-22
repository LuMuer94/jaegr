package com.jaegr.model;

import com.jaegr.DBNote;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by jonas on 30.06.17.
 */
public class NoteView {
    private UserView owner;

    private String groupName;
    private long groupId;

    private long id;

    private Date date;

    private String title;
    private String content;

    public NoteView(DBNote note) {
        this.owner = new UserView(note.getUser());

        this.groupName = note.getGroup().getName();
        this.groupId = note.getGroup().getId();

        this.id = note.getId();
        this.date = note.getDate();
        this.title = note.getTitle();
        this.content = note.getContent();
    }

    public long getId() {
        return id;
    }

    public Date getDate() {
        return date;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public UserView getOwner() {
        return owner;
    }

    public String getGroupName() {
        return groupName;
    }

    public long getGroupId() {
        return groupId;
    }

    /*
       private DBUser user;

    private String title;

    private Date date;

    private DBGroup group;
     */
}
