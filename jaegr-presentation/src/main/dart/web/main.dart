// Copyright (c) 2017. All rights reserved. Use of this source code
// is governed by a BSD-style license that can be found in the LICENSE file.

import 'package:angular2/platform/browser.dart';
import 'package:jaegr/app_component.dart';
import 'package:jaegr/components/service/MockService.dart';
import 'package:jaegr/components/service/RestService.dart';
import 'package:jaegr/components/shared/context.dart';


void main() {
  bootstrap(AppComponent, [MockService,RestService,Context]);
}

