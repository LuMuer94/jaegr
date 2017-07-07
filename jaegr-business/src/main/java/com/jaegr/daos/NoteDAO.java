package com.jaegr.daos;
//TODO Delete Note -> user

import com.jaegr.DBGroup;
import com.jaegr.DBNote;
import com.jaegr.DBNote_;
import com.jaegr.DBUser;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.*;

/**
 * Created by leono on 13.06.2017.
 */
public class NoteDAO extends BaseDAO {


    /*TODO changed protected to public*/
    public NoteDAO(EntityManager entityManager) {
        super(entityManager);
    }

    /*
    Gibt stumpf alle Notes zurück die sich in der Datenbank befinden.
     */
    public List<DBNote> getList(){
        final CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
        final CriteriaQuery<DBNote> query = builder.createQuery(DBNote.class);

        final Root<DBNote> from = query.from(DBNote.class);

        List<DBNote> notes = this.entityManager.createQuery(query).getResultList();

        return notes;
    }

    public Set<DBNote> getNotesByGroup(long id){

        DBGroup group = getGroup(id);

        return group.getNotes();
    }


    public Set<DBNote> getNotesByUser(long id){

        DBUser user = getUser(id);

        return user.getNotes();
    }

    public DBNote createNote(DBNote param){
        final long id = param.getGroup().getId();
        DBGroup group = getGroup(id);
        DBUser user = param.getUser(); //Dart?
        DBNote note = new DBNote();

        note.setUser(user);
        note.setGroup(group);
        note.setTitle(param.getTitle());
        note.setTitle(param.getContent());
        note.setDate(new Date());

        this.entityManager.persist(note);

        Set<DBNote> notes = user.getNotes();
        notes.add(note);
        user.setNotes(notes);

        this.entityManager.merge(user);

        return note;
    }

    public DBNote editNote(long id, DBNote edit){
        DBNote note = entityManager.find(DBNote.class, id);

        if (note != null) {
            note.setTitle(edit.getTitle());
            note.setDate(new Date());
            entityManager.merge(note);
        }

        return note;

    }

    public DBNote deleteNote(long noteId) {
        DBNote note = this.entityManager.find(DBNote.class, noteId);

        if (note != null) {

            this.entityManager.remove(note);

        }

        return note;
    }

    /*
    public List<DBNote> getNotesByFriends(long id){

        List<DBNote> notes = null;
        Set<DBNote> candidates;

        DBUser user = get(id);

        Set<DBUser> friends = user.getFriends();
        for(DBUser friend : friends){
            candidates = friend.getNotes();
            for(DBNote note : candidates){
                // TODO Privacy?
                if(!note.isPrivacy() && note.getRecipients().contains(user)){
                    notes.add(note);
                }
            }
        }

        Collections.sort(notes, new Comparator<DBNote>() {
            public int compare(DBNote o1, DBNote o2) {
                if (o1.getDate() == null || o2.getDate() == null)
                    return 0;
                return o1.getDate().compareTo(o2.getDate());
            }
        });

        return notes;

    }
    */
    /*
        // Sort "maxResults" notes by date
        final CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
        final CriteriaQuery<DBNote> query = builder.createQuery(DBNote.class);

        final Root<DBNote> from = query.from(DBNote.class);

        final Order order = builder.desc(from.get(DBNote_.date));

        query.select(from).orderBy(order);

    */

    public DBUser getUser(long id) {

        DBUser user = entityManager.find(DBUser.class, id);

        if(user == null) {
            //ToDo: UserNotFoundException
        }

        return user;
    }

    public DBGroup getGroup(long id) {

        DBGroup group = entityManager.find(DBGroup.class, id);

        if(group == null) {
            //ToDo: GroupNotFoundException
        }

        return group;
    }
}
