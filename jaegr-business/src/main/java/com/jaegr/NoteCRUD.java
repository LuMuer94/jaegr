package com.jaegr;

    /*
    TODO ++  Create GroupCRUD ++ CRUD Funktionen für Notes, User und Group implementieren
     */
import com.jaegr.daos.NoteDAO;
import org.json.simple.JSONObject;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Set;

/**
 * Created by Leon on 09.06.2017.
 */
@Path("/notes")
@Transactional
public class NoteCRUD {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public JSONObject getNotes(){
        NoteDAO dao = new NoteDAO(entityManager);
        List<DBNote> notes =  dao.getList();
        JsonHelper jsonHelper = new JsonHelper();
        return jsonHelper.toJSonNotes(notes);

    }

    @PersistenceContext
    private EntityManager entityManager;

    /*
    Methode um (zu testzwecken) alle gespeicherten Notes ausgeben zu lassen.
     */
    @Path("/{id}")
    @GET
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getNoteByUser(@PathParam("id") final long id){

        NoteDAO dao = new NoteDAO(entityManager);

        Set<DBNote> notesByUser = dao.getNotesByUser(id);

        return Response.ok(notesByUser).build();
    }

    //TODO: wie wird getNotesByCriteria im GET-request aufgerufen? Wo kommen die parameter maxresult und keyword her?
    //Das selbe bei den anderen methoden.


    @Path("/{id}")
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getNotesByCriteria(@PathParam("id") final long id, final int maxResults, final String keyword){

        NoteDAO dao = new NoteDAO(entityManager);

        Set<DBNote> notes = dao.getNotesByCriteria(id, maxResults, keyword);

        return Response.ok(notes).build();
    }

    /* // TODO alternative Path for keyword?
    @Path("/{id}/{keyword}")
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getNotesByKeyword(@PathParam("id") final long id, @PathParam("keyword") final String keyword, final int maxResults){

        NoteDAO dao = new NoteDAO(entityManager);

        List<DBNote> notes = dao.getNotesByKeyword(id, maxResults, keyword);

        return Response.ok(notes).build();
    }
    */

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(final DBNote param) {

        NoteDAO dao = new NoteDAO(entityManager);

        DBNote note = dao.createNote(param);

        return Response.ok(note).build();
    }

    @POST
    @Consumes({ MediaType.APPLICATION_JSON })
    @Produces({ MediaType.APPLICATION_JSON})
    @Path("/{id}")
    public Response update(@PathParam("id") final long id, DBNote edit){

        NoteDAO dao = new NoteDAO(entityManager);

        DBNote note = dao.editNote(id, edit);

        return Response.ok(note).build();
    }


    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") final long id){

        NoteDAO dao = new NoteDAO(entityManager);

        DBNote note = dao.deleteNote(id);

        return Response.ok(note).build();
    }

    @DELETE
    @Path("/{forbidden}")
    public Response deleteAllNotesContaining(@PathParam("forbidden") final String forbidden){

        NoteDAO dao = new NoteDAO(entityManager);

        List<DBNote> deletedNotes = dao.deleteAllNotesContaining(forbidden);

        return Response.ok(deletedNotes).build();
    }
}
