package com.jersey.controllers;

/**
 * Created by vsabadosh on 17/11/15.
 */

import com.jersey.dao.Album;
import com.jersey.dao.Artist;
import com.jersey.dao.MusicService;
import com.jersey.dao.ResourceWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.hateoas.jaxrs.JaxRsLinkBuilder;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Component
@Path("/albums")
public class AlbumController {
    @Autowired
    ApplicationContext applicationContext;
    @Autowired
    private MusicService musicService;

    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, "application/hal+json", "application/hal+xml"})
    public Response getAllAlbums(@DefaultValue("false") @QueryParam("embedded") boolean embedded) {
        Collection<Album> albums = musicService.getAllAlbums();
        List<ResourceWrapper<Album>> resources = new ArrayList<>();
        for (Album album : albums) {
            resources.add(getAlbumResource(album, embedded));
        }
        applicationContext.getBean("_relProvider");
        return Response.ok(resources).build();
    }

    @GET
    @Path("/{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, "application/hal+json", "application/hal+xml"})
    public Response getAlbum(@PathParam("id") String id, @DefaultValue("false") @QueryParam("embedded") boolean embedded) {
        Album album = musicService.getAlbum(id);
        return Response.ok(getAlbumResource(album, embedded)).build();
    }

    private ResourceWrapper<Album> getAlbumResource(Album album, boolean embedded) {
        ResourceWrapper<Album> albumResource = new ResourceWrapper<>();
        albumResource.setEntity(album);
        albumResource.add(JaxRsLinkBuilder.linkTo(AlbumController.class).slash(album.getId()).withSelfRel());
        albumResource.add(JaxRsLinkBuilder.linkTo(ArtistController.class).slash(album.getArtistId()).withRel("artist"));

        if (embedded) {
            Resource<Artist> artistResource = new Resource<Artist>(musicService.getArtist(album.getArtistId()),
                    JaxRsLinkBuilder.linkTo(AlbumController.class).slash(album.getId()).withSelfRel());

            Resources<Resource<Artist>> resources = new Resources<>(Collections.singleton(artistResource));
            albumResource.setEmbedded(resources);
        }

        return albumResource;
    }

    @POST
    @Path("/{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, "application/hal+json", "application/hal+xml"})
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, "application/hal+json", "application/hal+xml"})
    public Response purchaseAlbum(@PathParam("id") String id, Album album) {
        musicService.addAlbum(album);
        return Response.ok().build();
    }
}
