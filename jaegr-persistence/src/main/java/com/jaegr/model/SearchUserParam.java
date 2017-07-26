package com.jaegr.model;

import javax.validation.constraints.NotNull;

/**
 * Created by jonas on 11.07.17.
 */
public class SearchUserParam {
    @NotNull
    private String likeName;

    public SearchUserParam() {
    }

    public String getLikeName() {
        return likeName;
    }

    public void setLikeName(String likeName) {
        this.likeName = likeName;
    }
}
