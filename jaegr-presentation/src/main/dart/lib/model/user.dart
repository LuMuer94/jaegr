import 'dart:convert';

class User {
  String name;
  int id;
  bool disabled;
  bool isAdmin;

  User(this.name, this.id, this.disabled, this.isAdmin);

  User.fromJson(Map json){
    this.id = json["id"];
    this.disabled = json["disabled"];
    this.name = json["name"];
    this.isAdmin = json["isAdmin"];
  }

  String toJSON() {
    return JSON.encode({
      'name': name,
      'id': id,
      'disabled': disabled,
      'isAdmin' : isAdmin
    });
  }
}