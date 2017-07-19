package com.jaegr;

import com.jaegr.auth.permission.IsOwnerPermission;
import com.jaegr.daos.UserDAO;
import com.jaegr.model.CreateUserParam;
import com.jaegr.model.SearchUserParam;
import com.jaegr.model.UpdateUserParam;
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
 * Created by danny on 28.06.17.
 */

//ToDo: handle user disabled
@Path("/users")
@Transactional
public class UserCRUD {
    @PersistenceContext
    private EntityManager entityManager;

    @Path("/create")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public UserView createUser(CreateUserParam createUserParam) {
        UserDAO dao = new UserDAO(entityManager);
        return new UserView(dao.create(createUserParam, false));
    }

    @Path("/{id}")
    @DELETE
    @RequiresUser
    public void disableUser(@PathParam("id") long id) {
        CRUDUtils.checkPermission(new IsOwnerPermission(id));
        new UserDAO(entityManager).disable(id);
    }

    @Path("/{id}/update")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @RequiresUser
    public UserView updateUser(@PathParam("id") long id, UpdateUserParam updateUserParam){
        CRUDUtils.checkPermission(new IsOwnerPermission(id));
        return new UserView(new UserDAO(entityManager).update(id, updateUserParam));
    }

    @Path("/{id:[0-9]}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @RequiresUser
    public UserView get(@PathParam("id") long id) {
        CRUDUtils.checkPermission(new IsOwnerPermission(id));
        return new UserView(new UserDAO(entityManager).get(id));
    }

    @Path("/current")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @RequiresUser
    public UserView getCurrent() {
        long id = CRUDUtils.getCurrentUserId();
        return new UserView(new UserDAO(entityManager).get(id));
    }

    @Path("/search")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @RequiresUser
    public Set<UserView> search(SearchUserParam p){
        UserDAO dao = new UserDAO(entityManager);
        return dao.search(p.getLikeName()).stream()
                .map(u -> new UserView(u))
                .collect(Collectors.toSet());
    }


}
