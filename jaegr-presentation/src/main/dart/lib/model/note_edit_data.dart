import 'dart:convert';

class NoteEditData {
  String title;
  String content;

  NoteEditData(this.title, this.content);

  String toJSON() {
    return JSON.encode({
      'title': title,
      'content': content,
    });
  }
}