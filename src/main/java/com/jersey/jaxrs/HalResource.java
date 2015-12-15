package com.jersey.jaxrs;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Vasyl Spachynskyi
 * @version $Id:
 * @since 26.11.2015
 */
@XmlRootElement(name = "resource")
public class HalResource<EntityType> extends Resource<EntityType> {

    private Map<String, Object> embedded;

    public HalResource(EntityType content, Iterable<Link> links) {
        super(content, links);
        embedded = new HashMap<>();
    }

    public HalResource(EntityType content, Link... links) {
        super(content, links);
        this.embedded = new HashMap<>();
    }

    public void add(Link link, Object embeddedItem) {
        super.add(link);
        embedded.put(link.getRel(), embeddedItem);
    }

    @XmlElement(name = "embedded")
    @JsonProperty("_embedded")
    public Map<String, Object> getEmbedded() {
        return embedded;
    }

    public void setEmbedded(Map<String, Object> embedded) {
        this.embedded = embedded;
    }
}