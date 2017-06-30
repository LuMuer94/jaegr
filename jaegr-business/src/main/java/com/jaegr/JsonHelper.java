package com.jaegr;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by danny on 28.06.17.
 */

public class JsonHelper {

    /*
    Kleine testmethode die einen User in ein JSONObject verpackt.
    Somit werden nur Informationen in das Object gepackt, die ich dem Client mitteilen will.
    (Den passworthash möchte ich zb nicht zurückgeben)
     */
    public JSONObject toJSon(DBUser user){
        String name = user.getName();
        Set<DBUser> friends = user.getFriends();
        long id = user.getId();
        Set<DBNote> notes = user.getNotes();

        JSONObject jobj = new JSONObject();
        jobj.put("Id", id);
        jobj.put("USername", name);
        JSONArray jarray = new JSONArray();
        for(DBUser friend : friends){
            jarray.add(friend.getName());
        }
        jobj.put("Friends", jarray);
        JSONArray jarrNotes = new JSONArray();
        for(DBNote note : notes){
            jarrNotes.add(note.getTitle());
        }
        jobj.put("Notes", jarrNotes);

        return jobj;
    }

    public JSONObject toJSonUsers(List<DBUser> list){
        JSONObject jobj = new JSONObject();
        for(DBUser user : list){
            jobj.put(user.getId(), toJSon(user));
        }
        return jobj;
    }


    public JSONObject toJSon(DBNote note){
        Date date = note.getDate();
        //Set<DBUser> recipients = note.getRecipients();
        String title = note.getTitle();
        DBUser owner = note.getUser();
        long id = note.getId();

        JSONObject jobj = new JSONObject();
        jobj.put("Id", id);
        jobj.put("Title", title);
        jobj.put("Owner", owner.getName());
        jobj.put("Date", date);

        JSONArray jarray = new JSONArray();
        //recipients.forEach(n -> jarray.add(n.getName()));

        jobj.put("Recipients", jarray);

        return jobj;
    }

    public JSONObject toJSonNotes(List<DBNote> list){
        JSONObject jobj = new JSONObject();
        for(DBNote note : list){
            jobj.put(note.getId(), toJSon(note));
        }
        return jobj;
    }
}
