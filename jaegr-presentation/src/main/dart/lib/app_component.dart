// Copyright (c) 2017. All rights reserved. Use of this source code
// is governed by a BSD-style license that can be found in the LICENSE file.

import 'dart:async';
import 'dart:html';
import 'package:angular2/core.dart';
import 'package:jaegr/components/newest/newest_component.dart';
import 'package:jaegr/components/create/create_component.dart';
import 'package:jaegr/components/login/login_component.dart';
import 'package:angular2/router.dart';
import 'package:jaegr/components/register/register_component.dart';
import 'package:jaegr/components/service/AbstractService.dart';
import 'package:jaegr/components/service/MockService.dart';
import 'package:jaegr/components/service/service.dart';
import 'package:jaegr/components/services/group_service.dart';
import 'package:jaegr/components/services/user_service.dart';
import 'package:jaegr/components/shared/context.dart';
import 'package:jaegr/model/group.dart';
import 'package:jaegr/model/user.dart';
import 'package:jaegr/components/user/user_view_component.dart';
import 'package:jaegr/model/util.dart';

@Component(
    selector: 'my-app',
    template: '''
      <h1>{{name}}</h1>
      <nav *ngIf="isLoggedIn() != null">
        <a [routerLink]="['Login/Logout']">Login/Logout</a>
        <!--<a [routerLink]="['CreateNews']">CreateNews</a>
        <a [routerLink]="['ShowNewest']">ShowNewest</a> -->
        <a *ngIf="!isLoggedIn()" [routerLink]="['Register']">Register</a>
        <a *ngIf="isLoggedIn()" [routerLink]="['UserView']">Groups and Messages</a>
      </nav>
      <router-outlet></router-outlet>
    ''',
    directives: const [Login,ShowNewest,CreateNews,Register,UserViewComponent,ROUTER_DIRECTIVES],
    providers: const [ROUTER_PROVIDERS,UserService,GroupService])

@RouteConfig(const [
  const Route(path: '/login', name: 'Login', component: Login, useAsDefault: true),
  const Route(path: '/createNews', name: 'CreateNews', component: CreateNews),
  const Route(path: '/news', name: 'ShowNewest', component: ShowNewest),
  const Route(path: '/register', name: 'Register', component: Register),
  const Route(path: '/userView', name:'UserView', component: UserViewComponent)
])

class AppComponent implements OnInit{
  var name = 'JaegR';


  bool isLoggedIn(){
    return context.loggedIn;
  }

  final MockService restService;
  final Router _router;
  final Context context;
  AppComponent( this.restService, this._router, this.context  );


  @override
  Future ngOnInit() async{
    if( await Util.isloggedIn( restService ) ){
      context.loggedIn=true;
      _router.navigate(['UserView']);
    }
    else{
      context.loggedIn=false;
      _router.navigate(['Login']);
    }
  }
}

