import 'package:jaegr/model/group.dart';
import 'package:jaegr/model/user.dart';

User user1;
List<Group> groups = [
  new Group("Group1", 1, user1 = new User("user1", 1, false, false))
];
