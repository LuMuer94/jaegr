import 'package:angular2/angular2.dart';
import 'dart:html';
import 'package:jaegr/model/user.dart';
import 'package:jaegr/model/user_create_data.dart';

@Component(
  selector: 'register',
  templateUrl: 'register_component.html'
)

class Register{

  UserCreateData model;

  CreateUser(){
    model = new UserCreateData();
  }

  void registerUser(dynamic e){
    e.preventDefault();
    var requestHeaders = {
      'Content-Type':'application/json',
      'Accept':'application/json'
    };
    HttpRequest.request("../rest/create",method: "POST",sendData: model.toJSON(),requestHeaders: requestHeaders).catchError((n)=>print(n));

  }
}
