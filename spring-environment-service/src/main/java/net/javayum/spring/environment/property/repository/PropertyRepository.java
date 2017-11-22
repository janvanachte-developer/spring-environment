package net.javayum.spring.environment.property.repository;

import net.javayum.spring.environment.property.model.Key;
import net.javayum.spring.environment.property.model.Property;

import java.util.List;

public interface PropertyRepository {

    List<Property> findAll();

    Property findOne(Key key);

    Property save(Property property);

    void delete(Key key);
}
