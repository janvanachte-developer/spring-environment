package net.javayum.spring.environment.property.service.rs;

import net.javayum.spring.environment.property.infrastructure.persistence.PropertyRepository;
import net.javayum.spring.environment.property.infrastructure.spring.DatabasePropertySourceConfiguration;
import net.javayum.spring.environment.property.infrastructure.spring.UpdateablePropertiesPropertySource;
import net.javayum.spring.environment.property.model.Key;
import net.javayum.spring.environment.property.model.Property;
import net.javayum.spring.environment.property.model.dto.KeyDTO;
import net.javayum.spring.environment.property.service.PropertyService;
import net.javayum.spring.environment.property.service.PropertyServiceConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.Environment;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Api(value = "customers", description = "RESTful API to interact with customer resources.")
@Path(value = PropertyServiceJAXRS.JAXRS_CONTEXT_ROOT)
@Service(PropertyServiceConfiguration.SERVICE_NAME_JAXRS)
@Transactional
public class PropertyServiceJAXRS implements PropertyService {

    private static final String PROPERTIES_PATH = "/properties";
    public static final String JAXRS_CONTEXT_ROOT = "/jaxrs";

    private final PropertyRepository repository;
    private final Environment environment;

    @Autowired
    public PropertyServiceJAXRS(PropertyRepository repository, Environment environment) {
        this.repository = repository;
        this.environment = environment;
    }

    @Override
    public Property create(Property property) {
        return repository.save(property);
    }

    @GET
    @Path(PROPERTIES_PATH + "/{key}")
    @Produces(MediaType.APPLICATION_JSON)
    public Property get(@PathParam("key") String key) {
        return repository.findOne(KeyDTO.of(key));
    }

    @Override
    public Property get(Key key) {
        return repository.findOne(key);
    }

    @Override
    public Property update(Property property) {
        return repository.save(property);
    }

    @Override
    public void delete(Property property) {
        repository.delete(property.getKey());
    }

    @Override
    public List<Property> getAllFromDatabase() {
        return repository.findAll();
    }

    @Override
    public List<Property> search(String query) {
        return null;
    }

    @Override
    public void refresh(Key key) {
        Property property = repository.findOne(key);

        MutablePropertySources propertySources = ((ConfigurableEnvironment) environment).getPropertySources();

        UpdateablePropertiesPropertySource propertySource = (UpdateablePropertiesPropertySource) propertySources.get(DatabasePropertySourceConfiguration.DB_PROPERTY_SOURCE);
        propertySource.update(property.getKey().toStringValue(), property.getValue().toStringValue());
    }
}
