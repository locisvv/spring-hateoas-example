package com.jersey.dao;

import com.jersey.controllers.AlbumController;
import com.jersey.controllers.ArtistController;
import com.sun.jersey.server.linking.Binding;
import com.sun.jersey.server.linking.Link;
import com.sun.jersey.server.linking.Links;
import com.sun.jersey.server.linking.Ref;
import com.sun.jersey.server.linking.Ref.Style;

import java.net.URI;

/**
 * Created by vsabadosh on 17/11/15.
 */
public class Album {

    private String id;
    private String title;
    private Artist artist;
    private int stockLevel;

    @Ref(
            value = "{id}",
            resource = ArtistController.class,
            style=Style.ABSOLUTE,
            method = "get",
            condition = "${not empty(instance.artist)}",
            bindings = @Binding(name = "id", value = "${instance.artist.id}"))
    private URI link;

    public Album() {
    }

    public Album(String id, String title, Artist artist, int stockLevel) {
        this.id = id;
        this.title = title;
        this.artist = artist;
        this.stockLevel = stockLevel;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Artist getArtist() {
        return artist;
    }

    public int getStockLevel() {
        return stockLevel;
    }

    public void setStockLevel(int stockLevel) {
        this.stockLevel = stockLevel;
    }

    public Album copyAlbum() {
        return new Album(id, title, artist, stockLevel);
    }

    public URI getLink() {
        return link;
    }

    public void setLink(URI link) {
        this.link = link;
    }
}