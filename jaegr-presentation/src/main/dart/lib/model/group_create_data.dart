import 'dart:convert';

class GroupCreateData {
  String name;

  String toJSON() {
    return JSON.encode({
      'name':name
    });
  }
}