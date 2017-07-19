import 'package:angular2/angular2.dart';
import 'dart:html';

@Component(
    selector: 'login',
    templateUrl: 'login_component.html'
)
class Login implements OnInit{

  String username;
  String password;
  bool loggedIn;

  bool isLoggedIn() => loggedIn;

  void login(dynamic e){
    e.preventDefault();
    HttpRequest.postFormData("../login.jsp", { "username" : username, "password" : password })
      .then((request) {loggedIn = true;print(request.getAllResponseHeaders());})
      .catchError((n)=>print(n));
  }

  void logout(dynamic e){
    e.preventDefault();
    HttpRequest.request("../logout", method: "GET")
      .then((request) {loggedIn = false; print(request.getAllResponseHeaders());})
      .catchError((n)=>print(n));
  }


  @override
  ngOnInit() {
    // TODO: implement ngOnInit correctly
    if( loggedIn == null)
      {
        loggedIn=false;
      }
  }
}
