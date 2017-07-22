import 'dart:convert';

class UserCreateData{
  String name;
  String password;

  UserCreateData(this.name, this.password);

  String toJSON() {
    return JSON.encode({
      'name':name,
      'password':password
    });
  }

}