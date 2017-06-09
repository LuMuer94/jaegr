import 'dart:convert';
class News {
  int id;
  String content;
  String headline;
  DateTime publishedOn;

  String toJSON() {
    return JSON.encode({
      'content':content,
      'headline':headline
    });
  }

}