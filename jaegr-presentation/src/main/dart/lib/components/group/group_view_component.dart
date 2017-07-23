import 'dart:async';
import 'package:angular2/angular2.dart';
import 'package:angular2/router.dart';
import 'package:jaegr/components/note/note_view_component.dart';
import 'package:jaegr/components/service/MockService.dart';
import 'package:jaegr/components/shared/context.dart';
import 'package:jaegr/model/group.dart';
import 'package:jaegr/model/note.dart';
import 'package:jaegr/model/user.dart';


@Component(
  selector: 'group-view',
  templateUrl: 'group_view_component.html',
  styleUrls: const ['group_view_component.css'],
  directives: const [NoteView]
)

class GroupView implements OnInit{
  @Input()
  Group group;

  @Input()
  User user;

  List<Note> groupNotes;

  final MockService restService;
  final Router _router;
  final Context context;

  GroupView( this.restService, this._router, this.context);

  void startAddingUser(){
    if( user==group.admin ){
      context.addingUser=true;
      viewUsers();
    }
  }

  void startAddingNote(){
  }

  Future viewUsers() async{
    _router.navigate(['MemberView', {'id': group.id.toString()}]);
  }


  @override
  Future ngOnInit() async{
    groupNotes = await restService.getNotesByGroup(group.id);
    context.addingUser=false;
  }
}