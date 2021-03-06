package com.jaegr.auth;

import com.jaegr.DBUser;
import com.jaegr.auth.permission.AdminPermission;
import com.jaegr.auth.permission.UserPermission;
import com.jaegr.daos.UserDAO;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.Permission;
import org.apache.shiro.subject.PrincipalCollection;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.Collection;
import java.util.Collections;

/**
 * Created by jonas on 10.07.17.
 */
@Transactional
public class DatabaseAuthorizer {
    @PersistenceContext
    private EntityManager entityManager;

    public AuthorizationInfo fetchAuthorizationInfo(PrincipalCollection principals) {
        Long userId = (Long) principals.getPrimaryPrincipal();
        UserDAO dao = new UserDAO(entityManager);
        DBUser user = dao.get(userId);

        if (user.isDisabled()) {
            throw new AuthorizationException("Disabled user");
        }


        return new AuthorizationInfo() {
            @Override
            public Collection<String> getRoles() {
                if (user.isAdmin()) {
                    return Collections.singleton("admin");
                }

                return Collections.emptyList();
            }

            @Override
            public Collection<String> getStringPermissions() {
                return Collections.emptyList();
            }

            @Override
            public Collection<Permission> getObjectPermissions() {
                if (user.isAdmin()) {
                    return Collections.singleton(new AdminPermission());
                } else {
                    return Collections.singleton(new UserPermission());
                }
            }
        };
    }
}
