package com.jaegr;

    /*
    TODO ++  Create GroupCRUD ++ CRUD Funktionen für Notes, User und Group implementieren
     */
import com.jaegr.daos.NoteDAO;
import com.jaegr.daos.UserDAO;
import com.jaegr.model.NoteView;
import com.jaegr.model.UserView;
import org.json.simple.JSONObject;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
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
    @Produces(MediaType.APPLICATION_JSON)
    public JSONObject getNotes(){
        NoteDAO dao = new NoteDAO(entityManager);
        List<DBNote> notes =  dao.getList();
        JsonHelper jsonHelper = new JsonHelper();
        return jsonHelper.toJSonNotes(notes);

    }
    /*
    Methode um (zu testzwecken) alle gespeicherten Notes ausgeben zu lassen.
     */
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getNoteByUser(@PathParam("id") final long id){

        NoteDAO dao = new NoteDAO(entityManager);

        Set<NoteView> notesByUser = dao.getNotesByUser(id).stream()
                .map(u -> new NoteView(u))
                .collect(Collectors.toSet());

        return Response.ok(notesByUser).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getNoteByGroup(@PathParam("id") final long id){

        NoteDAO dao = new NoteDAO(entityManager);

        Set<NoteView> notesByGroup = dao.getNotesByGroup(id).stream()
                .map(u -> new NoteView(u))
                .collect(Collectors.toSet());

        return Response.ok(notesByGroup).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(final DBNote param) {

        NoteDAO dao = new NoteDAO(entityManager);

        DBNote note = dao.createNote(param);

        return Response.ok(new NoteView(note)).build();
    }

    @POST
    @Path("/{id}")
    @Consumes({ MediaType.APPLICATION_JSON })
    @Produces({ MediaType.APPLICATION_JSON})
    public Response update(@PathParam("id") final long noteId, DBNote edit){

        NoteDAO dao = new NoteDAO(entityManager);

        DBNote note = dao.editNote(noteId, edit);

        return Response.ok(new NoteView(note)).build();
    }


    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") final long id){

        NoteDAO dao = new NoteDAO(entityManager);

        DBNote note = dao.deleteNote(id);

        return Response.ok(new NoteView(note)).build();
    }
    //ToDo:
    /*
    create(groupId)
    update(change content)
    delete(check owner) -> Shiro?

    get notes by user
    get notes by groups
    get

    noteview ->
     */


}
