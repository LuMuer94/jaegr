import 'dart:async';
import 'package:angular2/angular2.dart';
import 'package:jaegr/components/service/MockService.dart';
import 'package:jaegr/model/user.dart';

@Injectable()
class Context{
  bool loggedIn;
  bool creatingGroup;
}