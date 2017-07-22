package com.jaegr;

import com.jaegr.auth.permission.IsGroupMemberPermission;
import com.jaegr.auth.permission.IsOwnerPermission;
import com.jaegr.daos.GroupDAO;
import com.jaegr.daos.NoteDAO;
import com.jaegr.daos.UserDAO;
import com.jaegr.model.*;
import org.apache.shiro.authz.annotation.RequiresUser;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by Leon on 09.06.2017.
 */
@Path("/notes")
@Transactional
public class NoteCRUD {

    @PersistenceContext
    private EntityManager entityManager;

    @GET
    @Path("/byUser/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @RequiresUser
    public Set<NoteView> getNotesByUser(@PathParam("id") final long userId){
        UserDAO dao = new UserDAO(entityManager);
        DBUser user = dao.get(userId);

        CRUDUtils.checkPermission(new IsOwnerPermission(user));

        return user.getNotes().stream()
                .map(NoteView::new)
                .collect(Collectors.toSet());
    }

    @GET
    @Path("/byGroup/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @RequiresUser
    public Set<NoteView> getNotesByGroup(@PathParam("id") final long groupId){
        NoteDAO dao = new NoteDAO(entityManager);

        UserDAO userDao = new UserDAO(entityManager);
        DBUser user = userDao.get(CRUDUtils.getCurrentUserId());

        GroupDAO groupDao = new GroupDAO(entityManager);
        boolean isMember = groupDao.checkIsMember(groupId, user);
        CRUDUtils.checkPermission(new IsGroupMemberPermission(isMember));

        return dao.getNotesByGroup(groupId).stream()
                .map(NoteView::new)
                .collect(Collectors.toSet());
    }

    @Path("/create")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @RequiresUser
    public NoteView create(final CreateNoteParam param) {
        UserDAO userDao = new UserDAO(entityManager);
        DBUser user = userDao.get(CRUDUtils.getCurrentUserId());

        GroupDAO groupDao = new GroupDAO(entityManager);
        boolean isMember = groupDao.checkIsMember(param.getGroupId(), user);
        CRUDUtils.checkPermission(new IsGroupMemberPermission(isMember));

        NoteDAO dao = new NoteDAO(entityManager);
        DBNote note = dao.createNote(user, param);
        return new NoteView(note);
    }

    @POST
    @Path("/{id}/edit")
    @Consumes({ MediaType.APPLICATION_JSON })
    @Produces({ MediaType.APPLICATION_JSON})
    @RequiresUser
    public NoteView edit(@PathParam("id") final long id, EditNoteParam param){
        NoteDAO dao = new NoteDAO(entityManager);
        DBNote note = dao.get(id);

        CRUDUtils.checkPermission(new IsOwnerPermission(note.getUser()));

        note = dao.editNote(id, param);
        return new NoteView(note);
    }


    @DELETE
    @Path("/{id}")
    @RequiresUser
    public Response delete(@PathParam("id") final long id){
        NoteDAO dao = new NoteDAO(entityManager);
        DBNote note = dao.get(id);

        CRUDUtils.checkPermission(new IsOwnerPermission(note.getUser()));

        dao.deleteNote(id);
        return Response.ok().build();
    }
}
