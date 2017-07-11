package com.jaegr;

import com.jaegr.auth.permission.EitherAdminOrOwnerPermission;
import com.jaegr.daos.GroupDAO;
import com.jaegr.daos.UserDAO;
import com.jaegr.model.CreateGroupParam;
import com.jaegr.model.GroupView;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresUser;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Set;
import java.util.stream.Collectors;

@Path("/groups")
@Transactional
public class GroupCRUD {
    @PersistenceContext
    private EntityManager entityManager;

    @Path("/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @RequiresUser
    public GroupView get(@PathParam("id") final long id) {
        GroupDAO dao = new GroupDAO(entityManager);

        UserDAO userDao = new UserDAO(entityManager);
        DBUser user = userDao.get(CRUDUtils.getCurrentUserId());

        boolean isMember = dao.checkIsMember(id, user);
        //CRUDUtils.checkPermission(new EitherAdminOrOwnerPermission(group.getOwner()));

        return new GroupView(dao.get(id));
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @RequiresUser
    public Set<GroupView> getGroups() {
        UserDAO userDao = new UserDAO(entityManager);
        DBUser user = userDao.get(CRUDUtils.getCurrentUserId());

        return user.getGroups().stream()
                .map(GroupView::new)
                .collect(Collectors.toSet());
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @RequiresUser
    public GroupView createGroup(final CreateGroupParam createGroupParam){
        UserDAO userDao = new UserDAO(entityManager);
        DBUser user = userDao.get(CRUDUtils.getCurrentUserId());

        GroupDAO dao = new GroupDAO(entityManager);
        DBGroup newGroup = dao.create(user, createGroupParam.getName());

        return new GroupView(newGroup);
    }

    @Path("/{id}/add/{userId}")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @RequiresUser
    public Response addUser(@PathParam("id") final long id, @PathParam("userId") final long userId){
        GroupDAO dao = new GroupDAO(entityManager);
        DBGroup group = dao.get(id);

        CRUDUtils.checkPermission(new EitherAdminOrOwnerPermission(group.getOwner()));

        UserDAO userDao = new UserDAO(entityManager);
        DBUser userToAdd = userDao.get(userId);
        group.getUsers().add(userToAdd);

        return Response.ok().build();
    }

    @Path("/{id}/remove/{userId}")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @RequiresUser
    public Response removeUser(@PathParam("id") final long id, @PathParam("userId") final long userId){
        GroupDAO dao = new GroupDAO(entityManager);
        DBGroup group = dao.get(id);

        CRUDUtils.checkPermission(new EitherAdminOrOwnerPermission(group.getOwner()));

        UserDAO userDao = new UserDAO(entityManager);
        DBUser userToAdd = userDao.get(userId);
        group.getUsers().remove(userToAdd);

        return Response.ok().build();
    }

    @Path("/{id}")
    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    @RequiresUser
    public Response deleteGroup(@PathParam("id") final long id){
        GroupDAO dao = new GroupDAO(entityManager);
        DBGroup group = dao.get(id);

        CRUDUtils.checkPermission(new EitherAdminOrOwnerPermission(group.getOwner()));

        dao.delete(id);

        return Response.ok().build();
    }
}
