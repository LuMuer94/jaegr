package com.jaegr;

import com.jaegr.auth.permission.IsGroupMemberPermission;
import com.jaegr.auth.permission.IsOwnerPermission;
import com.jaegr.daos.GroupDAO;
import com.jaegr.daos.UserDAO;
import com.jaegr.model.CreateGroupParam;
import com.jaegr.model.GroupView;
import org.apache.shiro.authz.Permission;
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
        CRUDUtils.checkPermission(new IsGroupMemberPermission(isMember));

        return new GroupView(dao.get(id));
    }

    @Path("/byUser/{userId}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @RequiresUser
    public Set<GroupView> getGroupsByUser(@PathParam("userId") final long userId) {
        UserDAO userDao = new UserDAO(entityManager);

        CRUDUtils.checkPermission(new IsOwnerPermission(userId));

        Set<DBGroup> groups = userDao.get(userId).getGroups();
        return groups.stream()
                .map(GroupView::new)
                .collect(Collectors.toSet());
    }

    @Path("/create")
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

        CRUDUtils.checkPermission(new IsOwnerPermission(group.getOwner()));

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

        CRUDUtils.checkAnyPermissions(new Permission[]{new IsOwnerPermission(userId), new IsOwnerPermission(group.getOwner())});

        //Owner can not be deleted
        if(group.getOwner().getId() == userId)
            throw new NotAcceptableException();

        UserDAO userDao = new UserDAO(entityManager);
        DBUser userToRemove = userDao.get(userId);
        group.getUsers().remove(userToRemove);

        return Response.ok().build();
    }

    @Path("/{id}")
    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    @RequiresUser
    public Response deleteGroup(@PathParam("id") final long id){
        GroupDAO dao = new GroupDAO(entityManager);
        DBGroup group = dao.get(id);

        CRUDUtils.checkPermission(new IsOwnerPermission(group.getOwner()));

        dao.delete(id);

        return Response.ok().build();
    }
}
