package com.jaegr.daos;
//TODO Delete Note -> user

import com.jaegr.DBNote;
import com.jaegr.DBNote_;
import com.jaegr.DBUser;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
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

    public DBNote createNote(DBNote param){

        final DBNote note = new DBNote();
        /*TODO Session ID?! */
        /*TODO Validate input*/
        note.setUser(param.getUser());
        note.setRecipients(param.getRecipients());
        note.setTitle(param.getTitle());
        note.setDate(new Date());
        //TODO Enum Privacy


        this.entityManager.persist(note);

        Set<DBNote> notes = param.getUser().getNotes();
        notes.add(note);
        param.getUser().setNotes(notes);

        return note;
    }

    public DBNote editNote(long id, DBNote edit){

        DBNote note;

        edit.setId(id);

        note = entityManager.find(DBNote.class, id);

        if (note != null) {
            note.setTitle(edit.getTitle());
            note.setDate(new Date());
            entityManager.merge(note);
        }

        return note;

    }

    public DBNote deleteNote(long id) {

        DBNote note;

        note = this.entityManager.find(DBNote.class, id);

        if (note != null) {

            removeNotesFromUser(note);

            this.entityManager.remove(note);

        }

        return note;
    }

    public List<DBNote> deleteAllNotesContaining(String forbidden) {


        final CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
        final CriteriaQuery<DBNote> query = builder.createQuery(DBNote.class);

        final Root<DBNote> from = query.from(DBNote.class);

        query.where(builder.equal(from.get(DBNote_.title), forbidden));
        List<DBNote> notes = this.entityManager.createQuery(query).getResultList();

        for(DBNote note : notes){

            removeNotesFromUser(note);

            this.entityManager.remove(note);
        }

        return notes;
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

    public Set<DBNote> getNotesByCriteria(long id, int maxResults, String keyword){

        List<DBNote> candidates;

        DBUser user = get(id);

        Set<DBUser> friends = user.getFriends();

        // Sort "maxResults" notes by date
        final CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
        final CriteriaQuery<DBNote> query = builder.createQuery(DBNote.class);

        final Root<DBNote> from = query.from(DBNote.class);

        final Order order = builder.desc(from.get(DBNote_.date));

        query.select(from).orderBy(order);

        candidates = this.entityManager.createQuery(query).setMaxResults(maxResults).getResultList();

        // Select notes:
        // + directed to the user (id)  + written by a friend + title contains keyword
        Set<DBNote> selection = null;
        // TODO PRIVACY
        for(DBNote candidate : candidates){
            if(friends.contains(candidate.getUser())){
                if(candidate.getRecipients().contains(user)){
                    if(candidate.getTitle().contains("keyword")){
                        selection.add(candidate);
                    }
                }
            }
        }

        return selection;
    }
    /*
    public Set<DBNote> getNotesByFriends(long id, int maxResults){

        List<DBNote> candidates;

        DBUser user = get(id);

        Set<DBUser> friends = user.getFriends();

        // Sort "maxResults" notes by date
        final CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
        final CriteriaQuery<DBNote> query = builder.createQuery(DBNote.class);

        final Root<DBNote> from = query.from(DBNote.class);

        final Order order = builder.desc(from.get(DBNote_.date));

        query.select(from).orderBy(order);

        candidates = this.entityManager.createQuery(query).setMaxResults(maxResults).getResultList();

        // Select notes:
        // + directed to the user (id)  + written by a friend
        Set<DBNote> selection = null;
        // TODO PRIVACY
        for(DBNote candidate : candidates){
            if(friends.contains(candidate.getUser())){
                if(candidate.getRecipients().contains(user)){
                    selection.add(candidate);
                }
            }
        }

        return selection;
    }
    */

    public DBUser get(long id) {

        DBUser user = entityManager.find(DBUser.class, id);

        if(user == null) {
            //ToDo: UserNotFoundException
        }

        return user;
    }

    public void removeNotesFromUser(DBNote note){
        Set<DBNote> notes;
        DBUser user;

        user = note.getUser();
        notes = user.getNotes();
        notes.remove(note);
        user.setNotes(notes);
    }

    public Set<DBNote> getNotesByUser(long id){

        DBUser user = get(id);

        return user.getNotes();
    }

}
