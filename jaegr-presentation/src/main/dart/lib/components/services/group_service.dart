import 'package:angular2/angular2.dart';
import 'dart:convert';
import 'dart:async';
import 'package:http/http.dart';
import 'package:jaegr/model/group.dart';


@Injectable()
class GroupService {
  static const _groupUrl = '';

  final Client _http;

  GroupService(this._http);

  Future<List<Group>> getGroups() async {
    try {
      final response = await _http.get(_groupUrl);
      final groups = _extractData(response)
          .map((value) => new Group.fromJson(value))
          .toList();
      return groups;
    } catch (e) {
      throw _handleError(e);
    }
  }

  dynamic _extractData(Response resp) => JSON.decode(resp.body)['data'];

  Exception _handleError(dynamic e) {
    print(e); //for demo purposes only
    return new Exception('Server error; cause: $e');
  }

  Future<Group> getGroup(int id) async {
    try {
      final response = await _http.get('$_groupUrl/$id');
      return new Group.fromJson(_extractData(response));
    } catch (e) {
      throw _handleError(e);
    }
  }

  //(await getGroups()).firstWhere((group) => group.id == id);
  static final _headers = {'Content-Type': 'application/json'};

  Future<Group> update(Group group) async {
    try {
      final url = '$_groupUrl';
      final response =
      await _http.put(url, headers: _headers, body: JSON.encode(group));
      return new Group.fromJson(_extractData(response));
    } catch (e) {
      throw _handleError(e);
    }
  }

  Future<Group> create( String name ) async {
    try {
      final response = await _http.post(_groupUrl,
          headers: _headers, body: JSON.encode({'name': name}));
      return new Group.fromJson(_extractData(response));
    }
    catch (e)
    {
      throw _handleError(e);
    }
  }

  Future<Null> delete( String name) async {
    try {
      final url = '$_groupUrl/$name';
      await _http.delete(url, headers: _headers);
    }
    catch (e) {
      throw _handleError(e);
    }
  }

}