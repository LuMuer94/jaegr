package com.jaegr.model;

import com.jaegr.DBNote;

import java.util.Date;

/**
 * Created by jonas on 30.06.17.
 */
public class NoteView {
    private String userName;
    private long userId;

    private String groupName;
    private long groupId;

    private Date date;

    private String title;
    private String content;

    public NoteView(DBNote note) {
        //ToDo:
    }

    public String getUserName() {
        return userName;
    }

    public long getUserId() {
        return userId;
    }

    public String getGroupName() {
        return groupName;
    }

    public long getGroupId() {
        return groupId;
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

    /*
       private DBUser user;

    private String title;

    private Date date;

    private DBGroup group;
     */
}
