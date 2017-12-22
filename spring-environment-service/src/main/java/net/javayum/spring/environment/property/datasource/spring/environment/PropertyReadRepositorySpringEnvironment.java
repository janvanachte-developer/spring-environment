package net.javayum.spring.environment.property.datasource.spring.environment;

import net.javayum.spring.environment.property.datasource.spring.environment.dto.SpringEnvironmentPropertyDTO;
import net.javayum.spring.environment.property.domain.Key;
import net.javayum.spring.environment.property.domain.Property;
import net.javayum.spring.environment.property.domain.SpringEnvironmentProperty;
import net.javayum.spring.environment.property.domain.Value;
import net.javayum.spring.environment.property.domain.dto.KeyDTO;
import net.javayum.spring.environment.property.domain.dto.ValueDTO;
import net.javayum.spring.environment.property.repository.PropertyReadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.Environment;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertySource;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Component
public class PropertyReadRepositorySpringEnvironment implements PropertyReadRepository {


    private Environment environment;

    @Autowired
    public PropertyReadRepositorySpringEnvironment(Environment environment) {
        this.environment = environment;
    }

    @Override
    public List<Property> findAll() {

        List<Property> properties = new ArrayList<>();

        MutablePropertySources propertySources = ((ConfigurableEnvironment) environment).getPropertySources();
        Iterator<PropertySource<?>> iterator = propertySources.iterator();

        while (iterator.hasNext()) {

            PropertySource propertySource = iterator.next();

            String name = propertySource.getName();

            try {
                Map<String, String> source = (Map) propertySource.getSource();

                for (String key_stringValue : source.keySet()) {
                    String value_stringValue = source.get(key_stringValue);

                    Property property = SpringEnvironmentPropertyDTO.createFrom(KeyDTO.createFrom(key_stringValue), ValueDTO.createFrom(value_stringValue));
                    properties.add(property);
                }

            } catch (Exception e) {
                System.out.println("Cannot retrieve properties from propertysource " + name);

            }
        }

        return properties;
    }

    @Override
    public Property findOne(Key key) {

        SpringEnvironmentProperty property;

        String value = environment.getProperty(key.toStringValue());
        if (value == null) {
            property = SpringEnvironmentPropertyDTO.createFrom(key, Value.NO_VALUE);
        } else {
            property = SpringEnvironmentPropertyDTO.createFrom(key, ValueDTO.createFrom(value));
        }

        return property;
    }
}
