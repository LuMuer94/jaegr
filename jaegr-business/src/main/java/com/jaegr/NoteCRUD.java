package com.jaegr;

    /*
    TODO ++  Create GroupCRUD ++ CRUD Funktionen für Notes, User und Group implementieren
     */
import javax.transaction.Transactional;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Created by Leon on 09.06.2017.
 */
@Path("/notes")
@Transactional
public class NoteCRUD {

    @GET
    @Produces (MediaType.APPLICATION_JSON)
    public String nameDoesntMatter (){
        return "hello world";
    }
}
