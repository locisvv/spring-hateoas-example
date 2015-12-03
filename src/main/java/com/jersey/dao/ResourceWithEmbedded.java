package com.jersey.dao;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonUnwrapped;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Vasyl Spachynskyi
 * @version $Id:
 * @since 26.11.2015
 */
@XmlRootElement
public class ResourceWithEmbedded<T> {
    @JsonUnwrapped
    T entity;
    public ResourceWithEmbedded() {}

    @JsonProperty("_embedded")
    @XmlElement(name = "embedded")
    private Map<String, Object> embedded;

    public T getEntity() {
        return entity;
    }

    public void setEntity(T entity) {
        this.entity = entity;
    }

    public Map<String, Object> getEmbedded() {
        return embedded;
    }

    public void putEmbedded(String id, Object entity) {
        if (embedded == null) {
            embedded = new HashMap<>();
        }
        embedded.put(id, entity);
    }
}