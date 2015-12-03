package com.jersey.dao;

/**
 * Created by vsabadosh on 17/11/15.
 */
public class Album {

    private String id;
    private String title;
    private Artist artist;
    private int stockLevel;

    public Album() {}
    
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
}