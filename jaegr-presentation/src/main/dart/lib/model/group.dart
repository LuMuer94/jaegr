import 'user.dart';

class Group {
  String name;
  int id;
  User admin;
  List<User> users;

  Group( this.name, this.id, this.admin,)
  {
    users = new List<User>();
    users.add(admin);
  }


  Group.fromJson(Map json){
    this.name = json["name"];
    this.id = json["id"];
    this.admin= new User.fromJson(json["admin"]);
  }

  static List<Group> fromJsonList(List json) {
    return json.map((u) => new Group.fromJson(u));
  }

}