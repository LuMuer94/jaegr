import 'dart:async';
import 'dart:html';
import 'package:angular2/router.dart';
import 'package:angular2/angular2.dart';
import 'package:jaegr/components/shared/context.dart';
import 'package:jaegr/model/group.dart';
import 'package:jaegr/model/note.dart';
import 'package:jaegr/components/service/RestService.dart';

@Component(
  selector: 'note-add',
  templateUrl: 'note_add_component.html',
  styleUrls: const ['note_add_component.css'],
)

class NoteAdd implements OnInit{

  @Input()
  Group group;

  String title;
  String content;

  final Router _router;
  final RouteParams _routeParams;
  final Context context;
  final RestService restService;

  NoteAdd(this._router, this._routeParams, this.context, this.restService);

  @Output()
  Stream<Note> get newNote => _note.stream;
  final _note = new StreamController<Note>();

  @override
  Future<Null>ngOnInit() async{
  }

  void Abort() => context.addingNote=false;

  isAddingNote() => context.addingNote;

  clear(){
    title=null;
    content=null;

  }

  Future<Null> submit() async{
    try{
      Note note = await restService.createNote(title, content, group.id);
      _note.add( note );
      abort();
    }
    catch(e){
      window.alert( "Submitting Note failed!");
    }
  }

  Future<Null> abort() async{
    try{
      clear();
      context.addingNote=false;
    }
    catch(e){}
  }

}