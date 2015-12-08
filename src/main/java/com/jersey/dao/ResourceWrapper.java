package com.jersey.dao;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.hateoas.Resources;

import javax.xml.bind.annotation.XmlAnyElement;
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
public class ResourceWrapper<T> extends ResourceSupport {
    @JsonUnwrapped
    @XmlAnyElement
    private T entity;

    @JsonUnwrapped
    @XmlAnyElement
    private Resources embedded;

    public T getEntity() {
        return entity;
    }

    public void setEntity(T entity) {
        this.entity = entity;
    }

    public Resources getEmbedded() {
        return embedded;
    }

    public void setEmbedded(Resources embedded) {
        this.embedded = embedded;
    }
}