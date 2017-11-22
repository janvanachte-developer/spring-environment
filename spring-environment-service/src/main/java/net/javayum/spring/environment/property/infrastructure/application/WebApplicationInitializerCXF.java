package net.javayum.spring.environment.property.infrastructure.application;

import net.javayum.spring.environment.property.infrastructure.jaxrs.CXFConfiguration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

import javax.servlet.ServletContext;

@Order(Ordered.HIGHEST_PRECEDENCE)
public class WebApplicationInitializerCXF implements WebApplicationInitializer {

    public static final String URL_CXFSERVLET = "/cxfservlet/*";

    @Override
    public void onStartup(ServletContext servletContext) {

        AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext();
        applicationContext.register(WebApplicationConfiguration.class);
        servletContext.addListener(new ContextLoaderListener(applicationContext));

        CXFConfiguration.addCXFServlet(servletContext, URL_CXFSERVLET);
    }
}