package com.jersey.controllers;

import com.jersey.dao.Artist;
import com.jersey.dao.MusicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
* Created by vsabadosh on 17/11/15.
*/
@Component
@Path("/artist")
public class ArtistController {
    private static final String APPLICATION_HAL_JSON = "application/hal+json";
    private static final String APPLICATION_HAL_XML = "application/hal+xml";
    private static final String HAL = "hal+";

    @Autowired
    private MusicService musicService;

    @GET
    @Path("/{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, APPLICATION_HAL_JSON, APPLICATION_HAL_XML})
    public Response getArtist(@PathParam("id") String id, @HeaderParam(HttpHeaders.ACCEPT) String accept) {
        Artist a = new Artist("1", "Dafuq");

        return Response.ok(a).build();
    }

    @POST
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, APPLICATION_HAL_JSON, APPLICATION_HAL_XML})
    public Response putArtist(Artist artistResource) {
        return Response.status(201).entity(artistResource).build();
    }
}

