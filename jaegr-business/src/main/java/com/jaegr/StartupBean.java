package com.jaegr;

import com.jaegr.DBNews;

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
		//Creating users
		DBUser leon = new DBUser();
		leon.setName("Leon");
		DBUser jonas = new DBUser();
		jonas.setName("Jonas");
		DBUser lukas = new DBUser();
		lukas.setName("Lukas");
		DBUser danny = new DBUser();
		danny.setName("Danny");
		//Adding friends
		HashSet<DBUser> friends = new HashSet<>();
		friends.add(jonas);
		friends.add(lukas);
		friends.add(danny);
		leon.setFriends(friends);

		friends = new HashSet<>();
		friends.add(leon);
		friends.add(lukas);
		friends.add(danny);
		jonas.setFriends(friends);

		friends = new HashSet<>();
		friends.add(jonas);
		friends.add(leon);
		friends.add(danny);
		lukas.setFriends(friends);

		friends = new HashSet<>();
		friends.add(jonas);
		friends.add(lukas);
		friends.add(leon);
		danny.setFriends(friends);

		//Creating notes
		DBNote json = new DBNote();
		json.setDate(new Date());
		json.setTitle("Json helper erweitern");
		json.setUser(danny);

		DBNote dart = new DBNote();
		dart.setDate(new Date());
		dart.setTitle("Dart interface planen");
		dart.setUser(danny);

		entityManager.persist(json);
		entityManager.persist(dart);

		if(!entityManager.contains(danny)){
			entityManager.persist(danny);
		}
		if(!entityManager.contains(jonas)){
			entityManager.persist(jonas);
		}
		if(!entityManager.contains(leon)){
			entityManager.persist(leon);
		}
		if(!entityManager.contains(lukas)){
			entityManager.persist(lukas);
		}

	}

	@PreDestroy
	public void shutdown() {
		// potential cleanup work
	}
}
