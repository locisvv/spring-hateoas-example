package com.jersey.config;

import com.fasterxml.jackson.annotation.JsonRootName;
import org.springframework.hateoas.RelProvider;
import org.springframework.hateoas.core.DefaultRelProvider;

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
        return  rootName.name().equals("##default") ? rootName.name() : type.getSimpleName().toLowerCase();
    }

    @Override
    public String getCollectionResourceRelFor(Class<?> type) {
        XmlRootElement rootName = type.getAnnotation(XmlRootElement.class);
        return  rootName.name().equals("##default") ? rootName.name() : type.getSimpleName().toLowerCase();
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
