package com.jaegr.daos;

import com.jaegr.*;

import javax.persistence.EntityManager;
import javax.persistence.criteria.*;
import javax.ws.rs.NotFoundException;
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
            throw new NotFoundException();
        }

        return group;
    }

    public boolean checkIsMember(long id, DBUser user) {
        final CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        final CriteriaQuery<Long> query = cb.createQuery(Long.class);
        final Root<DBGroup> from = query.from(DBGroup.class);

        Predicate isId = cb.equal(from.get(DBGroup_.id), id);
        Predicate isMember = cb.isMember(user, from.get(DBGroup_.users));
        query.select(cb.count(from)).where(cb.and(isId, isMember));


        Long val = entityManager.createQuery(query).getSingleResult();
        return val == 1;

    }

    public DBGroup create(DBUser user, String name){
        DBGroup newGroup = new DBGroup();
        newGroup.setOwner(user);
        newGroup.setUsers(new HashSet<>());
        newGroup.getUsers().add(user);
        newGroup.setName(name);

        entityManager.persist(newGroup);
        return newGroup;
    }

    public void delete(long groupId){
        DBGroup group = get(groupId);
        this.entityManager.remove(group);
    }
}
