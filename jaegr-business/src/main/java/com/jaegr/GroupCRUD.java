package com.jaegr;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.ws.rs.Path;

/**
 * Created by jonas on 30.06.17.
 */
@Path("/friends")
@Transactional
public class GroupCRUD {
    @PersistenceContext
    private EntityManager entityManager;

    //ToDo:
    // get -> GroupView
    // getGroups -> Set<GroupView>
    // create
    // addUser Permissions(!)
    // removeUser
    // addAdmin
    // removeAdmin
    // delete
}
