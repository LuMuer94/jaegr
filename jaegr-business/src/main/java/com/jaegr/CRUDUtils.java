package com.jaegr;

import com.jaegr.daos.UserDAO;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.Permission;
import org.apache.shiro.subject.Subject;

import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.core.Response;

/**
 * Created by jonas on 10.07.17.
 */
public class CRUDUtils {
    public static void checkPermission(Permission permission) {
        final Subject subject = SecurityUtils.getSubject();

        if (!subject.isPermitted(permission)) {
            throw new NotAuthorizedException(Response.status(Response.Status.UNAUTHORIZED).build());
        }
    }

    public static void checkPermission(String s) {
        final Subject subject = SecurityUtils.getSubject();

        if (!subject.isPermitted(s)) {
            throw new NotAuthorizedException(Response.status(Response.Status.UNAUTHORIZED).build());
        }
    }

    public static long getCurrentUserId() {
        Subject subject = SecurityUtils.getSubject();
        return (Long) subject.getPrincipal();
    }
}
