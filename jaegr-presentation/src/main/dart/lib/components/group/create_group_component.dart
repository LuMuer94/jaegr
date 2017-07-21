import 'dart:async';
import 'package:angular2/angular2.dart';
import 'dart:html';
import 'package:jaegr/components/service/MockService.dart';
import 'package:jaegr/components/shared/context.dart';
import 'package:jaegr/model/group.dart';
import 'package:jaegr/model/news.dart';

@Component(
    selector: 'create-group',
    templateUrl: 'create_group_component.html',
    styleUrls: const ['create_group_component.css'])

class CreateGroup {
  String name;
  Group created;

  @Output()
  Stream<Group> get newGroup => _group.stream;
  final _group = new StreamController<Group>();

  final MockService restService;
  final Context context;
  CreateGroup( this.restService, this.context );

  bool inputsAreValid() {
    return name != null && name.compareTo("") != 0;
  }

  Future createGroup(dynamic e) async {
    e.preventDefault();
    if (inputsAreValid()) {
      try {
        created = await restService.createGroup(name);
        _group.add(created);
        window.alert("Group " + name +
            " create successfully!\nYou can now add users to your group");
        context.creatingGroup = false;
      }
      catch (e) {
        window.alert("Error!\nGroup " + name + " could not be created");
      }
    }
    else{
      window.alert("Enter a name for your group");
    }

  }

  void close(dynamic e){
    e.preventDefault();
    context.creatingGroup=false;
  }

}