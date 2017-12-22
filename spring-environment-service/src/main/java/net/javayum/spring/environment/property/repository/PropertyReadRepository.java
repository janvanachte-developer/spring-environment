package net.javayum.spring.environment.property.repository;

import net.javayum.spring.environment.property.domain.Key;
import net.javayum.spring.environment.property.domain.Property;

import java.util.List;

public interface PropertyReadRepository {

    List<Property> findAll();

    Property findOne(Key key);

}
