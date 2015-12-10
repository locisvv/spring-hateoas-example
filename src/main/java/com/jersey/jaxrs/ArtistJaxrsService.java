package com.jersey.jaxrs;

import com.jersey.dto.Artist;
import com.jersey.service.MusicRepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static org.springframework.hateoas.jaxrs.JaxRsLinkBuilder.linkTo;

/**
 * Created by vsabadosh on 17/11/15.
 */
@Component
@Path("/artist")
public class ArtistJaxrsService {
    private static final String APPLICATION_HAL_JSON = "application/hal+json";
    private static final String APPLICATION_HAL_XML = "application/hal+xml";

    @Autowired
    private MusicRepositoryService musicRepositoryService;

    @GET
    @Path("/{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, APPLICATION_HAL_JSON, APPLICATION_HAL_XML})
    public Response getArtist(@PathParam("id") String id, @HeaderParam(HttpHeaders.ACCEPT) String accept) {
        Resource<Artist> hypResource = new Resource<>(musicRepositoryService.getArtist(id));
        hypResource.add(linkTo((ArtistJaxrsService.class)).slash(id).withRel("self"));
        hypResource.add(linkTo(ArtistJaxrsService.class).slash(id).withRel("my"));

        return Response.ok(hypResource).build();
    }

    @POST
   @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, APPLICATION_HAL_JSON, APPLICATION_HAL_XML})
    public Response putArtist(Resource<Artist> artistResource) {
        return Response.status(201).entity(artistResource).build();
    }
}