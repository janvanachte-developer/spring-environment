package net.javayum.spring.environment.property;

import net.javayum.spring.environment.property.datasource.jpa.PropertyEntity;
import net.javayum.spring.environment.property.domain.Key;
import net.javayum.spring.environment.property.domain.Property;
import net.javayum.spring.environment.property.domain.Value;
import net.javayum.spring.environment.property.domain.dto.KeyDTO;
import net.javayum.spring.environment.property.domain.dto.ValueDTO;

public class TestData {

    public static final Key KEY = KeyDTO.createFrom("key1");
    public static final Value VALUE = ValueDTO.createFrom("value1");
    public static final Property PROPERTY = PropertyEntity.of(KEY, VALUE);
}
