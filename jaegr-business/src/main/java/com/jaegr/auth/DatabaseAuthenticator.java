package com.jaegr.auth;

import com.jaegr.DBUser;
import com.jaegr.daos.UserDAO;
import org.apache.shiro.authc.*;
import org.apache.shiro.util.ByteSource;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Transactional
public class DatabaseAuthenticator {
    @PersistenceContext
    private EntityManager entityManager;

    public AuthenticationInfo fetchAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        if(token instanceof  UsernamePasswordToken) {
            UsernamePasswordToken userPwToken = (UsernamePasswordToken) token;
            UserDAO dao = new UserDAO(entityManager);

            DBUser user = dao.getByName(userPwToken.getUsername());

            if (user == null)
                throw new AuthenticationException("Either wrong username or password");

            if(user.isDisabled())
                throw new AuthenticationException("User is no longer active");

            String pwHash = user.getPasswordHash();
            ByteSource salt = ByteSource.Util.bytes(user.getPasswordSalt());

            System.out.println("Logged in:" + user.getId() + "pw:" + new String(userPwToken.getPassword()));
            return new SimpleAccount(user.getId(), pwHash, salt, JaegrRealm.REALM);
        } else {
            throw new AuthenticationException("Unsupported AuthenticationToken");
        }
    }

}