package com.jaegr.daos;

import com.jaegr.DBGroup;
import com.jaegr.DBNote;
import com.jaegr.DBUser;
import com.jaegr.DBUser_;
import com.jaegr.model.CreateUserParam;
import com.jaegr.model.UpdateUserParam;

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

    public List<DBUser> getList(){
        final CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
        final CriteriaQuery<DBUser> query = builder.createQuery(DBUser.class);

        final Root<DBUser> from = query.from(DBUser.class);

        List<DBUser> users = this.entityManager.createQuery(query).getResultList();
        return users;
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

    public DBUser create(CreateUserParam p) {
        DBUser user = new DBUser();
        user.setName(p.getName());
        user.setPasswordHash(hashPassword(p.getPassword()));
        //ToDo: assign to default groups
        //ToDo: maybe check for name duplicate

        entityManager.persist(user);
        return user;
    }

    public void disable(long id) {
        DBUser user = get(id);
        user.setDisabled(true);

        entityManager.merge(user);
    }

    public Set<DBUser> getFriends(long id) {
        return get(id).getFriends();
    }

    public void addFriend(long id, long friendId) {
        DBUser user = get(id);
        DBUser friend = get(friendId);

        user.addFriend(friend);

        entityManager.merge(user);
    }

    public void removeFriend(long id, long friendId) {
        DBUser user = get(id);
        DBUser friend = get(friendId);
        Set<DBUser> friends = user.getFriends();

        final CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        final CriteriaQuery<DBUser> query = cb.createQuery(DBUser.class);

        final Root<DBUser> from = query.from(DBUser.class);
        query.select(from)
                .where(cb.or(
                        cb.equal(from.get(DBUser_.id), id),
                        cb.equal(from.get(DBUser_.id), friendId))
                );
        List<DBUser> users = entityManager.createQuery(query).getResultList();
        if(users.size() != 2){
            //ToDO UserNotFoundException
        }

        friends.remove(friend);
        user.setFriends(friends);

        entityManager.merge(user);
    }

    public DBUser update(long id, UpdateUserParam param) {
        DBUser user = get(id);
        HashMap updateMap = new HashMap<String, Object>();
        String newUsername = param.getNewName();
        String newPassword = param.getNewPassword();

        if(newUsername != null && !newUsername.isEmpty())
            updateMap.put(DBUser_.name.getName(), newUsername);

        if(newPassword != null && !newPassword.isEmpty())
            updateMap.put(DBUser_.passwordHash.getName(), hashPassword(newPassword));

        entityManager.refresh(user, updateMap);

        return user;
    }

    public DBUser get(long id) {
        DBUser user = entityManager.find(DBUser.class, id);
        if(user == null) {
            //ToDo: UserNotFoundException
        }

        return user;
    }

    public Set<DBGroup> getGroups(long id){
        DBUser user = get(id);

        return user.getGroups();
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
