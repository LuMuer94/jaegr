import 'dart:async';
import 'package:angular2/angular2.dart';
import 'package:angular2/router.dart';
import 'package:intl/intl.dart';
import 'package:jaegr/components/service/MockService.dart';
import 'package:jaegr/components/shared/context.dart';
import 'package:jaegr/model/note.dart';
import 'package:jaegr/model/user.dart';
@Component(
    selector: 'note-view',
    templateUrl: 'note_view_component.html',
  styleUrls: const ['note_view_component.css'],
)
class NoteView{
  @Input()
  Note note;

  @Input()
  User user;

  final Router _router;
  final Context context;
  final MockService restService;

  NoteView(this._router, this.context, this.restService);

  String formatDate( DateTime date )
  {
    var formatter = new DateFormat('yyyy-MM-dd H:m:s');
    String formatted = formatter.format(date);
    return formatted;
  }






}