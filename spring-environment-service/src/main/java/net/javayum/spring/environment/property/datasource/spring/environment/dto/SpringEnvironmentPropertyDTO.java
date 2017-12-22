package net.javayum.spring.environment.property.datasource.spring.environment.dto;

import net.javayum.spring.environment.property.datasource.jpa.PropertyEntity;
import net.javayum.spring.environment.property.domain.Key;
import net.javayum.spring.environment.property.domain.SpringEnvironmentProperty;
import net.javayum.spring.environment.property.domain.Value;

public class SpringEnvironmentPropertyDTO extends PropertyEntity implements SpringEnvironmentProperty {

    protected SpringEnvironmentPropertyDTO(Key key, Value value) {
        super(key, value);
    }

    public static SpringEnvironmentProperty createFrom(Key key, Value value) {

        return new SpringEnvironmentPropertyDTO(key, value);
    }
}
