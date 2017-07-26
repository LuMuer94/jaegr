import 'dart:async';
import 'package:angular2/angular2.dart';
import 'dart:html';
import 'package:jaegr/components/service/RestService.dart';
import 'package:jaegr/model/user.dart';
import 'package:jaegr/model/user_create_data.dart';

@Component(
  selector: 'register',
  templateUrl: 'register_component.html'
)

class Register{

  String username;
  String password;
  String checkPassword;

  final RestService restService;

  Register( this.restService );

  void clearInputs()
  {
    username="";
    password="";
    checkPassword="";
  }

  bool inputsAreValid(){
    return username!=null && password!=null && checkPassword!=null &&
    username.compareTo("")!=0 && password.compareTo("")!=0 && password.compareTo(checkPassword)==0;
  }

  Future registerUser(dynamic e) async {
    e.preventDefault();
    if( inputsAreValid() ){
      try {
        await restService.createUser(username, password);
        window.alert("Registration successful!\nYou should now be able to login with\nyour username and password");
        clearInputs();
      }
      catch( e ){
        window.alert("Registration failed!\nUsername might already be in use");
      }
    }
    else{
      window.alert( "Please enter a username and a password and\nre-enter your password correctly for checking!");
      checkPassword="";
    }
  }
}
