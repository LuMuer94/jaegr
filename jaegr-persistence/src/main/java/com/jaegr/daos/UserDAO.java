package com.jaegr.daos;

import com.jaegr.DBNote;
import com.jaegr.DBUser;
import com.jaegr.DBUser_;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

/**
 * Created by Leon on 09.06.2017.
 */

public class UserDAO extends BaseDAO{

    public UserDAO(EntityManager entityManager) {
        super(entityManager);
    }

    private static String hashPassword(String password) {
        //ToDo: real hashing
        return String.valueOf(password.hashCode());
    }

    public Set<DBNote> getNotesByUser(long id){
        /*
        final CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
        final CriteriaQuery<DBUser> query = builder.createQuery(DBUser.class);
        final Root<DBUser> from = query.from(DBUser.class);

        query.where(builder.equal(from.get(DBUser_.id), id));
        entityManager.createQuery(query).getSingleResult();
        */
        return get(id).getNotes();
    }

    public DBUser create(String name, String password) {
        DBUser user = new DBUser();
        user.setName(name);
        user.setPasswordHash(hashPassword(password));
        //ToDo: assign to default groups
        //ToDo: maybe check for name duplicate

        entityManager.persist(user);
        return user;
    }

    public void delete(long id) {
        entityManager.remove(get(id));
    }

    public void update(long id, String newUsername, String newPassword) {
        DBUser user = get(id);
        HashMap updateMap = new HashMap<String, Object>();

        if(newUsername != null && !newUsername.isEmpty())
            updateMap.put(DBUser_.name.getName(), newUsername);

        if(newPassword != null && !newPassword.isEmpty())
            updateMap.put(DBUser_.passwordHash.getName(), hashPassword(newPassword));

        entityManager.refresh(user, updateMap);
    }

    public DBUser get(long id) {
        DBUser user = entityManager.find(DBUser.class, id);
        if(user == null) {
            //ToDo: UserNotFoundException
        }

        return user;
    }

    public DBUser login(String username, String password) {
        String passwordHash = hashPassword(password);

        final CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        final CriteriaQuery<DBUser> query = cb.createQuery(DBUser.class);

        final Root<DBUser> from = query.from(DBUser.class);
        query.select(from)
                .where(cb.and(
                        cb.equal(from.get(DBUser_.name), username),
                        cb.equal(from.get(DBUser_.passwordHash), passwordHash))
                );

        //ToDo: map exception to wrong username/password If not found
        return entityManager.createQuery(query).getSingleResult();

    }
}
