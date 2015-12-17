package com.jersey.providers;

import org.atteo.evo.inflector.English;
import org.springframework.hateoas.RelProvider;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Vasyl Spachynskyi
 * @version $Id:
 * @since 14.12.2015
 */
public class XmlRootRelProvider implements RelProvider {
    @Override
    public String getItemResourceRelFor(Class<?> type) {
        XmlRootElement rootName = type.getAnnotation(XmlRootElement.class);
        return !rootName.name().equals("##default") ? rootName.name() : type.getSimpleName().toLowerCase();
    }

    @Override
    public String getCollectionResourceRelFor(Class<?> type) {
        XmlRootElement annotation = type.getAnnotation(XmlRootElement.class);
        String name = !annotation.name().equals("##default") ? annotation.name() : type.getSimpleName().toLowerCase();
        return English.plural(name);
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
