package org.acme;

import javax.ws.rs.*;

@Path("/user")
public class UserResource {

    /***********GET**********/

    @GET
    public void getUser(){}

    /***********POST**********/

    @POST
    public void addUser(){}

    /***********PUT**********/

    @PUT
    public void updateUser(){}

    /***********DELETE**********/

    @DELETE
    public void deleteUser(){}

}
