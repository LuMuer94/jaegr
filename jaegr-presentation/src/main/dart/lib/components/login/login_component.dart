import 'dart:async';
import 'package:angular2/angular2.dart';
import 'package:jaegr/components/service/MockService.dart';
import 'dart:html';
import 'package:jaegr/components/service/RestService.dart';
import 'package:jaegr/model/user.dart';
import 'package:jaegr/model/util.dart';

@Component(selector: 'login', templateUrl: 'login_component.html')

class Login implements OnInit {
  String username;
  String password;
  bool loggedIn;
  User user;


  final MockService restService;

  Login(this.restService);

  Future login(dynamic e) async {
    e.preventDefault();
    await restService.login(username, password);
    if (await Util.isloggedIn(restService)) {
      loggedIn = true;
    } else {
      loggedIn = false;
    }
  }

  void logout(dynamic e) {
    e.preventDefault();
    HttpRequest.request("../logout", method: "GET").then((request) {
      loggedIn = false;
      print(request.getAllResponseHeaders());
    }).catchError((n) => print(n));
  }

  @override
  Future<Null> ngOnInit() async {
    try {
      user = await restService.getCurrentUser();
    } catch (e) {}
    loggedIn = user != null;
  }

}
