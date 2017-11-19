package net.javayum.spring.environment.property.property.service.rs;

import net.javayum.spring.environment.property.property.infrastructure.persistence.PropertyRepository;
import net.javayum.spring.environment.property.infrastructure.spring.DatabasePropertySourceConfiguration;
import net.javayum.spring.environment.property.infrastructure.spring.UpdateablePropertiesPropertySource;
import net.javayum.spring.environment.property.property.model.Key;
import net.javayum.spring.environment.property.property.model.Property;
import net.javayum.spring.environment.property.property.model.dto.KeyDTO;
import net.javayum.spring.environment.property.property.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.Environment;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Service(PropertyServiceJAXRSConfiguration.SERVICE_NAME_SPRING_WEB)
@Transactional
@RestController
public class PropertyServiceSpringWeb implements PropertyService {

    public static final String APPLICATION_JSON = "application/json";

    private final PropertyRepository repository;
    private final Environment environment;

    @Autowired
    public PropertyServiceSpringWeb(PropertyRepository repository, Environment environment) {
        this.repository = repository;
        this.environment = environment;
    }

    @Override
    @RequestMapping(value = "/properties", method = RequestMethod.PUT)
    public Property create(Property property) {
        return repository.save(property);
    }

    @RequestMapping(path = "/properties/{key:.+}", method = RequestMethod.GET, produces = APPLICATION_JSON)
    // ":.+ added because of Spring bug truncating PathVariable with dots. See https://stackoverflow.com/questions/16332092/spring-mvc-pathvariable-with-dot-is-getting-truncated
    public Property get(@PathVariable String key) {
        return get(KeyDTO.of(key));
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
    @RequestMapping(value = "/properties", method = RequestMethod.DELETE)
    public void delete(Property property) {
        repository.delete(property.getKey());
    }

    @Override
    @RequestMapping(value = "/properties", method = RequestMethod.GET)
    public List<Property> getAllFromDatabase() {
        return repository.findAll();
    }

    @Override
    public List<Property> search(String query) {
        return null;
    }

    @Override
    @RequestMapping(value = "/environment", method = RequestMethod.GET)
    public void refresh(Key key) {
        Property property = repository.findOne(key);

        MutablePropertySources propertySources = ((ConfigurableEnvironment) environment).getPropertySources();

        UpdateablePropertiesPropertySource propertySource = (UpdateablePropertiesPropertySource) propertySources.get(DatabasePropertySourceConfiguration.DB_PROPERTY_SOURCE);
        propertySource.update(property.getKey().toStringValue(), property.getValue().toStringValue());
    }
}
