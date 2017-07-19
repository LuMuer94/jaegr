package com.jaegr.auth.permission;

import org.apache.shiro.authz.Permission;

/**
 * Created by jonas on 13.07.17.
 */
public class UserPermission  implements Permission {
    @Override
    public boolean implies(Permission permission) {
        if(permission instanceof IsGroupMemberPermission) {
            IsGroupMemberPermission p = (IsGroupMemberPermission)permission;
            if(p.check()) {
                return true;
            }
        } else if(permission instanceof  IsOwnerPermission) {
            IsOwnerPermission p = (IsOwnerPermission)permission;
            if(p.check()) {
                return true;
            }
        }

        return false;
    }
}
