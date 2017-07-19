import '../../model/group.dart';
import '../../model/note.dart';
import '../../model/user.dart';
import 'AbstractService.dart';
import 'dart:async';

import 'package:angular2/angular2.dart';

@Injectable()
class MockService extends AbstractService {

  static List<User> users = [
    new User("User1", 1, false, false),
    new User("User2", 2, false, false),
    new User("User3", 3, false, false),
    new User("User4", 4, false, false),
  ];

  static List<Group> groups = [
    new Group("Group1", 1, users[0])
  ];

  static List<Note> notes = notes = [
    new Note(users[0], "title1", "content", 1, 1, new DateTime.now()),
    new Note(users[1], "title2", "content", 1, 2, new DateTime.now()),
    new Note(users[2], "title3","content", 1, 3, new DateTime.now()),
    new Note(users[3], "title4", "content", 1, 4, new DateTime.now()),
    new Note(users[3], "title5", "content" , 1, 5, new DateTime.now()),
  ];

  User curUser = null;

  @override
  Future<Null> addUserToGroup(int groupId, int userId) {
    return getGroupById(groupId).then((g) {
      return getUserById(userId).then((u) => g.users.add(u));
    });
  }

  @override
  Future<Null> removeUserToGroup(int groupId, int userId) {
    return getGroupById(groupId).then((g) {
      return getUserById(userId).then((u) => g.users.remove(u));
    });
  }

  @override
  Future<Group> createGroup(String name) {
    return getCurrentUser().then((u) {
      int lastId = groups.last.id;
      Group g = new Group(name, lastId + 1, u);
      groups.add(g);
      return g;
    });
  }

  @override
  Future<Note> createNote(String title, String content, int groupId) {
    return getCurrentUser().then((u) {
      int lastId = notes.last.id;
      Note n = new Note(
          u, title, content, groupId, lastId + 1, new DateTime.now());
      notes.add(n);
      return n;
    });
  }

  @override
  Future<User> createUser(String username, String password) {
    int lastId = users.last.id;
    User u = new User(username, lastId + 1, false, false);
    users.add(u);
    return new Future.value(u);
  }

  @override
  Future<Null> deleteNote(int id) {
    notes.removeWhere((n) => n.id  == id);
    return new Future.value(null);
  }

  @override
  Future<User> getCurrentUser() {
    if(curUser != null) {
      return new Future.value(curUser);
    } else {
      return new Future.error("Not logged in");
    }
  }


  @override
  Future<Group> getGroupById(int id) {
    try {
      return new Future.value(groups.firstWhere((u) => u.id == id ));
    } catch(ex) {
      return new Future.error("No group found");
    }
  }

  @override
  Future<List<Group>> getGroupsByUser(int id) {
    return new Future.value(groups.where((g) => g.users.any((u) => u.id == id )).toList());
  }

  @override
  Future<List<Note>> getNotesByGroup(int groupId) {
    return new Future.value(notes.where((n) => n.groupId == groupId).toList());
  }


  @override
  Future<Note> getNoteById(int id) {
    try {
      return new Future.value(users.firstWhere((u) => u.id == id ));
    } catch(ex) {
      return new Future.error("No note found");
    }
  }

  @override
  Future<List<Note>> getNotesByUser(int userId) {
    return new Future.value(notes.where((n) => n.author.id == userId).toList());
  }

  @override
  Future<User> getUserById(int id) {
    try {
      return new Future.value(users.firstWhere((u) => u.id == id ));
    } catch(e) {
      return new Future.error("No user found");
    }
  }

  @override
  Future<Null> login(String username, String password) {
    try {
      User user =  users.firstWhere((u) => u.name == username );
      curUser = user;
      return new Future.value(null);
    } catch(e) {
      return new Future.error("No user not found for login");
    }
  }

  @override
  Future<Null> logout() {
    return getCurrentUser().then((u) {
      curUser = null;
    });
  }
  @override
  Future<Note> editNote(int id, String newTitle, String newContent) {
    return getNoteById(id).then((n) {
      n.title = newTitle;
      n.content = newContent;
      return n;
    });
  }
  @override
  Future<List<User>> searchUser(String likeName) {
    List<User> result = users.where((u) => u.name.startsWith(likeName)).toList();
    return new Future.value(result);
  }
}