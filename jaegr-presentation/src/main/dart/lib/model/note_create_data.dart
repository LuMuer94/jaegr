import 'dart:convert';

class NoteCreateData {
  String title;
  String content;
  int groupId;

  NoteCreateData(this.title, this.content, this.groupId);

  String toJSON() {
    return JSON.encode({
      'title': title,
      'content': content,
      'groupId': groupId
    });
  }
}