package net.javayum.spring.environment.property.property.infrastructure.persistence;

import net.javayum.spring.environment.property.property.model.Key;
import net.javayum.spring.environment.property.property.model.Property;

import java.util.List;

public interface PropertyRepository {

    List<Property> findAll();

    Property findOne(Key key);

    Property save(Property property);

    void delete(Key key);
}
