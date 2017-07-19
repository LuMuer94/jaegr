import 'dart:async';
import 'dart:convert';
import 'package:angular2/angular2.dart';
import 'dart:html';
import 'package:jaegr/model/user.dart';


@Injectable()
class CurrentUserService {

  User getUser() {
    HttpRequest request = new HttpRequest();
    User user;
    request.open("GET", "../rest/users/current");
    request.onLoad.listen((event) {
      if(request.status < 400) {
        try {
          user = new User.fromJson(JSON.decode(request.responseText));
        } catch(e) {
          print("Error!");
        }
      } else {
         print( "not logged in! 401" );
        return null;

      }
    });
    request.setRequestHeader("Accept", "application/json");
    return user;
  }

  Exception _handleError(dynamic e) {
    print(e); //for demo purposes only
    return new Exception('Server error; cause: $e');
  }

}