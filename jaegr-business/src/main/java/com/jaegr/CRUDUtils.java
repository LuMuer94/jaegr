package com.jaegr;

import com.jaegr.daos.UserDAO;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.Permission;
import org.apache.shiro.subject.Subject;

import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.core.Response;
import java.util.Arrays;

/**
 * Created by jonas on 10.07.17.
 */
public class CRUDUtils {
    public static void checkPermission(Permission permission) {
        checkAnyPermissions(new Permission[]{permission});
    }

    public static void checkAnyPermissions(Permission[] permission) {
        final Subject subject = SecurityUtils.getSubject();

        boolean any = Arrays.stream(permission)
                            .anyMatch(subject::isPermitted);

        if(!any)
            throw new NotAuthorizedException(Response.status(Response.Status.UNAUTHORIZED).build());
    }

    public static long getCurrentUserId() {
        Subject subject = SecurityUtils.getSubject();
        return (Long) subject.getPrincipal();
    }
}
