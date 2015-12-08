package com.jersey.dao;

/**
 * Created by vsabadosh on 17/11/15.
 */
public class Album {

    private String id;
    private String title;
    private String artistId;
    private int stockLevel;

    public Album() {
    }

    public Album(String id, String title, String artistId, int stockLevel) {
        this.id = id;
        this.title = title;
        this.artistId = artistId;
        this.stockLevel = stockLevel;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getArtistId() {
        return artistId;
    }

    public int getStockLevel() {
        return stockLevel;
    }

    public void setStockLevel(int stockLevel) {
        this.stockLevel = stockLevel;
    }

    public Album copyAlbum() {
        return new Album(id, title, artistId, stockLevel);
    }
}