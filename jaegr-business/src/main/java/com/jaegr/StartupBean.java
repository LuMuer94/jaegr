package com.jaegr;

import com.jaegr.DBNews;
import com.jaegr.daos.GroupDAO;
import com.jaegr.daos.UserDAO;
import com.jaegr.model.CreateUserParam;
import org.h2.engine.User;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Singleton
@Startup
public class StartupBean {

	@PersistenceContext
	private EntityManager entityManager;

//	@PostConstruct
//	public void startup() {
//
//	    final DBUser firstUserItem = this.entityManager.find(DBUser.class, 1L);
//		final DBNews firstNewsItem = this.entityManager.find(DBNews.class, 1L);
//		final DBNote firstNoteItem = this.entityManager.find(DBNote.class, 1L);
//
//
//		// only initialize once
//		if (firstNewsItem == null) {
//			final DBNews news = new DBNews();
//
//			news.setHeadline("Startup");
//			news.setContent("Startup Bean successfully executed");
//			news.setPublishedOn(new Date());
//
//			this.entityManager.persist(news);
//		}
//
//		if (firstUserItem == null && firstNoteItem == null) {
//		    final DBUser user = new DBUser();
//            final DBNote note = new DBNote();
//
//            Set<DBNote> notes;
//            user.setName("Start Up");
//            note.setTitle("StartUp");
//            note.setDate(new Date());
//            note.setUser(user);
//            note.setRecipients(null);
//            note.setPrivacy(false);
//
//            this.entityManager.persist(note);
//
//            notes = user.getNotes();
//            notes.add(note);
//            user.setNotes(notes);
//
//            this.entityManager.persist(user);
//        }
//
//	}

	@PostConstruct
	public void startup2(){
	    UserDAO userDao = new UserDAO(entityManager);
        GroupDAO groupDao = new GroupDAO(entityManager);

		//Creating users
        DBUser admin = userDao.create(new CreateUserParam("admin", "123"), true);
        DBUser user1 = userDao.create(new CreateUserParam("user1", "abc"), false);

        DBGroup group1 = groupDao.create(admin, "group1");
        DBGroup group2 = groupDao.create(user1, "group2");

        entityManager.flush();

        boolean b1 = groupDao.checkIsMember(group1.getId(), admin);
        boolean b2 = groupDao.checkIsMember(group1.getId(), user1);


		//Creating notes
		DBNote noteJson = new DBNote();
        noteJson.setDate(new Date());
        noteJson.setTitle("Json helper erweitern");
        noteJson.setContent("jo");
        noteJson.setUser(admin);
		entityManager.persist(noteJson);
	}

	@PreDestroy
	public void shutdown() {
		// potential cleanup work
	}
}
