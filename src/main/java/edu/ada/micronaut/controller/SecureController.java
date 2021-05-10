package edu.ada.micronaut.controller;

import io.micronaut.http.annotation.Get;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("secured")
public class SecureController {
    @GET
    @Path("message")
    @Produces(MediaType.TEXT_PLAIN)
    public String securedMethod(){

        return "This api is secured";
    }
}
