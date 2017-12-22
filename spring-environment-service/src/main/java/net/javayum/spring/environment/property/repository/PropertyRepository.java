package net.javayum.spring.environment.property.repository;

import net.javayum.spring.environment.property.domain.Key;
import net.javayum.spring.environment.property.domain.Property;

import java.util.List;

public interface PropertyRepository {

    List<Property> findAll();

    Property findOne(Key key);
    Property requireOne(Key key) throws NotFoundException;

    Property save(Property property);

    void delete(Key key);

    class NotFoundException extends RuntimeException {

        public NotFoundException(Key key) {
            super("Cannot find property with key=" + key.toStringValue());
        }
    }
}
