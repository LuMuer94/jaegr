import 'user.dart';
import 'note.dart';

class Group {
  String name;
  int id;
  User owner;
  List<Note> notes;
  List<User> members;

  Group( this.name, this.id, this.owner,)
  {
    notes = new List<Note>();
    members = new List<User>();
    members.add(owner);
  }

  Group.fromJson(Map json){
    this.name = json["name"];
    this.id = json["id"];
    this.owner= new User.fromJson(json["owner"]);
    this.notes = json["notes"].map((u) => new Note.fromJson(u));
    this.members = json["members"].map((u) => new User.fromJson(u));
  }

}