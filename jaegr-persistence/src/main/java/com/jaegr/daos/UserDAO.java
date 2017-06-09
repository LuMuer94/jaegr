package com.jaegr.daos;

import com.jaegr.DBNote;
import com.jaegr.DBUser;

import javax.persistence.EntityManager;
import java.util.Set;

/**
 * Created by Leon on 09.06.2017.
 */

public class UserDAO extends BaseDAO{

    protected UserDAO(EntityManager entityManager) {
        super(entityManager);
    }

    public Set<DBNote> getNotesByUser(long id){
        /*
        final CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
        final CriteriaQuery<DBUser> query = builder.createQuery(DBUser.class);
        final Root<DBUser> from = query.from(DBUser.class);

        query.where(builder.equal(from.get(DBUser_.id), id));
        entityManager.createQuery(query).getSingleResult();
        */
        DBUser user;
        user = entityManager.find(DBUser.class, id);
        if(user==null){
            /*
            TODO UserNotFoundException
             */
        }
        return user.getNotes();
    }


}
