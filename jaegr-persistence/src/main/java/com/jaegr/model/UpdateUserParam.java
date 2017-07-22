package com.jaegr.model;

/**
 * Created by jonas on 16.06.17.
 */
public class UpdateUserParam {
    private String newName;
    private String newPassword;

    public UpdateUserParam() {

    }

    public String getNewName() {
        return newName;
    }

    public void setNewName(String newName) {
        this.newName = newName;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}