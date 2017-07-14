
import 'package:jaegr/model/NoteView.dart';
import 'package:jaegr/model/UserView.dart';

final List<Note> notes = [
  new Note(new User("USer1", 1, false, false), "group1", 1, 1, new DateTime.now()),
  new Note(new User("USer2", 2, false, false), "group2", 2, 2, new DateTime.now()),
  new Note(new User("USer1", 3, false, false), "group1", 3, 3, new DateTime.now()),
  new Note(new User("USer4", 4, false, false), "group1", 4, 4, new DateTime.now()),
  new Note(new User("USer5", 5, false, false), "group5", 5, 5, new DateTime.now()),
];