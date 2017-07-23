import 'dart:async';
import 'package:angular2/angular2.dart';
import 'package:angular2/router.dart';
import 'package:jaegr/components/group/create_group_component.dart';
import 'package:jaegr/components/note/note_component.dart';
import 'package:jaegr/components/note/note_edit_component.dart';
import 'package:jaegr/components/service/AbstractService.dart';
import 'package:jaegr/components/service/MockService.dart';
import 'package:jaegr/components/service/service.dart';
import 'package:jaegr/components/shared/context.dart';
import 'package:jaegr/model/group.dart';
import 'package:jaegr/model/note.dart';
import 'package:jaegr/model/user.dart';


@Component(
  selector: 'my-view',
  templateUrl: 'user_view_component.html',
  styleUrls: const ['user_view_component.css'],
  directives: const [NoteComponent, NoteEdit,CreateGroup],
  providers: const [AbstractService]
)

//TODO: replace mock services
class UserViewComponent implements OnInit{
  String title = "My View";
  final Router _router;
  final MockService service;
  final Context context;
  List<Group> groups;

  List<Note> groupNotes;
  Note selectedNote;
  User user;

  selectedGroup() => context.selectedGroup;

  UserViewComponent(
      this.context,
      this.service,
      this._router
      );

  bool isCreatingGroup(){
    return context.creatingGroup;
  }

  Future<Null> ngOnInit() async {
    user = await service.getCurrentUser();
    groups = await service.getGroupsByUser(user.id);
  }


  Future<Null> onSelect(Group group) async{
    context.selectedGroup = group;
    groupNotes = await service.getNotesByGroup(selectedGroup().id);
  }

  void addGroup( Group group){
    groups.add(group);
    context.selectedGroup = group;
  }

  void startCreatingGroup(){
    context.creatingGroup=true;
  }

}