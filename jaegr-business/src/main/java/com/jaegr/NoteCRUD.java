package com.jaegr;

    /*
    TODO ++  Create GroupCRUD ++ CRUD Funktionen für Notes, User und Group implementieren
     */
import com.jaegr.daos.NoteDAO;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Date;
import java.util.Set;

/**
 * Created by Leon on 09.06.2017.
 */
@Path("/notes")
@Transactional
public class NoteCRUD {

    @PersistenceContext
    private EntityManager entityManager;

    NoteDAO noteDAO;

    @Path("/{id}")
    @GET
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    public DBNote getNoteByID(@PathParam("id") final long id){
        DBNote noteByID;

        noteByID = this.entityManager.find(DBNote.class, id);

        if(noteByID == null){
            /*
            TODO NoteNotFoundException
             */
        }
        return noteByID;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(final DBNote param) {
        /*
        DBNote note = noteDAO.createNote(param);
        */

        final DBNote note = new DBNote();

        note.setUser(param.getUser());
        note.setGroup(param.getGroup());
        note.setTitle(param.getTitle());
        note.setDate(new Date());

        this.entityManager.persist(note);

        return Response.ok(note).build();
    }

    @POST
    @Consumes({ MediaType.APPLICATION_JSON })
    @Produces({ MediaType.APPLICATION_JSON})
    @Path("/{id}")
    public Response update(@PathParam("id") final long id, DBNote edit){
        DBNote note;
        // TODO group edit?

        edit.setId(id);

        note = entityManager.find(DBNote.class, id);

        if (note != null) {
            note.setTitle(edit.getTitle());
            /*TODO Date for edit?*/
            entityManager.merge(note);
        }
        /*
        note = noteDAO.editNote(id, edit);
        */
        return Response.ok(note).build();
    }


    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") final long id){
        /*
        DBNote note;
        note = noteDAO.deleteNote(id);
        */
        DBNote note;

        note = this.entityManager.find(DBNote.class, id);

        if (note != null) {
            this.entityManager.remove(note);
        }

        return Response.ok(note).build();
    }
}
