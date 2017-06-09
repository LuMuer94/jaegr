

import 'package:angular2/angular2.dart';
import 'dart:convert';
import 'dart:html';
import 'package:jaegr/model/news.dart';

@Component(selector: 'show-newest', templateUrl: 'newest_component.html')
class ShowNewest implements OnInit {

  News loaded;

  @override
  ngOnInit() {
    fetchNewest();
  }

  void loadNewest(dynamic e){
    e.preventDefault();
    fetchNewest();
  }

  bool hasBeenLoaded() => loaded != null;

  void fetchNewest() {
    HttpRequest.request("../rest/news/newest",method: "GET",requestHeaders: {'Accept':'application/json'}).then((response){
      var json = JSON.decode(response.responseText);
      var news = new News();
      news.content = json["content"];
      news.headline = json["headline"];
      news.id = json["id"];
      news.publishedOn = new DateTime.fromMillisecondsSinceEpoch(json["publishedOn"]);
      loaded = news;
    }).catchError((n)=>print(n));
  }
}