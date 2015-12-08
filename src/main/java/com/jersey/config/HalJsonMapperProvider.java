package com.jersey.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.introspect.JacksonAnnotationIntrospector;
import com.fasterxml.jackson.module.jaxb.JaxbAnnotationIntrospector;
import org.springframework.hateoas.core.DefaultRelProvider;
import org.springframework.hateoas.hal.Jackson2HalModule;
import org.springframework.stereotype.Component;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;

/**
 * Created by vsabadosh on 26/11/15.
 */

@Component
@Provider
@Consumes({"application/hal+json", "text/json"})
@Produces({"application/hal+json", "text/json"})
public class HalJsonMapperProvider implements ContextResolver<ObjectMapper> {
    private final ObjectMapper halObjectMapper;

    public HalJsonMapperProvider() {
        halObjectMapper = new ObjectMapper();
        halObjectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        halObjectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);

        AnnotationIntrospector primary = new JaxbAnnotationIntrospector();
        AnnotationIntrospector secondary = new JacksonAnnotationIntrospector();
        AnnotationIntrospector pair = AnnotationIntrospector.pair(secondary, primary);
        halObjectMapper.setAnnotationIntrospector(pair);

        halObjectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        halObjectMapper
                .setHandlerInstantiator(new Jackson2HalModule.
                        HalHandlerInstantiator(new DefaultRelProvider(), null, false));
        halObjectMapper.registerModule(new Jackson2HalModule());
    }

    @Override
    public ObjectMapper getContext(Class<?> type) {
        return halObjectMapper;
    }
}