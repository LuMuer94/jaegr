import 'dart:async';
import 'package:angular2/angular2.dart';
import 'package:angular2/router.dart';
import 'package:jaegr/model/note.dart';
@Component(
    selector: 'note',
    styleUrls: const ['note_component.css'],
    templateUrl: 'note_component.html'
)
class NoteComponent{
  @Input()
  Note note;

}