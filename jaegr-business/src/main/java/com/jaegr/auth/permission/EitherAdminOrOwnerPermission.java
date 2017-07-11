package com.jaegr.auth.permission;

import com.jaegr.DBUser;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.Permission;
import org.apache.shiro.subject.Subject;

/**
 * Created by jonas on 10.07.17.
 */
//ToDo: make permissions properly
public class EitherAdminOrOwnerPermission implements Permission {
    private long ownerId;

    public EitherAdminOrOwnerPermission(DBUser owner) {
        ownerId = owner.getId();
    }

    public EitherAdminOrOwnerPermission(long userId) {
        ownerId = userId;
    }

    @Override
    public boolean implies(Permission permission) {
        return check();
    }

    public boolean check() {
        Subject subject = SecurityUtils.getSubject();
        return subject.hasRole("admin") || (Long) subject.getPrincipal() == this.ownerId;

    }
}
