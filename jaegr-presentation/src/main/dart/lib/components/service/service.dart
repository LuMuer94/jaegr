import 'package:angular2/angular2.dart';
import 'mock_group.dart';
import 'mock_notes.dart';
import 'mock_user.dart';
@Injectable()
class Service{
  getGroup(int groupId) => groups.firstWhere((g) => g.id == groupId, orElse: () => null);
  getNote(int noteId) => notes.firstWhere((n) => n.id == noteId, orElse:  () => null);
  getUser(int userId) => users.firstWhere((u) => u.id == userId, orElse:  () => null);
  getUserTest() => users;
  getGroupsTest() => groups;
  getNotesTest() => notes;
}