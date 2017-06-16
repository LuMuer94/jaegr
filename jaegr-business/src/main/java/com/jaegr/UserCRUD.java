package com.jaegr;

import com.jaegr.daos.UserDAO;
import com.jaegr.model.CreateUserParam;
import com.jaegr.model.UpdateUserParam;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Date;

/**
 * Created by jonas on 16.06.17.
 */
@Path("/user")
@Transactional
public class UserCRUD  {
    @PersistenceContext
    private EntityManager entityManager;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(final CreateUserParam param) {
        UserDAO dao = new UserDAO(entityManager);
        DBUser user = dao.create(param.getName(), param.getPassword());

        return Response.ok(user).build();
    }

    @DELETE
    public Response delete(@PathParam("id") long userId) {
        UserDAO dao = new UserDAO(entityManager);
        dao.delete(userId);
        //ToDo: check permissions, maybe only current user
        return Response.ok().build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response get(@QueryParam("id") long userId) {
        UserDAO dao = new UserDAO(entityManager);
        DBUser user = dao.get(userId);
        return Response.ok(user).build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("id") long userId, final UpdateUserParam param) {
        UserDAO dao = new UserDAO(entityManager);
        dao.update(userId, param.getNewName(), param.getNewPassword());
        return Response.ok().build();
    }

}
