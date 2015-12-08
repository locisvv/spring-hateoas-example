package com.jersey.jaxrs;

/**
 * Created by vsabadosh on 17/11/15.
 */

import com.jersey.dto.Album;
import com.jersey.dto.Artist;
import com.jersey.service.MusicRepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
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
public class AlbumJaxrsService {
    @Autowired
    private MusicRepositoryService musicRepositoryService;

    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, "application/hal+json", "application/hal+xml"})
    public Response getAllAlbums(@DefaultValue("false") @QueryParam("embedded") boolean embedded) {
        Collection<Album> albums = musicRepositoryService.getAllAlbums();
        List<HalResource<Album>> resources = new ArrayList<>();
        for (Album album : albums) {
            resources.add(getAlbumResource(album, embedded));
        }
        return Response.ok(resources).build();
    }

    @GET
    @Path("/{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, "application/hal+json", "application/hal+xml"})
    public Response getAlbum(@PathParam("id") String id, @DefaultValue("false") @QueryParam("embedded") boolean embedded) {
        Album album = musicRepositoryService.getAlbum(id);
        return Response.ok(getAlbumResource(album, embedded)).build();
    }

    private HalResource<Album> getAlbumResource(Album album, boolean embedded) {
        Resource<Artist> artistResource = new Resource<Artist>(musicRepositoryService.getArtist(album.getArtistId()),
                    JaxRsLinkBuilder.linkTo(AlbumJaxrsService.class).slash(album.getId()).withSelfRel());

        HalResource albumResource;
        if (embedded) {
            albumResource = new HalResource(Collections.singleton(artistResource));
        } else {
            albumResource = new HalResource();
        }

        albumResource.setEntity(album);
        albumResource.add(JaxRsLinkBuilder.linkTo(AlbumJaxrsService.class).slash(album.getId()).withSelfRel());
        albumResource.add(JaxRsLinkBuilder.linkTo(ArtistJaxrsService.class).slash(album.getArtistId()).withRel("artist"));

        return albumResource;
    }

    @POST
    @Path("/{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, "application/hal+json", "application/hal+xml"})
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, "application/hal+json", "application/hal+xml"})
    public Response purchaseAlbum(@PathParam("id") String id, Album album) {
        musicRepositoryService.addAlbum(album);
        return Response.ok().build();
    }
}
