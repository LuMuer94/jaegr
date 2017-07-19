import 'package:angular2/angular2.dart';
import 'mock_group.dart';
import 'mock_notes.dart';
import 'mock_user.dart';
import 'dart:async';
import 'package:jaegr/model/group.dart';
import 'package:jaegr/model/note.dart';
import 'package:jaegr/model/user.dart';

@Injectable()
class Service{
  /*
  Future<Group> getGroup(int groupId) async{
    return groups.firstWhere((g) => g.id == groupId, orElse: () => null);
  }
  Future<Note> getNote(int noteId) async{
    return notes.firstWhere((n) => n.id == noteId, orElse:  () => null);
  }
  Future<User> getUser(int userId) async{
    return users.firstWhere((u) => u.id == userId, orElse:  () => null);
  }
  Future<List<User>> getUserTest() async{
    return users;
  }
 Future<List<Group>> groupsTest() async {
   return groups;
 }
  Future<List<Note>> getNotesTest() async{
    return notes;
  }
  Future<List<Group>> getGroupsByUser(User user) async{
    List<Group> groups = await groupsTest();
    groups.retainWhere((u) => u.members.contains( user ));
    return groups;
  }
  */
  getGroup(int groupId) => groups.firstWhere((g) => g.id == groupId, orElse: () => null);
  getNote(int noteId) => notes.firstWhere((n) => n.id == noteId, orElse:  () => null);
  getUser(int userId) => users.firstWhere((u) => u.id == userId, orElse:  () => null);
  getUserTest() => users;
  List<Group> getGroupsTest() => groups;
  getNotesTest() => notes;

}