package net.javayum.spring.environment.property.service.rs;

import net.javayum.spring.environment.property.datasource.jpa.PropertyEntity;
import net.javayum.spring.environment.property.domain.Property;
import net.javayum.spring.environment.property.domain.dto.ValueDTO;
import net.javayum.spring.environment.property.repository.PropertyRepository;
import net.javayum.spring.environment.property.resource.rs.PropertyJSON;
import net.javayum.spring.environment.property.service.EnvironmentSynchronizationService;
import net.javayum.spring.environment.property.service.spring.DatabasePropertySourceConfiguration;
import net.javayum.spring.environment.property.service.spring.UpdateablePropertiesPropertySource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.Environment;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.ws.rs.*;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.net.URISyntaxException;

import static net.javayum.spring.environment.property.service.rs.EnvironmentSynchronizationServiceJAXRS.SERVICE_NAME;


@Service(SERVICE_NAME)
@Transactional
public class EnvironmentSynchronizationServiceJAXRS implements EnvironmentSynchronizationService {

    public static final String SERVICE_NAME = "something";
    public static final String PATH = "/spring-environment";

    private final Environment environment;
    private final PropertyRepository repository;

    @Autowired
    public EnvironmentSynchronizationServiceJAXRS(Environment environment, PropertyRepository repository) {
        this.environment = environment;
        this.repository = repository;
    }

    @PUT
    @Path(PATH + "/{key}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Property putJSON(PropertyJSON property, @PathParam("key") String key) {
        return PropertyJSON.createFrom(sync(property));
    }

    @Override // Move to abstract super ?
    public Property sync(Property property) {

        if ( ! dbUpdated(property)) {
            updateDb(property);
            sendToOtherEnvironments(property);
        }

        updateLocalEnvironment(property);

        return PropertyEntity.of(property.getKey(), ValueDTO.createFrom(environment.getProperty(property.getKey().toStringValue())));
    }

    private boolean dbUpdated(Property property) {

        try {
            Property propert_inDB = repository.requireOne(property.getKey());
            if ( property.getKey().equals(propert_inDB.getKey())) {
                return true;
            } else {
                return false;
            }
        } catch (PropertyRepository.NotFoundException e) {
                return false;
        }
    }

    private void updateDb(Property property) {
        repository.save(property);
    }

    private void updateLocalEnvironment(Property property) {
        MutablePropertySources propertySources = ((ConfigurableEnvironment) environment).getPropertySources();
        UpdateablePropertiesPropertySource propertySource = (UpdateablePropertiesPropertySource) propertySources.get(DatabasePropertySourceConfiguration.DB_PROPERTY_SOURCE);
        propertySource.update(property.getKey().toStringValue(), property.getValue().toStringValue());
    }

    private void sendToOtherEnvironments(Property property) {

        String key = property.getKey().toStringValue();

        URI[] uris = new URI[1];
        try {
            uris[0] = new URI("http://localhost:8080/cxfservlet");
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        Client client = ClientBuilder.newClient();

        for ( URI uri : uris ) {
            try {
                WebTarget webTarget = client.target(uri).path("spring-environemnt").path(key);
                Response response = webTarget.request(MediaType.APPLICATION_JSON).put(Entity.entity(property, MediaType.APPLICATION_JSON));
            } catch ( Exception e ) {
                // put on a queue or something
            }
        }
    }
}
