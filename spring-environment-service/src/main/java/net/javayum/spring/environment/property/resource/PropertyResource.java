package net.javayum.spring.environment.property.resource;

import net.javayum.spring.environment.property.domain.Key;
import net.javayum.spring.environment.property.domain.Property;

import java.util.List;

public interface PropertyResource {

    Property create(Property property);

    Property get(Key key);

    Property update(Property property);

    void delete(Property property);

    List<Property> getAllFromDatabase();

    List<Property> search(String query);
}