package org.acme.resources;

import org.acme.models.User;
import org.jboss.resteasy.reactive.RestResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.*;

@Path("/user")
public class UserResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserResource.class);

    private final Map <String, User> users = Collections.synchronizedMap(new HashMap<>());

    /***********GET**********/

    @Path("/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes()
    public Response getUser(@PathParam("id") String id){
        LOGGER.info("getUser: start");
        User user = users.get(id);

        //se l'utente non è presente mi genera un errore 404
        if (user == null){
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        LOGGER.info("getUser: end");
        return Response.ok(user).build();
    }

    /***********POST**********/

    @POST
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addUser(User user){
        System.out.println(user.getCognome());
        LOGGER.info("createUser:start - user = [{}]", user.toString());
        String id = UUID.randomUUID().toString();

        //validazione base:
        if(user.getNome() == null || user.getCognome() == null || user.getNome().isBlank() || user.getCognome().isBlank()){
            LOGGER.warn("createUser:end - campi non compilati correttamente");
            return Response.status(Response.Status.BAD_REQUEST).build();
        }

        users.put(id, user);
        LOGGER.info("createUser: end - id = [{}]", id);
        return Response.ok(id).build();
    }

    /***********PUT**********/

    @Path("/{id}")
    @PUT
    @Produces()
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateUser(@PathParam("id") String id, User user){ //ma perchè dopo pathparam non metto la virgola e dopo string id sì

        LOGGER.info("updateUser:start - user = [{}], id = [{}]", user, id);
        users.put(id, user);
        LOGGER.info("updateUser: end - OK");
        return Response.ok().build();
    }

    /***********DELETE**********/

    @Path("/{id}")
    @DELETE
    @Produces()
    @Consumes()
    public Response deleteUser(@PathParam("id") String id){
        LOGGER.info("deleteUser: start - id = [{}]", id);
        users.remove(id);
        LOGGER.info("deleteUser: end - OK");
        return Response.ok().build();
    }

}
