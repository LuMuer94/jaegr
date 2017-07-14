package com.jaegr.auth.permission;

import org.apache.shiro.authz.Permission;

/**
 * Created by jonas on 13.07.17.
 */
public class AdminPermission  implements Permission {
    @Override
    public boolean implies(Permission permission) {
        if(permission instanceof IsGroupMemberPermission) {
            return true;
        } else if(permission instanceof  IsOwnerPermission) {
            return true;
        }

        return false;
    }
}
