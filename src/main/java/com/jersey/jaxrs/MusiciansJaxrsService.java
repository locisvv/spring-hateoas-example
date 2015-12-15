package com.jersey.jaxrs;

import com.jersey.service.MusicRepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.MediaType.APPLICATION_XML;

/**
 * @author Vasyl Spachynskyi
 * @version $Id:
 * @since 15.12.2015
 */
@Component
@Path("/musics")
public class MusiciansJaxrsService {
    private static final String APPLICATION_HAL_JSON = "application/hal+json";
    private static final String APPLICATION_HAL_XML = "application/hal+xml";

    @Autowired
    private MusicRepositoryService musicRepositoryService;

    @GET
    @Path("/{id}")
    @Produces({APPLICATION_XML, APPLICATION_JSON, APPLICATION_HAL_JSON, APPLICATION_HAL_XML})
    public Response getMusician(@PathParam("id") Integer id, @HeaderParam(HttpHeaders.ACCEPT) String accept) {
        return Response.ok(musicRepositoryService.getMusician(id)).build();
    }

    @GET
    @Produces({APPLICATION_XML, APPLICATION_JSON, APPLICATION_HAL_JSON, APPLICATION_HAL_XML})
    public Response getMusicians(@PathParam("id") Integer id, @HeaderParam(HttpHeaders.ACCEPT) String accept) {
        return Response.ok(musicRepositoryService.getMusicians()).build();
    }
}
