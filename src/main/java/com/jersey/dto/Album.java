package com.jersey.dto;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * Created by vsabadosh on 17/11/15.
 */
@XmlRootElement
public class Album {

    private String id;
    private String title;
    private Artist artist;
    private int stockLevel;
    private List<Musician> musicians;

    public Album() {
    }

    public Album(String id, String title, int stockLevel) {
        this.id = id;
        this.title = title;
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

    public List<Musician> getMusicians() {
        return musicians;
    }

    public void setMusicians(List<Musician> musicians) {
        this.musicians = musicians;
    }

    public Album copyAlbum() {
        return new Album(id, title, stockLevel);
    }
}