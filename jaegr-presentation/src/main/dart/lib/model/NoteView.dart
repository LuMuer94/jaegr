import 'UserView.dart';
class Note{
  User owner;
  String groupName;
  int groupId;
  int id;
  DateTime date;
  Note(this.owner, this.groupName, this.groupId, this.id, this.date);
  Note.fromJson(Map json){
    this.owner = json["owner"];
    this.groupName = json ["groupName"];
    this.groupId = json ["groupId"];
    this.id = json ["id"];
    this.date = json ["date"];
  }
}