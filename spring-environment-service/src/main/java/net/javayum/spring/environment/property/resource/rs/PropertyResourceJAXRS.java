package net.javayum.spring.environment.property.resource.rs;

import net.javayum.spring.environment.property.domain.Key;
import net.javayum.spring.environment.property.repository.PropertyRepository;
import net.javayum.spring.environment.property.domain.Property;
import net.javayum.spring.environment.property.domain.dto.KeyDTO;
import net.javayum.spring.environment.property.resource.PropertyResource;
import net.javayum.spring.environment.property.service.EnvironmentSynchronizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

import static net.javayum.spring.environment.property.resource.rs.PropertyResourceJAXRS.SERVICE_NAME;

// local tomcat: http://localhost:8080/cxfservlet/properties/

//@Api(value = "customers", description = "RESTful API to interact with customer resources.")
@Service(SERVICE_NAME)
@Transactional
public class PropertyResourceJAXRS implements PropertyResource {

    public static final String PATH = "/properties";
    public static final String SERVICE_NAME = "net/javayum/spring/environment/property";

    @Autowired
    private PropertyRepository repository;

    //@Autowired
    private EnvironmentSynchronizationService service;

    public PropertyResourceJAXRS() {}

    @Override
    public Property create(Property property) {
        return repository.save(property);
    }

    @GET
    @Path(PATH + "/{key}")
    @Produces(MediaType.APPLICATION_JSON)
    public Property getJSON(@PathParam("key") String key) {
        return PropertyJSON.createFrom(get(KeyDTO.createFrom(key)));
    }

    @Override
    public Property get(Key key) {
        return repository.findOne(key);
    }

    @PUT
    @Path(PATH + "/{key}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Property putJSON(PropertyJSON property, @PathParam("key") String key) {
        return PropertyJSON.createFrom(update(property));
    }

    @Override
    public Property update(Property property) {
        return service.sync(repository.save(property));
    }

    @Override
    public void delete(Property property) {
        repository.delete(property.getKey());
    }

    @GET
    @Path(PATH)
    @Produces(MediaType.APPLICATION_JSON)
    @Override
    public List<Property> getAllFromDatabase() {
        return PropertyJSON.createFrom(repository.findAll());
    }

    @Override
    public List<Property> search(String query) {
        return null;
    }

}
