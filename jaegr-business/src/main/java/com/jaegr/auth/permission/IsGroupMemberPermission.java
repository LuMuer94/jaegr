package com.jaegr.auth.permission;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.Permission;
import org.apache.shiro.subject.Subject;

/**
 * Created by jonas on 13.07.17.
 */
public class IsGroupMemberPermission  implements Permission {
    private boolean isGroupMember;

    public IsGroupMemberPermission(boolean isGroupMember) {
        this.isGroupMember = isGroupMember;
    }


    @Override
    public boolean implies(Permission permission) {
        return false;
    }

    public boolean check() {
        return isGroupMember;
    }
}
