package com.jersey.providers;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.DeserializationFeature;
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
public class HalXmlMapperProvider implements ContextResolver<XmlMapper> {
    private final XmlMapper xmlMapper;

    public HalXmlMapperProvider() {
        xmlMapper = new XmlMapper();
        xmlMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        xmlMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        xmlMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

        AnnotationIntrospector primary = new JaxbAnnotationIntrospector();
        AnnotationIntrospector secondary = new JacksonAnnotationIntrospector();
        AnnotationIntrospector pair = AnnotationIntrospector.pair(primary, secondary);

        xmlMapper.setAnnotationIntrospector(pair);
        xmlMapper.setHandlerInstantiator(new Jackson2HalModule.
                HalHandlerInstantiator(new XmlRootRelProvider(), null, false));
        xmlMapper.registerModule(new Jackson2HalModule());
    }

    @Override
    public XmlMapper getContext(Class<?> type) {
        return xmlMapper;
    }
}
