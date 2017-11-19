package net.javayum.spring.environment.property.property.service;

import net.javayum.spring.environment.property.property.infrastructure.persistence.jpa.PropertyEntity;
import net.javayum.spring.environment.property.property.model.Key;
import net.javayum.spring.environment.property.property.model.Property;
import net.javayum.spring.environment.property.property.model.Value;
import net.javayum.spring.environment.property.property.model.dto.KeyDTO;
import net.javayum.spring.environment.property.property.model.dto.ValueDTO;

public class TestData {

    public static final Key KEY = KeyDTO.of("key1");
    public static final Value VALUE = ValueDTO.of("value1");
    public static final Property PROPERTY = PropertyEntity.of(KEY, VALUE);
}
