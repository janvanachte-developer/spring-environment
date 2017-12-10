package net.javayum.spring.environment.property.service.rs;

import net.javayum.spring.environment.property.model.Property;
import net.javayum.spring.environment.property.service.SynchronizeEnvironmentService;
import net.javayum.spring.environment.property.service.spring.DatabasePropertySourceConfiguration;
import net.javayum.spring.environment.property.service.spring.UpdateablePropertiesPropertySource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.Environment;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.stereotype.Component;

@Component
public class SynchronizeEnvironmentServiceJAXRS implements SynchronizeEnvironmentService {

    private final Environment environment;

    @Autowired
    public SynchronizeEnvironmentServiceJAXRS(Environment environment) {
        this.environment = environment;
    }

    @Override
    public void synchronize(Property property) {
    }
}
