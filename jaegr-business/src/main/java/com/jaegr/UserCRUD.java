package com.jaegr;

import com.jaegr.daos.UserDAO;
import com.jaegr.model.CreateUserParam;
import com.jaegr.model.UserView;
import org.json.simple.JSONObject;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Created by danny on 28.06.17.
 */
@Path("/users")
@Transactional
public class UserCRUD {
    @PersistenceContext
    private EntityManager entityManager;

    @Path("/create")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void createUser(CreateUserParam createUserParam) {
        UserDAO dao = new UserDAO(entityManager);
        dao.create(createUserParam);
    }

    @DELETE
    public void disableUser() {
        long currentId = 0;

        UserDAO dao = new UserDAO(entityManager);
        dao.disable(0);
    }

    @Path("/current")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public UserView getCurrent() {
        //ToDo: Shiro
        long currentId = 0;

        UserDAO dao = new UserDAO(entityManager);
        //ToDo: exception, UserNotFound
        DBUser user = dao.get(currentId);
        return new UserView(user);
    }

    // /users?id=1
    // /users/1
    // /users/current
    @Path("/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public UserView get(@PathParam("id") long id) {
        UserDAO dao = new UserDAO(entityManager);

        //ToDo: exception user not found
        DBUser user = dao.get(id);
        return new UserView(user);
    }
}
