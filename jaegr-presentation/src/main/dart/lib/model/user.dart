import 'package:jaegr/model/group.dart';

class User {
  String name;
  int id;
  bool disabled;
  bool isAdmin;

  User(this.name, this.id, this.disabled, this.isAdmin);

  @override
  equals( User other){
    if( other != null ){
      return this.id == other.id;
    }
    return false;
  }

  isMemberOf( Group g){
    if( g!=null){
      try{
        User user = g.users.singleWhere( (u) => u.equals(this) );
        return user != null;
      }
      catch( e ){}
    }
    return false;
  }

  User.fromJson(Map json){
    this.id = json["id"];
    this.disabled = json["disabled"];
    this.name = json["name"];
    this.isAdmin = json["admin"];
  }

  static List<User> fromJsonList(List json) {
    return json.map((u) => new User.fromJson(u)).toList();
  }
}

