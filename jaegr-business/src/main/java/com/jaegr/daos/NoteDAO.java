package com.jaegr.daos;

import com.jaegr.DBGroup;
import com.jaegr.DBNote;
import com.jaegr.DBNote_;
import com.jaegr.DBUser;
import com.jaegr.model.CreateNoteParam;
import com.jaegr.model.EditNoteParam;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.ws.rs.NotFoundException;
import java.util.*;

/**
 * Created by leono on 13.06.2017.
 */
public class NoteDAO extends BaseDAO {
    public NoteDAO(EntityManager entityManager) {
        super(entityManager);
    }

    public DBNote get(long id) {
        DBNote note = entityManager.find(DBNote.class, id);
        if(note == null) {
            throw new NotFoundException();
        }

        return note;
    }

    public Set<DBNote> getNotesByGroup(long groupId){
        GroupDAO groupDao = new GroupDAO(entityManager);
        return groupDao.get(groupId).getNotes();
    }

    public DBNote createNote(DBUser user, CreateNoteParam param){
        GroupDAO groupDao = new GroupDAO(entityManager);
        DBGroup group = groupDao.get(param.getGroupId());

        DBNote note = new DBNote();
        note.setGroup(group);
        note.setUser(user);
        note.setTitle(param.getTitle());
        note.setContent(param.getContent());
        note.setDate(new Date());

        this.entityManager.persist(note);

        return note;
    }

    public DBNote editNote(long id, EditNoteParam editNoteParam){
        DBNote note = get(id);

        String newTitle = editNoteParam.getTitle();
        if(newTitle != null && !newTitle.isEmpty())
            note.setTitle(newTitle);

        String newContent = editNoteParam.getContent();
        if(newContent != null && !newContent.isEmpty())
            note.setContent(newContent);

        return note;
    }

    public void deleteNote(long id) {
        DBNote note = get(id);
        entityManager.remove(note);
    }
}
