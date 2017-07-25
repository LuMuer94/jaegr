import 'dart:async';
import 'package:angular2/angular2.dart';
import 'package:jaegr/components/service/MockService.dart';
import 'package:jaegr/model/group.dart';
import 'package:jaegr/model/note.dart';
import 'package:jaegr/model/user.dart';

@Injectable()
class Context{
  bool loggedIn;
  bool creatingGroup;
  bool addingUser;
  Group selectedGroup;
  bool addingNote;
  bool deleteNote;
}