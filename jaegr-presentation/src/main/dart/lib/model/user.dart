import 'dart:convert';
class User {
  String username;
  String password;

  String toJSON() {
    return JSON.encode({
      'username':username,
      'password':password
    });
  }

}