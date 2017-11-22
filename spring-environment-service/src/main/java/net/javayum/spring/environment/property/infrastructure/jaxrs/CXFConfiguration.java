package net.javayum.spring.environment.property.infrastructure.jaxrs;

import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import net.javayum.spring.environment.property.infrastructure.application.WebApplicationConfiguration;
import net.javayum.spring.environment.property.resource.PropertyResource;
import org.apache.cxf.bus.spring.SpringBus;
import org.apache.cxf.endpoint.Server;
import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;
import org.apache.cxf.transport.servlet.CXFServlet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration;
import javax.ws.rs.ext.RuntimeDelegate;
import java.util.Arrays;

@Configuration
public class CXFConfiguration {

    public static void addCXFServlet(ServletContext servletContext, String url) {
        ServletRegistration.Dynamic servlet
                = servletContext.addServlet(url.replace("/","").replace("*",""), new CXFServlet());
        servlet.setLoadOnStartup(1);
        servlet.addMapping(url);
    }

    @Bean(destroyMethod = "shutdown")
    public SpringBus cxf() {
        return new SpringBus();
    }

    @Bean
    @DependsOn("cxf")
    @Autowired
    public Server jaxRsServer(ApplicationContext appContext, PropertyResource propertyResource, JacksonJsonProvider jsonProvider, WebApplicationConfiguration.JaxRsApiApplication jaxRsApiApplication) {
        JAXRSServerFactoryBean factory = RuntimeDelegate.getInstance().createEndpoint(jaxRsApiApplication, JAXRSServerFactoryBean.class);
        factory.setServiceBeans(Arrays.<Object>asList(propertyResource));
        factory.setAddress("/" + factory.getAddress());
        factory.setProvider(jsonProvider);
        return factory.create();
    }
}
