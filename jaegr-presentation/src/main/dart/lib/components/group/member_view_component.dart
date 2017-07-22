import 'dart:async';
import 'dart:html';
import 'package:angular2/angular2.dart';
import 'package:angular2/platform/common.dart';
import 'package:angular2/router.dart';
import 'package:jaegr/components/service/MockService.dart';
import 'package:jaegr/components/shared/context.dart';
import 'package:jaegr/model/group.dart';
import 'package:jaegr/model/user.dart';

@Component(
  selector: 'member-view',
  templateUrl: 'member_view_component.html',
  styleUrls: const ['member_view_component.css']
)

class MemberView implements OnInit{

  Group group;
  User user;
  List<User> members;

  final MockService restService;
  final RouteParams _routeParams;
  final Router _router;
  final Context context;

  MemberView( this.restService, this._routeParams, this._router,this.context );

  getMembers()
  {
    members = group.users;
  }

  isAddingUser()
  {
    return context.addingUser;
  }
  startAddingUser()
  {
    if( user!=null && group!=null && user==group.admin ){
      context.addingUser=true;
    }
  }

  void goBack() => _router.navigate(['UserView']);

  @override
  Future<Null>ngOnInit() async{
    var _id = _routeParams.get('id');
    var id = int.parse(_id ?? '', onError: (_) => null);
    group = await restService.getGroupById(id);
    user = await restService.getCurrentUser();
    if( context.addingUser == null){
      context.addingUser=false;
    }
    getMembers();
  }

  Future addUser( User user ) async {
    if (await restService.getCurrentUser() == group.admin) {
      await restService.addUserToGroup(group.id, user.id);
      group = await restService.getGroupById(group.id);
      getMembers();
      window.alert("User " + user.name + " added to group " + group.name + "!");
      context.addingUser=false;
    }
    else {
      window.alert("User " + user.name + " could NOT be added to group " + group.name + "!");
    }
  }

}