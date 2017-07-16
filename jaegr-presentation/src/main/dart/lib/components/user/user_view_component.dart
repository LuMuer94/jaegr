import 'dart:async';
import 'package:angular2/angular2.dart';
import 'package:angular2/router.dart';
import 'package:jaegr/components/services/group_service.dart';
import 'package:jaegr/model/group.dart';


@Component(
  selector: 'user-view',
  templateUrl: 'user_view_component.html',
  styleUrls: const ['heroes_component.css'],
  directives: const [COMMON_DIRECTIVES],
  pipes: const [COMMON_PIPES],
)
class UserViewComponent implements OnInit {
  List<Group> groups;
  Group selectedGroup;
  final GroupService _groupService;
  final Router _router;
  UserViewComponent(this._groupService, this._router);

  Future<Null> getGroups() async {
    groups = await _groupService.getGroups();
  }
  Future<Null> create(String name) async {
    name = name.trim();
    if (name.isEmpty) return;
    groups.add(await _groupService.create(name));
    selectedGroup = null;
  }
  Future<Null> leave(Group group) async {
    await _groupService.delete(group.name);
    groups.remove(group);
    if (selectedGroup == group) selectedGroup = null;
  }
  void ngOnInit() {
    getGroups();
  }
  void onSelect(Group group) {
    selectedGroup = group;
  }
  Future<Null> gotoDetail() => _router.navigate([
    'HeroDetail',
    {'id': selectedGroup.name.toString()}
  ]);
}