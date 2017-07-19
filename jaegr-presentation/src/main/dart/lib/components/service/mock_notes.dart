
import 'package:jaegr/model/note.dart';
import 'package:jaegr/model/user.dart';

final List<Note> notes = [
  new Note(new User("USer1", 1, false, false), "title1", "content",1, 1, new DateTime.now()),
  new Note(new User("USer2", 2, false, false), "title2", "content",2, 2, new DateTime.now()),
  new Note(new User("USer1", 3, false, false), "title1","content", 3, 3, new DateTime.now()),
  new Note(new User("USer4", 4, false, false), "title1", "content", 4, 4, new DateTime.now()),
  new Note(new User("USer5", 5, false, false), "title", "content" ,5, 5, new DateTime.now()),
];