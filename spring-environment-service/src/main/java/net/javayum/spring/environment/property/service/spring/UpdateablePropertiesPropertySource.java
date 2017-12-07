package net.javayum.spring.environment.property.service.spring;

import org.springframework.core.env.PropertiesPropertySource;

import java.util.Properties;

public class UpdateablePropertiesPropertySource extends PropertiesPropertySource {

    public UpdateablePropertiesPropertySource(String name, Properties source) {
        super(name, source);
    }

    public void update(String key, String value) {
        source.put(key, value);
    }
}
