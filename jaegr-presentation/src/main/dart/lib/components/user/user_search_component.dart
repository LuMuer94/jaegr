import 'dart:async';
import 'dart:html';
import 'package:angular2/angular2.dart';
import 'package:angular2/router.dart';
import 'package:jaegr/components/service/MockService.dart';
import 'package:jaegr/model/group.dart';
import 'package:jaegr/model/user.dart';
import 'package:stream_transform/stream_transform.dart';
import 'package:prompt/prompt.dart';



@Component(
  selector: 'user-search',
  templateUrl: 'user_search_component.html',
  styleUrls: const ['user_search_component.css'],
)
class UserSearch implements OnInit {
  MockService restService;
  Router _router;

  @Input()
  Group group;

  @Output()
  Stream<User> get newUser => _user.stream;
  final _user = new StreamController<User>();

  Stream<List<User>> users;

  StreamController<String> _searchTerms =
  new StreamController<String>.broadcast();

  UserSearch(this.restService, this._router);
  // Push a search term into the stream.
  void search(String term) => _searchTerms.add(term);

  Future<Null> ngOnInit() async {
    users = _searchTerms.stream
        .transform(debounce(new Duration(milliseconds: 300)))
        .distinct()
        .transform(switchMap((term) => term.isEmpty
        ? new Stream<List<User>>.fromIterable([<User>[]])
        : restService.searchUser(term).asStream()))
        .handleError((e) {
      print(e); // for demo purposes only
    });
  }
  Future addUser(User user) async{
      if( !group.users.contains(user) ){
        _user.add( user );
      }
      else{
        window.alert( "User " + user.name + " is already a member of the group!");
      }
  }

}