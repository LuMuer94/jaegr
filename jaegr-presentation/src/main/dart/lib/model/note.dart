import 'user.dart';
class Note{
  User author;
  String title;
  String content;
  String groupName;
  DateTime date;
  int groupId;
  int id;


  Note( this.author, this.title, this.content, this.groupId, this.id, this.date);


  Note.fromJson(Map json){
    this.author= new User.fromJson(json["owner"]);
    this.title= json["title"];
    this.content = json["content"];
    this.groupName = json ["groupName"];
    this.date = json ["date"];
    this.groupId = json ["groupId"];
    this.id = json ["id"];
  }
}