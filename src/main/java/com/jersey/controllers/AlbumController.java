package com.jersey.controllers;

/**
 * Created by vsabadosh on 17/11/15.
 */

import com.jersey.dao.Album;
import com.jersey.dao.Artist;
import com.jersey.dao.ResourceWithEmbedded;
import com.jersey.dao.MusicService;
import com.sun.jersey.server.linking.Link;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.*;

@Component
@Path("/albums")
public class AlbumController {

    @Autowired
    private MusicService musicService;

    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, "application/hal+json", "application/hal+xml"})
    public Response getAllAlbums(@DefaultValue("false") @QueryParam("embedded") boolean embedded) {
        Collection<Album> albums = musicService.getAllAlbums();
        List<ResourceWithEmbedded<Album>> resources = new ArrayList<>();
        for (Album album : albums) {
            resources.add(getAlbumResource(album, embedded));
        }
        return Response.ok(resources).build();
    }

    @GET
    @Path("/{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, "application/hal+json", "application/hal+xml"})
    public Response getAlbum(@PathParam("id") String id, @DefaultValue("false") @QueryParam("embedded") boolean embedded) {
        Album album = musicService.getAlbum(id);

        return Response.ok(getAlbumResource(album, embedded)).build();
    }

    private ResourceWithEmbedded<Album> getAlbumResource(Album album, boolean embedded) {
        ResourceWithEmbedded<Album> resource = new ResourceWithEmbedded<>();
        resource.setEntity(album);
        return resource;
    }

    @POST
    @Path("/{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, "application/hal+json", "application/hal+xml"})
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, "application/hal+json", "application/hal+xml"})
    public Response purchaseAlbum(@PathParam("id") String id, ResourceWithEmbedded<Album> albumResource) {
        musicService.addAlbum(albumResource.getEntity());
        return Response.ok().build();
    }
}
