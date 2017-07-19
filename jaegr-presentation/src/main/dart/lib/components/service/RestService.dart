import '../../model/group.dart';
import '../../model/group_create_data.dart';
import '../../model/note.dart';
import '../../model/note_create_data.dart';
import '../../model/user.dart';
import '../../model/user_create_data.dart';
import 'AbstractService.dart';
import 'dart:async';
import 'dart:convert';
import 'dart:html';

class RestService extends AbstractService {

  String baseUrl = "..";

  Future<String> doRequest(String url, String method, String content, bool hasResponseContent) {
    Map<String, String> reqHeaders = new Map();
    if(content != null) {
      reqHeaders['Content-Type'] = 'application/json';
    }

    if(hasResponseContent) {
      reqHeaders['Accept'] = 'application/json';
    }

    return HttpRequest.request("$baseUrl/$url", method: method, requestHeaders: reqHeaders, sendData: content).then((response) {
      int status = response.status;
      if(status != 200) {
        throw "Invalid status code: $status";
      }

      return response.responseText;
    });
  }

  @override
  Future<Group> createGroup(String name) {
    GroupCreateData gcd = new GroupCreateData(name);
    return doRequest("/rest/groups/create", "POST", gcd.toJSON(), true).then((s) {
      return new Group.fromJson(JSON.decode(s));
    });
  }

  @override
  Future<Note> createNote(String title, String content, int groupId) {
    NoteCreateData ncd = new NoteCreateData(title, content, groupId);
    return doRequest("/rest/notes/create", "POST", ncd.toJSON(), true).then((s) {
      return new Note.fromJson(JSON.decode(s));
    });
  }

  @override
  Future<User> createUser(String username, String password) {
    UserCreateData ucd = new UserCreateData(username, password);
    return doRequest("/rest/users/create", "POST", ucd.toJSON(), true).then((s) {
      return new User.fromJson(JSON.decode(s));
    });
  }

  @override
  Future<Null> deleteNote(int id) {
    return doRequest("/rest/notes/$id", "DELETE", null, false).then((s) => new Future.value(null));
  }

  @override
  Future<User> getCurrentUser() {
    return doRequest("/rest/users/current", "GET", null, true).then((s) {
      return new User.fromJson(JSON.decode(s));
    });
  }

  @override
  Future<Group> getGroupById(int id) {
    return doRequest("/rest/groups/$id", "GET", null, true).then((s) {
      return new Group.fromJson(JSON.decode(s));
    });
  }

  @override
  Future<List<Group>> getGroupsByUser(int id) {
    return doRequest("/rest/groups/byUser/$id", "GET", null, true).then((s) {
      return Group.fromJsonList(JSON.decode(s));
    });
  }

  @override
  Future<Note> getNoteById(int id) {
    return doRequest("/rest/notes/$id", "GET", null, true).then((s) {
      return new Note.fromJson(JSON.decode(s));
    });
  }

  @override
  Future<List<Note>> getNotesByGroup(int groupId) {
    return doRequest("/rest/notes/byGroup/$groupId", "GET", null, true).then((s) {
      return Note.fromJsonList(JSON.decode(s));
    });
  }

  @override
  Future<List<Note>> getNotesByUser(int userId) {
    return doRequest("/rest/notes/byUser/$userId", "GET", null, true).then((s) {
      return Note.fromJsonList(JSON.decode(s));
    });
  }

  @override
  Future<User> getUserById(int id) {
    return doRequest("/rest/users/$id", "GET", null, true).then((s) {
      return new User.fromJson(JSON.decode(s));
    });
  }

  @override
  Future<Null> login(String username, String password) {
    return HttpRequest.postFormData("$baseUrl/login.jsp", { "username" : username, "password" : password })
          .then((req) {
            if(req.status == 200) {
              return null;
            } else {
              throw "Login failed";
            }
    });
  }

  @override
  Future<dynamic> addUserToGroup(int groupId, int userId) {
    return doRequest("/rest/groups/$userId/add/$userId", "POST", null, false).then((s) => null);
  }

  @override
  Future<Null> removeUserToGroup(int groupId, int userId) {
    return doRequest("/rest/groups/$userId/remove/$userId", "POST", null, false).then((s) => null);
  }
  @override
  Future<Note> editNote(int id, String newTitle, String newContent) {
    // TODO: implement editNote
  }

  @override
  Future<dynamic> logout() {
    // TODO: implement logout
  }
}