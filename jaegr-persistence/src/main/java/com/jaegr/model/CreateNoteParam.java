package com.jaegr.model;

/**
 * Created by jonas on 07.07.17.
 */
public class CreateNoteParam {
    private String title;
    private String content;
    private long groupId;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getGroupId() {
        return groupId;
    }

    public void setGroupId(long groupId) {
        this.groupId = groupId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
