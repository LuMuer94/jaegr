import 'package:angular2/angular2.dart';
import 'mock_group.dart';
import 'mock_notes.dart';
import 'mock_user.dart';
@Injectable()
class Service{
  getGroup(int groupId){return groups;}
  getNote(int noteId){return notes;}
  getUserTest() => users;
  getGroupsTest() => groups;
  getNotesTest() => notes;
}