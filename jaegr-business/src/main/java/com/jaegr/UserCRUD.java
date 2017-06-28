package com.jaegr;

import com.jaegr.daos.UserDAO;
import org.json.simple.JSONObject;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Created by danny on 28.06.17.
 */
@Path("/users")
@Transactional
public class UserCRUD {
    @PersistenceContext
    private EntityManager entityManager;

    /*
    Da user eine referenz auf andere user enthalten wird, sofern einfach eine list mit allen usern zurückgegeben wird,
    eine unendliche liste zurückgegeben. Deshalb werden hier nur die namen zurückgegeben.
     */
    //TODO: Find a way to give back the Users without creating a potential infinit list.
    // Maybe parse it to json yourself?
    @Path("/names")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public JSONObject getUserList(){
        UserDAO dao = new UserDAO(this.entityManager);
        List<DBUser> list = dao.getList();

        JsonHelper jhelper = new JsonHelper();

        return jhelper.toJSonUsers(list);
    }
}
