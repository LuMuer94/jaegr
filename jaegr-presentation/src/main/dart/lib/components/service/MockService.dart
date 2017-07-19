import '../../model/group.dart';
import '../../model/note.dart';
import '../../model/user.dart';
import 'AbstractService.dart';
import 'dart:async';

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
  
  @override
  Future<Null> addUserToGroup(int groupId, int userId) {
    return new Future.error("Can not add user to group");
  }

  @override
  Future<Group> createGroup(String name) {
    return new Future.error("Can not create group");
  }

  @override
  Future<Note> createNote(String title, String content, int groupId) {
    return new Future.error("Can not create note");
  }

  @override
  Future<User> createUser(String username, String password) {
    return new Future.error("Can not create user");
  }

  @override
  Future<Null> deleteNote(int id) {
    return new Future.error("Can not delete note");
  }

  @override
  Future<User> getCurrentUser() {
    return new Future.value(users[0]);
  }


  @override
  Future<Null> removeUserToGroup(int groupId, int userId) {
    return new Future.error("Can not remove user from group");
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
    return new Future.value(notes.where((n) => n.groupdId == groupId).toList());
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
    } catch(ex) {
      return new Future.error("No user found");
    }
  }

  @override
  Future<Null> login(String username, String password) {
    if(users.any((u) => u.username == username)) {
      return new Future.value(null);
    } else {
      return new Future.error("No user not found for login");
    }
  }
}