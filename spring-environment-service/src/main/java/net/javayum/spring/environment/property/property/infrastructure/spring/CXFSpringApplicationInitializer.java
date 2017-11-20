package net.javayum.spring.environment.property.property.infrastructure.spring;

import org.apache.cxf.transport.servlet.CXFServlet;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration;

@Order(Ordered.HIGHEST_PRECEDENCE)
public class CXFSpringApplicationInitializer implements WebApplicationInitializer {

    @Override
    public void onStartup(ServletContext servletContext) {

        AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext();
        applicationContext.register(SpringWebApplicationConfiguration.class);
        servletContext.addListener(new ContextLoaderListener(applicationContext));

        ServletRegistration.Dynamic dispatcher
                = servletContext.addServlet("dispatcher", new CXFServlet());
        dispatcher.addMapping("/cxf");
    }
}