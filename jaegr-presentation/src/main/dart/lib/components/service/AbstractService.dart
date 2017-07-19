import 'dart:async';
import 'package:jaegr/model/group.dart';
import 'package:jaegr/model/note.dart';
import 'package:jaegr/model/user.dart';
abstract class AbstractService{
  Future<User> getCurrentUser();
  Future<User> getUserById(int id);
  Future<User> createUser(String username, String password);
  Future<Null> login(String username, String password);

  Future<Group> getGroupById(int id);
  Future<List<Group>> getGroupsByUser(int id);
  Future<Group> createGroup(String name);
  Future<Null> addUserToGroup(int groupId, int userId);
  Future<Null> removeUserToGroup(int groupId, int userId);

  Future<List<Note>> getNotesByUser(int userId);
  Future<List<Note>> getNOtesByGroup(int groupId);
  Future<Note> getNoteById(int id);
  Future<Note> createNote(String title, String content, int groupId);
  Future<Null> deleteNote(int id);

}