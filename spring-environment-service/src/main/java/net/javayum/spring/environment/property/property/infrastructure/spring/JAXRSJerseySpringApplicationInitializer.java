package net.javayum.spring.environment.property.property.infrastructure.spring;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

import javax.servlet.*;

@Order(Ordered.HIGHEST_PRECEDENCE)
public class JAXRSJerseySpringApplicationInitializer {//implements WebApplicationInitializer {

    //@Override
    public void onStartup(ServletContext servletContext) {
        // Create the 'root' Spring application context
//        AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext();
//        applicationContext.register(SpringWebApplicationConfiguration.class);
//        servletContext.addListener(new ContextLoaderListener(applicationContext));
//
//        ServletRegistration.Dynamic dispatcher
//                = servletContext.addServlet("dispatcher", new CXFServlet());
//        dispatcher.addMapping("/jersey");
//
//        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
//        characterEncodingFilter.setEncoding("UTF-8");
//        characterEncodingFilter.setForceEncoding(true);
//
//        // UTF-8
//        servletContext.addFilter("characterEncoding", characterEncodingFilter).addMappingForUrlPatterns(null, false, "/*");
//
//        // security filter
////        container.addFilter("springSecurityFilterChain", new DelegatingFilterProxy("springSecurityFilterChain"))
//                .addMappingForUrlPatterns(null, false, "/*");

        // Manage the lifecycle of the root application context

//        ServletRegistration.Dynamic application = container.addServlet("dispatcher", new DispatcherServlet(rootContext));
//        application.setLoadOnStartup(1);
//        application.addMapping("/");
    }


//    @Override
//    public void onStartup(ServletContext servletContext) throws ServletException {
//        final AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
//        context.setConfigLocation("net.javayum.spring.environment.property");
//
//    final FilterRegistration.Dynamic characterEncodingFilter = servletContext.addFilter("characterEncodingFilter", new CharacterEncodingFilter());
//    characterEncodingFilter.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), true, "/*");
//    characterEncodingFilter.setInitParameter("encoding", "UTF-8");
//    characterEncodingFilter.setInitParameter("forceEncoding", "true");
//
//    final FilterRegistration.Dynamic springSecurityFilterChain = servletContext.addFilter("springSecurityFilterChain", new DelegatingFilterProxy());
//    springSecurityFilterChain.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), true, "/*");
//
//    servletContext.addListener(new ContextLoaderListener(context));
//    servletContext.setInitParameter("spring.profiles.default", "production");
//
//    final SpringServlet application = new SpringServlet();
//
//    final ServletRegistration.Dynamic appServlet = servletContext.addServlet("appServlet", application);
//    appServlet.setInitParameter("com.sun.jersey.config.property.packages", "net.javayum.spring.environment.property");
//    //appServlet.setInitParameter("com.sun.jersey.spi.container.ContainerRequestFilters", "com.myapp.api.SizeLimitFilter");
//    appServlet.setLoadOnStartup(1);
//
//    final Set<String> mappingConflicts = appServlet.addMapping("/api/*");
//
//    if (!mappingConflicts.isEmpty()) {
//        throw new IllegalStateException("'appServlet' cannot be mapped to '/' under Tomcat versions <= 7.0.14");
//    }

//    @Override
//    public void onStartup(ServletContext servletContext)
//            throws ServletException {
//
//        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
//        context.register(PropertyServiceJAXRSConfiguration.class);
//
//        servletContext.addListener(new ContextLoaderListener(context));
//        //servletContext.setInitParameter("contextConfigLocation", "net.javayum.spring.environment.property.service");
//
//        ServletRegistration.Dynamic application = servletContext.addServlet("dispatcher", new DispatcherServlet(context));
//
//        application.setLoadOnStartup(1);
//        application.addMapping("/");
//
//    }

//    public void onStartup2(ServletContext container) {
//        // Create the 'root' Spring application context
//        AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
//        rootContext.register(WebLayerConfiguration.class);
//        rootContext.getEnvironment().setActiveProfiles(SpringProfile.PRODUCTION);
//
//        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
//        characterEncodingFilter.setEncoding("UTF-8");
//        characterEncodingFilter.setForceEncoding(true);
//
//        // UTF-8
//        container.addFilter("characterEncoding", characterEncodingFilter).addMappingForUrlPatterns(null, false, "/*");
//
//        // security filter
////        container.addFilter("springSecurityFilterChain", new DelegatingFilterProxy("springSecurityFilterChain"))
////                .addMappingForUrlPatterns(null, false, "/*");
//
//        // Manage the lifecycle of the root application context
//        container.addListener(new ContextLoaderListener(rootContext));
//
//        ServletRegistration.Dynamic application = container.addServlet("dispatcher", new DispatcherServlet(rootContext));
//
//        application.setLoadOnStartup(1);
//        application.addMapping("/");
//    }

}
