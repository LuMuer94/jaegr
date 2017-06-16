package com.jaegr.daos;


import com.jaegr.DBNote;
import com.jaegr.DBUser;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.Date;
import java.util.Set;

/**
 * Created by leono on 13.06.2017.
 */
public class NoteDAO extends BaseDAO {


    /*TODO changed protected to public*/
    public NoteDAO(EntityManager entityManager) {
        super(entityManager);
    }

    /*
    public Set<DBNote> getNotesByDate(Date date){

        final CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
        final CriteriaQuery<DBNote> query = builder.createQuery(DBNote.class);
        final Root<DBNote> from = query.from(DBNote.class);

        query.where(builder.equal(from.get(DBNote_.date), date));
        entityManager.createQuery(query).getResultList();

    }
    */

    public DBNote createNote(DBNote param){
        /*TODO Validate input?*/
        final DBNote note = new DBNote();

        note.setUser(param.getUser());
        note.setGroup(param.getGroup());
        note.setTitle(param.getTitle());
        note.setDate(new Date());

        this.entityManager.persist(note);

        return note;
    }

    public DBNote editNote(long id, DBNote edit){
        DBNote note;

        note = entityManager.find(DBNote.class, id);

        if (note != null) {
            note.setTitle(edit.getTitle());
            /*TODO Date for edit?*/
            entityManager.merge(note);
        }

        return note;
    }

    public DBNote deleteNote(long id) {

        DBNote note = entityManager.find(DBNote.class, id);

        if (note != null) {
            entityManager.remove(note);
        }
        return note;
    }

    public Set<DBNote> deleteAllNotesContaining(){
        return null;
    }

}
