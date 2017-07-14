// Copyright (c) 2017. All rights reserved. Use of this source code
// is governed by a BSD-style license that can be found in the LICENSE file.

import 'package:angular2/core.dart';
import 'package:jaegr/components/newest/newest_component.dart';
import 'package:jaegr/components/create/create_component.dart';
import 'package:jaegr/components/login/login_component.dart';
import 'package:angular2/router.dart';
import 'package:jaegr/components/register/register_component.dart';

@Component(
    selector: 'my-app',
    directives: const [Login,ShowNewest,CreateNews,ROUTER_DIRECTIVES],
    providers: const [ROUTER_PROVIDERS],
    template: '''
      <h1>{{name}}</h1>
      <nav>
        <a [routerLink]="['Login']">Login</a>
        <a [routerLink]="['CreateNews']">CreateNews</a>
        <a [routerLink]="['ShowNewest']">ShowNewest</a>
        <a [routerLink]="['Register']">Register</a>
      </nav>
      <router-outlet></router-outlet>
    ''')

@RouteConfig(const [
  const Route(
      path: '/login',
      name: 'Login',
      component: Login,
      useAsDefault: true),
  const Route(path: '/createNews', name: 'CreateNews', component: CreateNews),
  const Route(path: '/news', name: 'ShowNewest', component: ShowNewest),
  const Route(path: '/register', name: 'Register', component: Register)
])

class AppComponent {
  var name = 'JaegR';
}


/*

    <login></login>
      <br />
      <show-newest style="display: inline-block; vertical-align: top;"></show-newest>
      <create-news style="display: inline-block; vertical-align: top; margin-left: 5em;"></create-news>





 */