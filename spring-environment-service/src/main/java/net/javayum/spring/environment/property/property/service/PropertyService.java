package net.javayum.spring.environment.property.property.service;

import net.javayum.spring.environment.property.property.model.Key;
import net.javayum.spring.environment.property.property.model.Property;

import java.util.List;

public interface PropertyService {

    Property create(Property property);

    Property get(Key key);

    Property update(Property property);

    void delete(Property property);

    List<Property> getAllFromDatabase();

    List<Property> search(String query);

    void refresh(Key key);
}
