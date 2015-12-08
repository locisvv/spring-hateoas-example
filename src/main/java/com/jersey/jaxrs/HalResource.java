package com.jersey.jaxrs;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.hateoas.Resources;

import javax.xml.bind.annotation.*;
import java.util.Arrays;
import java.util.List;

/**
 * @author Vasyl Spachynskyi
 * @version $Id:
 * @since 26.11.2015
 */
@XmlRootElement(name = "resource")
public class HalResource<T> extends Resources {
    @JsonUnwrapped
    private T entity;

    public HalResource() {}

    public HalResource(Iterable<T> content) {
        super(content);
    }

    public T getEntity() {
        return entity;
    }

    public void setEntity(T entity) {
        this.entity = entity;
    }
}