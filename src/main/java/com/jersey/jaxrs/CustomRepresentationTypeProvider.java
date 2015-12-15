package com.jersey.jaxrs;

import com.fasterxml.jackson.jaxrs.xml.JacksonJaxbXMLProvider;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.ext.Provider;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

@Provider
@Consumes({
        "application/xml",
        "application/hal+xml",
        "text/xml"})
@Produces({
        "application/xml",
        "application/hal+xml",
        "text/xml"})
public class CustomRepresentationTypeProvider extends JacksonJaxbXMLProvider {

    @Override
    public boolean isReadable(Class<?> aClass, Type type, Annotation[] annotations, MediaType mediaType) {
        return super.isReadable(aClass, type, annotations, mediaType);
    }

    @Override
    public boolean isWriteable(Class<?> aClass, Type type, Annotation[] annotations, MediaType mediaType) {
        return super.isWriteable(aClass, type, annotations, mediaType);
    }
}