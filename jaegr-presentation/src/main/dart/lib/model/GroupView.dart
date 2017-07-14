import 'UserView.dart';
class Group{
  String name;
  int id;
  User admin;
  List<User> members;
  Group (this.name, this.id, this.admin, this.members);
  Group.fromJson(Map json){
    this.name = json["name"];
    this.id = json["id"];
    this.admin = new User.fromJson(json["admin"]);
    this.members = json["users"].map((u) => new User.fromJson(u));

  }
}