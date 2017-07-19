import 'dart:convert';

class UserCreateData{
  String name;
  String password;

  String toJSON() {
    return JSON.encode({
      'name':name,
      'password':password
    });
  }

}