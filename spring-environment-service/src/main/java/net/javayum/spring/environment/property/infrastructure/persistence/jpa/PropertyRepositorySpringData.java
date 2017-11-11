package net.javayum.spring.environment.property.infrastructure.persistence.jpa;

import net.javayum.spring.environment.property.infrastructure.persistence.PropertyRepository;
import net.javayum.spring.environment.property.model.Key;
import net.javayum.spring.environment.property.model.Property;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface PropertyRepositorySpringData extends JpaRepository<PropertyEntity, String> {

}
