import 'dart:async';
import 'dart:html';
import 'package:angular2/router.dart';
import 'package:angular2/angular2.dart';
import 'package:jaegr/components/shared/context.dart';
import 'package:jaegr/model/group.dart';
import 'package:jaegr/model/note.dart';
import 'package:jaegr/components/service/MockService.dart';
import 'package:jaegr/model/user.dart';

@Component(
  selector: 'leave-group',
  templateUrl: 'leave_group_component.html',
  styleUrls: const ['leave_group_component.css'],
)

class LeaveGroup implements OnInit{

  Group group;
  User user;

  final MockService restService;
  final RouteParams _routeParams;
  final Router _router;
  final Context context;

  LeaveGroup(this.restService, this._routeParams, this._router, this.context);


  @override
  Future<Null>ngOnInit() async{
    var _id = _routeParams.get('id');
    var id = int.parse(_id ?? '', onError: (_) => null);
    group = await restService.getGroupById(id);
    user = await restService.getCurrentUser();
  }

  void goBack() => _router.navigate(['UserView']);


  Future<Null> leaveGroup() async{
    try{
      await restService.removeUserToGroup(group.id, user.id);
      context.selectedGroup = null;
      goBack();
    }
    catch(e){
      window.alert( "Leaving Group failed!Maybe your are not a member of this group");
    }
  }


}