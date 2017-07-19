import 'dart:async';
import 'package:angular2/angular2.dart';
import 'package:angular2/router.dart';
import 'package:jaegr/components/service/service.dart';
import 'package:jaegr/model/group.dart';
import 'package:jaegr/model/user.dart';


@Component(
  selector: 'my-view',
  templateUrl: 'user_view_component.html',
  styleUrls: const ['user_view_component.css']
)

//TODO: replace mock services
class UserViewComponent implements OnInit{
  String title = "My View";
  final Router _router;
  final Service service;
  List<Group> groups;
  Group selectedGroup;

  UserViewComponent(
      this.service,
      this._router
      );

  //implement as future
  void getGroups()
   {
    groups =  service.getGroupsTest();
  }

  void ngOnInit() {
    getGroups();
  }

  void onSelect(Group group) {
    selectedGroup = group;
  }


}