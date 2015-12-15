package com.jersey.dto;

/**
 * Created by vsabadosh on 17/11/15.
*/

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Music Artist/Group.
 *
 */
@XmlRootElement(name = "artist")
public class Artist {

    private String id;
    private String name;

    public Artist(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public Artist() {}

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
