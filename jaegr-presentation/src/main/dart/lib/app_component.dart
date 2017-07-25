// Copyright (c) 2017. All rights reserved. Use of this source code
// is governed by a BSD-style license that can be found in the LICENSE file.

import 'dart:async';
import 'package:angular2/core.dart';
import 'package:jaegr/components/group/create_group_component.dart';
import 'package:jaegr/components/group/group_view_component.dart';
import 'package:jaegr/components/group/leave_group_component.dart';
import 'package:jaegr/components/group/member_view_component.dart';
import 'package:jaegr/components/login/login_component.dart';
import 'package:angular2/router.dart';
import 'package:jaegr/components/note/note_detail_component.dart';
import 'package:jaegr/components/note/note_edit_component.dart';
import 'package:jaegr/components/note/note_view_component.dart';
import 'package:jaegr/components/register/register_component.dart';
import 'package:jaegr/components/service/MockService.dart';
import 'package:jaegr/components/shared/context.dart';
import 'package:jaegr/components/user/user_search_component.dart';
import 'package:jaegr/components/util/util.dart';
import 'package:jaegr/components/user/user_view_component.dart';

@Component(
    selector: 'my-app',
    template: '''
      <h1>{{name}}</h1>
      <nav *ngIf="isLoggedIn() != null">
        <a [routerLink]="['Login/Logout']">Login/Logout</a>
        <a *ngIf="!isLoggedIn()" [routerLink]="['Register']">Register</a>
        <a *ngIf="isLoggedIn()" [routerLink]="['UserView']">Groups and Messages</a>
      </nav>
      <router-outlet></router-outlet>
    ''',
    styleUrls: const ['app_component.css'],
    directives: const [Login,Register,UserViewComponent,
    CreateGroup,GroupView,NoteView,MemberView,UserSearch,NoteDetail,NoteEdit,ROUTER_DIRECTIVES],
    providers: const [ROUTER_PROVIDERS])

@RouteConfig(const [
  const Route(path: '/login', name: 'Login', component: Login, useAsDefault: true),
  const Route(path: '/register', name: 'Register', component: Register),
  const Route(path: '/userView', name:'UserView', component: UserViewComponent),
  const Route(path: '/memberView/:id', name: 'MemberView', component: MemberView),
  const Route(path: '/noteDetail/:id', name: 'NoteDetail', component: NoteDetail),
  const Route(path: '/noteEdit/:id', name: 'NoteEdit', component: NoteEdit),
  const Route(path: '/leaveGroup/:id', name: 'LeaveGroup', component: LeaveGroup)
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
    context.creatingGroup = false;
    if( await Util.isLoggedIn( restService ) ){
      context.loggedIn=true;
      _router.navigate(['UserView']);
    }
    else{
      context.loggedIn=false;
      _router.navigate(['Login']);
    }
  }
}

