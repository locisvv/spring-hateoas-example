package com.jersey.config;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.introspect.JacksonAnnotationIntrospector;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.module.jaxb.JaxbAnnotationIntrospector;
import org.springframework.hateoas.core.DefaultRelProvider;
import org.springframework.hateoas.hal.Jackson2HalModule;
import org.springframework.stereotype.Component;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;

/**
 * @author Vasyl Spachynskyi
 * @version $Id:
 * @since 27.11.2015
 */
@Provider
@Component
@Consumes({"application/hal+xml", "application/xml"})
@Produces({"application/hal+xml", "application/xml"})
public class HalXmlMapperProvider implements ContextResolver<ObjectMapper> {
    private final ObjectMapper xmlMapper;

    public HalXmlMapperProvider() {
        xmlMapper = new ObjectMapper();
        xmlMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        xmlMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);

        AnnotationIntrospector primary = new JaxbAnnotationIntrospector();
        AnnotationIntrospector secondary =  new JacksonAnnotationIntrospector();
        AnnotationIntrospector pair = AnnotationIntrospector.pair(secondary, primary);
        xmlMapper.setAnnotationIntrospector(pair);

        xmlMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        xmlMapper.setHandlerInstantiator(new Jackson2HalModule.HalHandlerInstantiator(new DefaultRelProvider(), null));
        xmlMapper.registerModule(new Jackson2HalModule());
    }

    @Override
    public ObjectMapper getContext(Class<?> type) {
        return xmlMapper;
    }
}
