package com.jaegr.model;

import javax.validation.constraints.NotNull;

/**
 * Created by jonas on 07.07.17.
 */
public class CreateNoteParam {
    @NotNull
    private String title;
    @NotNull
    private String content;
    private long groupId;

    public CreateNoteParam() {
    }

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
