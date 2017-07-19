import 'User.dart';
class Note{
  User author;
  String title;
  String content;
  String groupName;
  int groupId;
  int id;


  private DBUser user;
  private String title;
  private String content;
  private Date date;
  private DBGroup group;

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