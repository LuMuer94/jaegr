// Copyright (c) 2017. All rights reserved. Use of this source code
// is governed by a BSD-style license that can be found in the LICENSE file.

import 'dart:html';
import 'package:angular2/angular2.dart';
import 'package:angular2/platform/browser.dart';
import 'package:jaegr/app_component.dart';
import 'package:http/http.dart';
import 'package:http/browser_client.dart';
import 'package:jaegr/components/service/MockService.dart';
import 'package:jaegr/components/service/service.dart';
import 'package:jaegr/components/services/user_service.dart';
import 'package:jaegr/components/services/group_service.dart';


void main() {
  bootstrap(AppComponent, [MockService]);
}

