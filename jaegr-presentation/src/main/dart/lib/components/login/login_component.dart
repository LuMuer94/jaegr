import 'dart:async';
import 'package:angular2/angular2.dart';
import 'dart:html';
import 'package:angular2/router.dart';
import 'package:jaegr/components/service/MockService.dart';
import 'package:jaegr/components/service/RestService.dart';
import 'package:jaegr/components/shared/context.dart';
import 'package:jaegr/model/user.dart';
import 'package:jaegr/model/util.dart';

@Component(selector: 'login', templateUrl: 'login_component.html')

class Login implements OnInit {
  String username;
  String password;
  User user;

  final MockService restService;
  final Router _router;
  final Context context;

  Login(this.restService,this._router, this.context);

  isLoggedIn(){
    return context.loggedIn;
  }

  //TODO: optional user messages
  Future login(dynamic e) async {
    e.preventDefault();
    await restService.login(username, password);
    if( context.loggedIn = await Util.isloggedIn(restService) ){
      user= await restService.getCurrentUser();
      print( "login succesfull");
      _router.navigate(['UserView']);
    }
    else{
      print( "Login failed");
    }
  }

  Future logout(dynamic e) async{
    e.preventDefault();
    await restService.logout();
    if( context.loggedIn = await Util.isloggedIn(restService) ){
      print( "logout failed");
    }
    else{
      print("logout successfull");
    }
  }


  @override
  Future<Null> ngOnInit() async {
    try {
      user = await restService.getCurrentUser();
    } catch (e) {}
    context.loggedIn = user != null;
  }

}
