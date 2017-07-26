import 'dart:async';
import 'dart:html';
import 'package:angular2/router.dart';
import 'package:angular2/angular2.dart';
import 'package:jaegr/components/shared/context.dart';
import 'package:jaegr/model/note.dart';
import 'package:jaegr/components/service/RestService.dart';

@Component(
  selector: 'note-edit',
  templateUrl: 'note_edit_component.html',
  styleUrls: const ['note_edit_component.css'],
  directives: const [COMMON_DIRECTIVES],
)

class NoteEdit implements OnInit{

  Note note;
  String title;
  String content;

  final Router _router;
  final RouteParams _routeParams;
  final Context context;
  final RestService restService;

  NoteEdit(this._router, this._routeParams, this.context, this.restService);

  isDeletingNote() => context.deleteNote;

  @override
  Future<Null>ngOnInit() async{
  var _id = _routeParams.get('id');
  var id = int.parse(_id ?? '', onError: (_) => null);
  note = await restService.getNoteById(id);
  this.title = note.title;
  this.content = note.content;
  }

  void goBack() => _router.navigate(['UserView']);


  Future<Null> submit() async{
    try{
      await restService.editNote(note.id, title, content);
      goBack();
    }
    catch(e){
      window.alert( "Changing Note failed");
    }
  }

  void wantsToDeleteNote()
  {
    context.deleteNote=true;
  }

  void abort()
  {
    context.deleteNote=false;
  }

  Future<Null> delete() async{
    try{
      await restService.deleteNote(note.id);
      window.alert("Note deleted");
      goBack();
    }
    catch(e){
      window.alert( "Deleting Note failed");
    }
  }

}