import 'dart:async';

import 'package:jaegr/components/service/RestService.dart';
import 'package:jaegr/model/user.dart';

class Util
{


  static Future<bool> isloggedIn( RestService rest) async {
    try {
      final User user = await rest.getCurrentUser();
      return true;
    }
    catch (e) {}
    return false;
  }


}