package com.jaegr.daos;

import com.jaegr.DBGroup;
import com.jaegr.DBNote;
import com.jaegr.DBUser;
import com.jaegr.DBUser_;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by leono on 06.07.2017.
 */
public class GroupDAO extends BaseDAO{

    public GroupDAO(EntityManager entityManager) {
        super(entityManager);
    }

    public DBGroup get(long id) {
        DBGroup group = entityManager.find(DBGroup.class, id);
        if(group == null) {
            //ToDo: GroupNotFoundException
        }

        return group;
    }

    public DBGroup create(long id, DBGroup group){
        String name = group.getName();
        DBUser user = getUser(id);
        Set<DBUser> users = group.getUsers();

        if(isDuplicate(name)){
            //ToDO NameAlreadyInUseException ?
        }

        if(name == null && name.isEmpty()){
            //ToDo NoNameException
        }
        //users.add(user);
        DBGroup newGroup = new DBGroup();
        newGroup.setName(name);
        newGroup.addAdmin(user);
        newGroup.setUsers(users);

        entityManager.persist(newGroup);

        return newGroup;
    }

    //ToDO not "polished"
    public DBGroup addUser(long userId, long friendId, long groupId){
        DBUser user = getUser(userId);
        DBUser friend = getUser(friendId);
        DBGroup group = get(groupId);

        if(!group.getAdmins().contains(user)){
            //ToDo NoPermissionException
        }
        group.addUser(friend);

        friend.addGroup(group);

        entityManager.merge(group);
        entityManager.merge(friend);

        return group;
    }

    public DBGroup addAdmin(long userId, long newAdminId, long groupId){
        DBUser user = getUser(userId);
        DBUser newAdmin = getUser(newAdminId);
        DBGroup group = get(groupId);

        if(!group.getAdmins().contains(user)){
            //ToDo NoPermissionException
        }

        if(!group.getUsers().contains(user)){
            //ToDo NotAvailableException
        }

        group.addAdmin(newAdmin);

        entityManager.merge(group);

        return group;
    }

    public DBGroup removeUser(long userId, long friendId, long groupId){
        DBUser user = getUser(friendId);
        DBGroup group = get(groupId);

        Set<DBUser> users = group.getUsers();
        users.remove(user);
        group.setUsers(users);

        entityManager.merge(users);

        return group;
    }

    public DBGroup removeAdmin(long userId, long friendId, long groupId){
        DBUser user = getUser(friendId);
        DBGroup group = get(groupId);

        Set<DBUser> admins = group.getAdmins();
        admins.remove(user);
        group.setUsers(admins);

        entityManager.merge(admins);

        return group;
    }

    public DBGroup delete(long userId, long groupId){


        return null;

    }



    public boolean isDuplicate(String name){
        /*
        final CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
        final CriteriaQuery<DBGroup> query = builder.createQuery(DBGroup.class);
        final Root<DBGroup> from = query.from(DBGroup.class);
        query.where(builder.equal(from.get(DBGroup_.name), name));

        return entityManager.createQuery(query).getSingleResult()==null;
        */
        return false;
    }


    public DBUser getUser(long id) {
        DBUser user = entityManager.find(DBUser.class, id);
        if(user == null) {
            //ToDo: UserNotFoundException
        }

        return user;
    }
}
