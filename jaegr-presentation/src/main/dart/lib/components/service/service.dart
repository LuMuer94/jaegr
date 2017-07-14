import 'package:angular2/angular2.dart';
import 'mock_group.dart';
import 'mock_notes.dart';
import 'mock_user.dart';
@Injectable()
class Service{
  getUser() => users;
  getGroups() => groups;
  getNotes() => notes;
}