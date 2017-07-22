package com.jaegr.auth.permission;

import com.jaegr.DBUser;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.Permission;
import org.apache.shiro.subject.Subject;

/**
 * Created by jonas on 13.07.17.
 */
public class IsOwnerPermission implements Permission {
    private long ownerId;

    public IsOwnerPermission(DBUser owner) {
        ownerId = owner.getId();
    }

    public IsOwnerPermission(long userId) {
        ownerId = userId;
    }

    @Override
    public boolean implies(Permission permission) {
        return false;
    }

    public boolean check() {
        Subject subject = SecurityUtils.getSubject();
        return (Long)subject.getPrincipal() == this.ownerId;

    }
}
