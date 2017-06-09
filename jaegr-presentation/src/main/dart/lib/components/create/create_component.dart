

import 'package:angular2/angular2.dart';
import 'dart:html';
import 'package:jaegr/model/news.dart';

@Component(selector: 'create-news', templateUrl: 'create_component.html')
class CreateNews {

  News model;

  CreateNews(){
    model = new News();
  }

  void postNews(dynamic e){
    e.preventDefault();
    var requestHeaders = {
      'Content-Type':'application/json',
      'Accept':'application/json'
    };
    HttpRequest.request("../rest/news",method: "POST",sendData: model.toJSON(),requestHeaders: requestHeaders).catchError((n)=>print(n));

  }
}