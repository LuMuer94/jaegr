import 'dart:async';
import 'dart:html';
import 'package:angular2/angular2.dart';
import 'package:angular2/router.dart';
import 'package:jaegr/components/group/leave_group_component.dart';
import 'package:jaegr/components/note/note_add_component.dart';
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
    directives: const [NoteView, NoteAdd, LeaveGroup])
class GroupView implements OnInit {
  @Input()
  Group group;

  @Input()
  User user;

  @Input()
  List<Note> groupNotes;

  final MockService restService;
  final Router _router;
  final Context context;

  GroupView(this.restService, this._router, this.context);

  void startAddingUser() {
    if (user == group.admin) {
      context.addingUser = true;
      viewUsers();
    }
  }

  isAddingNote() => context.addingNote;

  void startAddingNote() {
    context.addingNote=true;
  }

  Future viewUsers() async {
    _router.navigate([
      'MemberView',
      {'id': group.id.toString()}
    ]);
  }

  Future leaveGroup() async {
    _router.navigate([
      'LeaveGroup',
      {'id': group.id.toString()}
    ]);
  }

  Future getGroupNotes() async{
    return await restService.getNotesByGroup(group.id);
  }



  @override
  Future ngOnInit() async {
    context.addingNote=false;
    context.addingUser = false;
    if( group.users.contains( user )){
      groupNotes = await restService.getNotesByGroup(group.id);
    }
  }


  addNote(Note note){
    groupNotes.add( note );
  }
}
