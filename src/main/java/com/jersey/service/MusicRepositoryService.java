package com.jersey.service;

import com.jersey.dto.Album;
import com.jersey.dto.Artist;
import com.jersey.dto.Musician;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by vsabadosh on 17/11/15.
 */
@Service
public class MusicRepositoryService {

    private Map<String, Album> albums;
    private Map<String, Artist> artists;
    private List<Musician> musicians;

    public MusicRepositoryService() {
        albums = new HashMap<>();
        artists = new HashMap<>();
        musicians = new ArrayList<>();

        Artist artist1 = new Artist("1", "artist");
        Artist artist2 = new Artist("2", "Celtic Frost");
        artists.put(artist1.getId(), artist1);
        artists.put(artist2.getId(), artist2);

        musicians.addAll(Arrays.asList(
                new Musician(3, "Peter", "saxophone"),
                new Musician(2, "David", "violin"),
                new Musician(1, "Kate", "piano")));

        Album album1 = new Album("1", "album", 2);
        Album album2 = new Album("2", "Deliverance", 3);
        Album album3 = new Album("3", "Pale Communion", 0);
        Album album4 = new Album("3", "Monotheist", 1);

        albums.put(album1.getId(), album1);
        albums.put(album2.getId(), album2);
        albums.put(album3.getId(), album3);
        albums.put(album4.getId(), album4);
    }

    public void addAlbum(Album album) {
        albums.put(album.getId(), album);
    }

    public Collection<Album> getAllAlbums() {
        Map<String, Album> newAlbums = new HashMap<String, Album>();
        for (Album album : albums.values()) {
            newAlbums.put(album.getId(), album.copyAlbum());
        }
        return newAlbums.values();
    }

    public Album getAlbum(final String id) {
        Album album = albums.get(id);
        return album.copyAlbum();
    }

    public Artist getArtist(final String id) {
        return artists.get(id);
    }

    public Musician getMusician(final int id) {
        return musicians.get(id);
    }

    public List<Musician> getMusicians() {
        return musicians;
    }
}