package com.jaegr;

    /*
    TODO ++  Create GroupCRUD ++ CRUD Funktionen für Notes, User und Group implementieren
     */
import com.jaegr.daos.NoteDAO;
import org.json.simple.JSONObject;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Leon on 09.06.2017.
 */
@Path("/notes")
@Transactional
public class NoteCRUD {

    @PersistenceContext
    private EntityManager entityManager;


    //ToDo:
    /*
    create(groupId)
    update(change content)
    delete(check owner)

    get notes by user
    get notes by groups
    get

    noteview ->
     */
}
