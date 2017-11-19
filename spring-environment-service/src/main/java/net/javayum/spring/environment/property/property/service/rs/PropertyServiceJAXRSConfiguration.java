package net.javayum.spring.environment.property.property.service.rs;

import org.springframework.context.annotation.ComponentScan;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

//@Configuration
//@ApplicationPath(PropertyServiceJAXRS.CONTEXT_ROOT)
@ComponentScan(basePackages = "net.javayum.spring.environment.property.service.rs")
public class PropertyServiceJAXRSConfiguration {//extends Application {

    public static final String SERVICE_NAME = "JAXRS";
    public static final String SERVICE_NAME_SPRING_WEB = "SPRING_WEB";

 //   @Override
    public Set<Class<?>> getClasses() {
        return new HashSet<Class<?>>(
                Arrays.asList(
                        PropertyServiceJAXRS.class)); //,
//                        NotFoundExceptionHandler.class,
//                        AlreadyExistsExceptionHandler.class));
    }
}
