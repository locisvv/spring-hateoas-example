package com.jersey.jaxrs;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resources;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Vasyl Spachynskyi
 * @version $Id:
 * @since 26.11.2015
 */
@XmlRootElement(name = "resource")
public class HalResource<EntityType, EmbeddedType> extends Resources<EmbeddedType> {

    @JsonUnwrapped
    private EntityType entity;

    public HalResource() {
    }

    public HalResource(Iterable<EmbeddedType> content, Link... links) {
        super(content, links);
    }

    public EntityType getEntity() {
        return entity;
    }

    public void setEntity(EntityType entity) {
        this.entity = entity;
    }
}