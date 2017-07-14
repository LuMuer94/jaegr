// Copyright (c) 2017. All rights reserved. Use of this source code
// is governed by a BSD-style license that can be found in the LICENSE file.

import 'package:angular2/core.dart';
import 'package:jaegr/components/newest/newest_component.dart';
import 'package:jaegr/components/create/create_component.dart';
import 'model/UserView.dart';

@Component(
    selector: 'my-app',
    directives: const [ShowNewest,CreateNews],
    template: '''
      <h1>Hello {{name}}</h1>
    ''')
class AppComponent {
  var name = 'Angular';
  User currentUser;
  bool isLoggedIn() => currentUser == null;
}
