package net.javayum.spring.environment.property.infrastructure.application;

import net.javayum.spring.environment.property.infrastructure.jaxrs.CXFConfiguration;
import net.javayum.spring.environment.property.infrastructure.spring.DatabasePropertySourceConfiguration;
import net.javayum.spring.environment.property.repository.PropertyRepositoryConfiguration;
import net.javayum.spring.environment.property.resource.PropertyResourceConfiguration;
import net.javayum.spring.environment.property.infrastructure.jaxrs.JSONConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@Configuration
@Import({
        DatabasePropertySourceConfiguration.class,
        PropertyRepositoryConfiguration.class,
        PropertyResourceConfiguration.class,

        JSONConfiguration.class,

        // http://localhost:8080/cxfservlet/properties
        CXFConfiguration.class,

})
public class WebApplicationConfiguration {

    @ApplicationPath("/")
    public class JaxRsApiApplication extends Application { }

    @Bean
    public JaxRsApiApplication jaxRsApiApplication() {
        return new JaxRsApiApplication();
    }
}
