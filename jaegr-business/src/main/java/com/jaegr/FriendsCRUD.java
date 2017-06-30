package com.jaegr;

import com.jaegr.daos.UserDAO;
import com.jaegr.model.FriendParam;
import com.jaegr.model.UserView;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by jonas on 30.06.17.
 */
@Path("/friends")
@Transactional
public class FriendsCRUD {
    @PersistenceContext
    private EntityManager entityManager;

    @Path("/{id}")
    @GET
    public Set<UserView> getFriends(@PathParam("id") long id) {
        UserDAO dao = new UserDAO(entityManager);
        return dao.getFriends(id).stream()
                .map(u -> new UserView(u))
                .collect(Collectors.toSet());
    }

    @Path("/add/{id}")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void add(FriendParam friend) {
        //toDo:
        long currentId = 0;

        UserDAO dao = new UserDAO(entityManager);
        dao.addFriend(currentId, friend.getId());
    }

    @Path("/remove/{id}")
    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    public void remove(FriendParam friend) {
        //toDo:
        long currentId = 0;

        UserDAO dao = new UserDAO(entityManager);
        dao.removeFriend(currentId, friend.getId());
    }

}
