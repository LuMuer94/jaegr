package com.jaegr.auth;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.Permission;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.subject.PrincipalCollection;

import javax.enterprise.context.spi.CreationalContext;
import javax.enterprise.inject.spi.Bean;
import javax.enterprise.inject.spi.BeanManager;
import javax.enterprise.inject.spi.CDI;
import java.util.Collection;
import java.util.Collections;
import java.util.Set;

public class JaegrRealm extends AuthorizingRealm implements Realm {
    final static String REALM = "JAEGR";

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        final BeanManager bm = CDI.current().getBeanManager();
        final Set<Bean<?>> beans = bm.getBeans(DatabaseAuthenticator.class);

        if (beans.isEmpty()) {
            throw new AuthenticationException();
        }

        final Bean<DatabaseAuthenticator> bean = (Bean<DatabaseAuthenticator>) bm.resolve(beans);
        final CreationalContext<DatabaseAuthenticator> cctx = bm.createCreationalContext(bean);
        final DatabaseAuthenticator authenticator =
                (DatabaseAuthenticator) bm.getReference(bean, DatabaseAuthenticator.class, cctx);

        return authenticator.fetchAuthenticationInfo(token);
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        final BeanManager bm = CDI.current().getBeanManager();
        final Set<Bean<?>> beans = bm.getBeans(DatabaseAuthorizer.class);

        if (beans.isEmpty()) {
            throw new AuthorizationException();
        }

        final Bean<DatabaseAuthorizer> bean = (Bean<DatabaseAuthorizer>) bm.resolve(beans);
        final CreationalContext<DatabaseAuthorizer> cctx = bm.createCreationalContext(bean);
        final DatabaseAuthorizer authorizer =
                (DatabaseAuthorizer) bm.getReference(bean, DatabaseAuthorizer.class, cctx);

        return authorizer.fetchAuthorizationInfo(principals);
    }
}
