import 'dart:async';
import 'package:angular2/router.dart';
import 'package:angular2/angular2.dart';
import 'package:jaegr/model/note.dart';
import 'package:jaegr/components/service/MockService.dart';

@Component(
  selector: 'note-edit',
  styleUrls: const ['note_component.css'],
  templateUrl: 'note_edit_component.html',
  directives: const [COMMON_DIRECTIVES],
)

class NoteEdit
{
  @Input()
  Note note2Edit;
  final MockService _service;

  NoteEdit(this._service);


  Future<Null> submit() async{
    print(this.note2Edit.id);
    _service.editNote(note2Edit.id, note2Edit.title, note2Edit.content);
  }

  Future<Null> delete() async{

    _service.deleteNote(this.note2Edit.id);

  }
}