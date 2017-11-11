package net.javayum.spring.environment.property.service;

import net.javayum.spring.environment.property.infrastructure.persistence.PropertyRepository;
import net.javayum.spring.environment.property.infrastructure.spring.DatabasePropertySourceConfiguration;
import net.javayum.spring.environment.property.infrastructure.spring.UpdateablePropertiesPropertySource;
import net.javayum.spring.environment.property.model.Key;
import net.javayum.spring.environment.property.model.Property;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.Environment;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PropertyServiceJAXRS implements PropertyService {


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
