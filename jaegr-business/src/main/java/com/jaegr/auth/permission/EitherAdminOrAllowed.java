package com.jaegr.auth.permission;

import com.jaegr.DBUser;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.Permission;
import org.apache.shiro.subject.Subject;

public class EitherAdminOrAllowed implements Permission {
    private boolean isAllowed;

    public EitherAdminOrAllowed(boolean isAllowed) {
        this.isAllowed = isAllowed;
    }


    @Override
    public boolean implies(Permission permission) {
        return check();
    }

    public boolean check() {
        Subject subject = SecurityUtils.getSubject();
        return isAllowed || subject.hasRole("admin");
    }
}
