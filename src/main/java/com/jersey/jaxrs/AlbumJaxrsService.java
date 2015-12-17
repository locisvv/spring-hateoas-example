package com.jersey.jaxrs;

/**
 * Created by vsabadosh on 17/11/15.
 */

import com.jersey.dto.Album;
import com.jersey.dto.Artist;
import com.jersey.service.MusicRepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.MediaType.APPLICATION_XML;
import static org.springframework.hateoas.jaxrs.JaxRsLinkBuilder.linkTo;

@Component
@Path("/albums")
public class AlbumJaxrsService {
    @Autowired
    private MusicRepositoryService musicRepositoryService;

    @GET
    @Produces({APPLICATION_XML, APPLICATION_JSON, "application/hal+json", "application/hal+xml"})
    public Response getAllAlbums(@DefaultValue("false") @QueryParam("embedded") boolean embedded) {
        Collection<Album> albums = musicRepositoryService.getAllAlbums();

        List<HalResource> resources = new ArrayList<>();
        for (Album album : albums) {
            resources.add(getAlbumResource(album, embedded));
        }
        return Response.ok(resources).build();
    }

    @GET
    @Path("/{id}")
    @Produces({APPLICATION_XML, APPLICATION_JSON, "application/hal+json", "application/hal+xml"})
    public Response getAlbum(@PathParam("id") String id, @DefaultValue("false") @QueryParam("embedded") boolean embedded) {
        Album album = musicRepositoryService.getAlbum(id);
        return Response.ok(getAlbumResource(album, embedded)).build();
    }

    private HalResource<Album> getAlbumResource(Album album, boolean embedded) {
        Link self = linkTo(AlbumJaxrsService.class).slash(album.getId()).withSelfRel();
        HalResource albumResource = new HalResource(album, self);

        if (embedded) {
            Link musicians = linkTo(MusiciansJaxrsService.class).withRel("musicians");
            Link artistLink = linkTo(ArtistJaxrsService.class).slash(1).withRel("artist");
            Resource<Artist> artistResource = new Resource<>(musicRepositoryService.getArtist("1"), artistLink.withSelfRel());

            albumResource.add(musicians, musicRepositoryService.getMusicians());
            albumResource.add(artistLink, Arrays.asList(artistResource,artistResource));
        }
        return albumResource;
    }
}