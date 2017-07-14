import 'package:angular2/angular2.dart';
import 'dart:html';
import 'package:jaegr/model/user.dart';

@Component(
  selector: 'register',
  templateUrl: 'register_component.html'
)

class Register{

  User model;

  CreateNews(){
    model = new User();
  }

  void registerUser(dynamic e){
    e.preventDefault();
    var requestHeaders = {
      'Content-Type':'application/json',
      'Accept':'application/json'
    };
    HttpRequest.request("../rest/register",method: "POST",sendData: model.toJSON(),requestHeaders: requestHeaders).catchError((n)=>print(n));

  }
}
