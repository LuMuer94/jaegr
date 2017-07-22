package com.jaegr;

import com.jaegr.auth.permission.IsOwnerPermission;
import com.jaegr.daos.UserDAO;
import com.jaegr.model.UserView;
import org.apache.shiro.authz.annotation.RequiresUser;

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
    @RequiresUser
    public Set<UserView> getFriends(@PathParam("id") long id) {
        CRUDUtils.checkPermission(new IsOwnerPermission(id));

        UserDAO dao = new UserDAO(entityManager);
        return dao.get(id).getFriends().stream()
                .map(u -> new UserView(u))
                .collect(Collectors.toSet());
    }

    @Path("/{id}/add/{friendId}")
    @POST
    @RequiresUser
    public void add(@PathParam("id") long id, @PathParam("friendId") long friendId) {
        CRUDUtils.checkPermission(new IsOwnerPermission(id));

        UserDAO dao = new UserDAO(entityManager);
        dao.addFriend(id, friendId);
    }

    @Path("/{id}/remove/{friendId}")
    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    @RequiresUser
    public void remove(@PathParam("id") long id, @PathParam("friendId") long friendId) {
        CRUDUtils.checkPermission(new IsOwnerPermission(id));

        UserDAO dao = new UserDAO(entityManager);
        dao.removeFriend(id, friendId);
    }

}
