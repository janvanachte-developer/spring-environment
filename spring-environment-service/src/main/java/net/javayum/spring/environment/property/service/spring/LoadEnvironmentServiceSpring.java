package net.javayum.spring.environment.property.service.spring;

import net.javayum.spring.environment.property.model.Property;
import net.javayum.spring.environment.property.service.LoadEnvironmentService;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.Environment;
import org.springframework.core.env.MutablePropertySources;

import javax.inject.Inject;
import javax.inject.Named;

@Named
public class LoadEnvironmentServiceSpring implements LoadEnvironmentService {

    private final Environment environment;

    @Inject
    public LoadEnvironmentServiceSpring(Environment environment) {
        this.environment = environment;
    }

    @Override
    public void update(Property property) {
        MutablePropertySources propertySources = ((ConfigurableEnvironment) environment).getPropertySources();
        UpdateablePropertiesPropertySource propertySource = (UpdateablePropertiesPropertySource) propertySources.get(DatabasePropertySourceConfiguration.DB_PROPERTY_SOURCE);
        propertySource.update(property.getKey().toStringValue(), property.getValue().toStringValue());
    }
}