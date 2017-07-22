package com.jaegr.daos;

import com.jaegr.DBGroup;
import com.jaegr.DBNote;
import com.jaegr.DBUser;
import com.jaegr.DBUser_;
import com.jaegr.model.CreateUserParam;
import com.jaegr.model.UpdateUserParam;
import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.apache.shiro.util.ByteSource;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.ws.rs.NotFoundException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Leon on 09.06.2017.
 */

public class UserDAO extends BaseDAO{

    public UserDAO(EntityManager entityManager) {
        super(entityManager);
    }

    private static void generatePasswordHash(DBUser user, String password) {
        RandomNumberGenerator rng = new SecureRandomNumberGenerator();
        String salt = rng.nextBytes().toBase64();

        String hashedPasswordBase64 = new Sha256Hash(password, salt, 1024).toBase64();
        user.setPasswordHash(hashedPasswordBase64);
        user.setPasswordSalt(salt);
    }

    public DBUser create(CreateUserParam p, boolean isAdmin) {
        DBUser user = new DBUser();
        user.setName(p.getName());
        user.setAdmin(isAdmin);
        //ToDo: add all fields
        generatePasswordHash(user, p.getPassword());

        entityManager.persist(user);
        return user;
    }

    public void disable(long id) {
        DBUser user = get(id);
        user.setDisabled(true);
    }

    public void addFriend(long id, long friendId) {
        DBUser user = get(id);
        DBUser friend = get(friendId);

        user.getFriends().add(friend);
    }

    public void removeFriend(long id, long friendId) {
        DBUser user = get(id);
        DBUser friend = get(friendId);

        user.getFriends().remove(friend);
    }

    public DBUser update(long id, UpdateUserParam param) {
        DBUser user = get(id);
        String newUsername = param.getNewName();
        String newPassword = param.getNewPassword();

        if(newUsername != null && !newUsername.isEmpty())
            user.setName(newUsername);

        if(newPassword != null && !newPassword.isEmpty())
            generatePasswordHash(user, newPassword);

        return user;
    }

    public DBUser get(long id) {
        DBUser user = entityManager.find(DBUser.class, id);
        if(user == null) {
            throw new NotFoundException();
        }

        return user;
    }

    public DBUser getByName(String username) {
        final CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        final CriteriaQuery<DBUser> query = cb.createQuery(DBUser.class);

        final Root<DBUser> from = query.from(DBUser.class);
        query.select(from).where(cb.equal(from.get(DBUser_.name), username));

        List<DBUser> results = entityManager.createQuery(query).getResultList();
        if(results.isEmpty())
            return null;

        return results.get(0);
    }

    public List<DBUser> search(String likeName) {
        final CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        final CriteriaQuery<DBUser> query = cb.createQuery(DBUser.class);

        final Root<DBUser> from = query.from(DBUser.class);
        query.select(from).where(cb.like(from.get(DBUser_.name), likeName + "%"));
        return entityManager.createQuery(query).getResultList();
    }

}
