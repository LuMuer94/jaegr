import 'dart:convert';

class UserSearchData{
  String likeName;

  UserSearchData(this.likeName);

  String toJSON() {
    return JSON.encode({
      'likeName':likeName
    });
  }

}