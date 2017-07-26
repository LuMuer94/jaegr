import '../../model/group.dart';
import '../../model/note.dart';
import '../../model/user.dart';
import 'dart:async';

abstract class AbstractService{
  Future<User> getCurrentUser();
  Future<User> getUserById(int id);
  Future<User> createUser(String username, String password);
  Future<List<User>> searchUser(String likeName);

  Future<Null> login(String username, String password);
  Future<Null> logout();

  Future<Group> getGroupById(int id);
  Future<List<Group>> getGroupsByUser(int id);
  Future<Group> createGroup(String name);
  Future<Null> addUserToGroup(int groupId, int userId);
  Future<Null> removeUserFromGroup(int groupId, int userId);

  Future<List<Note>> getNotesByUser(int userId);
  Future<List<Note>> getNotesByGroup(int groupId);
  Future<Note> getNoteById(int id);
  Future<Note> createNote(String title, String content, int groupId);
  Future<Null> deleteNote(int id);
  Future<Note> editNote(int id, String newTitle, String newContent);
}