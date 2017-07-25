import 'dart:async';

import 'package:jaegr/components/service/AbstractService.dart';
import 'package:jaegr/model/user.dart';

class Util
{


  static Future<bool> isLoggedIn( AbstractService rest) async {
    try {
      final User user = await rest.getCurrentUser();
      return user!=null;
    }
    catch (e) {}
    return false;
  }


}