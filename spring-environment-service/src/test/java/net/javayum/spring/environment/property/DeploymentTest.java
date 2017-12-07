package net.javayum.spring.environment.property;

import net.javayum.spring.environment.property.resource.PropertyResource;
import net.javayum.spring.environment.property.resource.rs.PropertyResourceJAXRS;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.extension.rest.client.ArquillianResteasyResource;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.io.File;

import static org.junit.Assert.assertNotNull;

//@RunWith(Arquillian.class)
public class DeploymentTest {

    @Deployment
    public static Archive<?> createTestArchive()  {

        File[] files = Maven.resolver().loadPomFromFile("./spring-environment-service/pom.xml").importRuntimeAndTestDependencies().resolve().withTransitivity().asFile();

        WebArchive webArchive = ShrinkWrap.create(WebArchive.class, "spring-environment.war");
        for(File file : files){
            webArchive.addAsLibrary(file);
        }
        webArchive.addPackages(true, "net.javayum.spring.environment.property");
        webArchive.addAsResource("db/properties.sql");
        webArchive.addAsResource("db/properties_data.sql");
        webArchive.addAsResource("log4j2.xml");

         return webArchive;
    }

    //@Test
    @RunAsClient
    public void get_all_properties(@ArquillianResteasyResource("") final WebTarget webTarget) {

        final Response response = webTarget
                .path(PropertyResourceJAXRS.SERVICE_NAME + PropertyResourceJAXRS.PATH)
                .request(MediaType.APPLICATION_JSON)
                .get();

        assertNotNull(response);
    }
}
