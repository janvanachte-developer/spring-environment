package net.javayum.spring.environment.property.service.spring;

import net.javayum.spring.environment.property.repository.PropertyRepository;
import net.javayum.spring.environment.property.model.Property;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.Environment;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertiesPropertySource;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Properties;

@Configuration
public class DatabasePropertySourceConfiguration {

    public static final String DB_PROPERTY_SOURCE = "databasePropertySource";

    @Autowired
    private Environment environment;

    @Autowired
    private PropertyRepository propertyRepository;

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @PostConstruct
    public void initializeDatabasePropertySourceUsage() {

        MutablePropertySources propertySources = ((ConfigurableEnvironment) environment).getPropertySources();

        Properties properties = new Properties();

        try {
            List<Property> properties_db = propertyRepository.findAll();
            for ( Property property : properties_db) {

                String key = property.getKey().toStringValue();
                String value = property.getValue().toStringValue();

                properties.put(key,value);

                logger.debug("Adding Property {}={} to PropertySource {}", key, value, DB_PROPERTY_SOURCE);
            }
            PropertiesPropertySource propertySource = new UpdateablePropertiesPropertySource(DB_PROPERTY_SOURCE, properties);

            propertySources.addFirst(propertySource);
        } catch (Exception e) {
            logger.error("Error during database properties setup", e);
            throw new RuntimeException(e);
        }
    }

    /**
     * Read why this is required: http://www.baeldung.com/2012/02/06/properties-with-spring/#java
     * It is important to be static: http://www.java-allandsundry.com/2013/07/spring-bean-and-propertyplaceholderconf.html
     */
    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }
}
