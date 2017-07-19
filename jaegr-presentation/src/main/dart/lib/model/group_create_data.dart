import 'dart:convert';

class GroupCreateData {
  String name;

  GroupCreateData(this.name);

  String toJSON() {
    return JSON.encode({
      'name':name
    });
  }
}