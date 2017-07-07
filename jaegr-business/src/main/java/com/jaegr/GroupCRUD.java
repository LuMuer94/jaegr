package com.jaegr;

import com.jaegr.daos.GroupDAO;
import com.jaegr.daos.UserDAO;
import com.jaegr.model.GroupView;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by jonas on 30.06.17.
 */
@Path("/friends")
@Transactional
public class GroupCRUD {
    @PersistenceContext
    private EntityManager entityManager;

    @Path("/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response get(@PathParam("id") final long id) {
        GroupDAO dao = new GroupDAO(entityManager);

        //ToDo: exception group not found
        DBGroup group = dao.get(id);
        return Response.ok(new GroupView(group)).build();
    }

    @Path("/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getGroups(@PathParam("id") final long id) {
        UserDAO dao = new UserDAO(entityManager);
        Set<GroupView> groups = dao.getGroups(id).stream()
                .map(u -> new GroupView(u))
                .collect(Collectors.toSet());

        return Response.ok(groups).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createGroup(final long creatorId, DBGroup group){
        GroupDAO dao = new GroupDAO(entityManager);

        DBGroup newGroup = dao.create(creatorId, group);

        return Response.ok(new GroupView(newGroup)).build();
    }

    @POST
    @Path("/add")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addUser(final long userId, final long friendId, final long groupId){
        GroupDAO dao = new GroupDAO(entityManager);

        DBGroup group = dao.addUser(userId, friendId, groupId);

        return Response.ok(new GroupView(group)).build();
    }

    @POST
    @Path("/remove")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response removeUser(final long userId, final long friendId, final long groupId){
        GroupDAO dao = new GroupDAO(entityManager);

        DBGroup group = dao.removeUser(userId, friendId, groupId);

        return Response.ok(new GroupView(group)).build();
    }

    @POST
    @Path("/add")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addAdmin(final long userId, final long newAdminId, final long groupId){
        GroupDAO dao = new GroupDAO(entityManager);

        DBGroup group = dao.addAdmin(userId, newAdminId, groupId);

        return Response.ok(new GroupView(group)).build();
    }

    @POST
    @Path("/remove")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response removeAdmin(final long userId, final long friendId, final long groupId){
        GroupDAO dao = new GroupDAO(entityManager);

        DBGroup group = dao.removeAdmin(userId, friendId, groupId);

        return Response.ok(new GroupView(group)).build();
    }

    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteGroup(final long userId, final long groupId){
        GroupDAO dao = new GroupDAO(entityManager);

        DBGroup group = dao.delete(userId, groupId);

        return Response.ok(new GroupView(group)).build();
    }




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
