// Copyright (c) 2017. All rights reserved. Use of this source code
// is governed by a BSD-style license that can be found in the LICENSE file.

import 'dart:async';
import 'dart:html';
import 'package:angular2/core.dart';
import 'package:jaegr/components/newest/newest_component.dart';
import 'package:jaegr/components/create/create_component.dart';
import 'package:jaegr/components/login/login_component.dart';
import 'package:angular2/router.dart';
import 'package:jaegr/components/note/note_component.dart';
import 'package:jaegr/components/register/register_component.dart';
import 'package:jaegr/components/service/MockService.dart';
import 'package:jaegr/components/service/service.dart';
import 'package:jaegr/components/services/group_service.dart';
import 'package:jaegr/components/services/user_service.dart';
import 'package:jaegr/model/group.dart';
import 'package:jaegr/model/user.dart';
import 'package:jaegr/components/user/user_view_component.dart';

@Component(
    selector: 'my-app',
    template: '''
      <h1>{{name}}</h1>
      <nav>
        <a [routerLink]="['Login/Logout']">Login/Logout</a>
        <a [routerLink]="['CreateNews']">CreateNews</a>
        <a [routerLink]="['ShowNewest']">ShowNewest</a>
        <a *ngIf="false" [routerLink]="['Register']">Register</a>
        <a *ngIf="true" [routerLink]="['UserView']">Groups and Messages</a>
        <a *ngIf="false" [routerLink]="['NoteEdit']">Edit Notes</a>
      </nav>
      <router-outlet></router-outlet>
    ''',
    directives: const [Login,ShowNewest,CreateNews,Register,UserViewComponent,ROUTER_DIRECTIVES, NoteComponent],
    providers: const [ROUTER_PROVIDERS,UserService,GroupService])

@RouteConfig(const [
  const Route(path: '/login', name: 'Login', component: Login, useAsDefault: true),
  const Route(path: '/createNews', name: 'CreateNews', component: CreateNews),
  const Route(path: '/news', name: 'ShowNewest', component: ShowNewest),
  const Route(path: '/register', name: 'Register', component: Register),
  const Route(path: '/userView', name:'UserView', component: UserViewComponent),
  const Route(path: '/note', name:'NoteEdit', component: NoteComponent)
])

class AppComponent{
  var name = 'JaegR test';
}

