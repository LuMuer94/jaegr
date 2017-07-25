import 'dart:async';
import 'package:angular2/angular2.dart';
import 'package:angular2/router.dart';
import 'package:intl/intl.dart';
import 'package:jaegr/components/service/MockService.dart';
import 'package:jaegr/components/shared/context.dart';
import 'package:jaegr/model/note.dart';
import 'package:jaegr/model/user.dart';
@Component(
  selector: 'note-detail',
  templateUrl: 'note_detail_component.html',
  styleUrls: const ['note_view_component.css', 'note_detail_component.css'],
)
class NoteDetail implements OnInit{

  Note note;

  final Router _router;
  final RouteParams _routeParams;
  final Context context;
  final MockService restService;

  NoteDetail(this._router, this._routeParams,this.context, this.restService);

  @override
  Future<Null>ngOnInit() async{
    var _id = _routeParams.get('id');
    var id = int.parse(_id ?? '', onError: (_) => null);
    note = await restService.getNoteById(id);
  }

  void goBack() => _router.navigate(['UserView']);

  String formatDate( DateTime date )
  {
    var formatter = new DateFormat('yyyy-MM-dd H:m:s');
    String formatted = formatter.format(date);
    return formatted;
  }

}