package net.javayum.spring.environment.property.service;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "net.javayum.spring.environment.property")
public class PropertyServiceConfiguration {
    public static final String SERVICE_NAME_JAXRS = "JAXRS";
    public static final String SERVICE_NAME_SPRING_WEB = "SPRING_WEB";
}
