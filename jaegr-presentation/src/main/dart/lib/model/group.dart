import 'user.dart';
import 'note.dart';

class Group {
  String name;
  User owner;
  List<User> members;
  List<Note> notes;

  Group(this.name, this.owner)
  {
    members = new List<User>();
  }

  factory Group.fromJson(Map<String, dynamic>group) =>
      new Group(group['name'], group['owner']);

/*factory User.fromJson(Map<String, dynamic> hero) =>
      new User(_toInt(hero['id']), hero['name']);

  Map toJson() => {'id': id, 'name': name};

}

int _toInt(id) => id is int ? id : int.parse(id);

  */

}