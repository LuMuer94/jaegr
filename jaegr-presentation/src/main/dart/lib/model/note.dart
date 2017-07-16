import 'dart:convert';

import 'package:jaegr/model/group.dart';
import 'package:jaegr/model/user.dart';

class Note {
  //int id;
  User user;
  String title;
  String content;
  DateTime date;
  Group group;


  String toJSON() {
    return JSON.encode({
      'user':user,
      'title':title,
      'content':content,
      'date':date,
      'group':group
    });
  }

}