package net.javayum.spring.environment.property.infrastructure.persistence.jpa;

import org.springframework.data.jpa.repository.JpaRepository;


public interface PropertyRepositorySpringData extends JpaRepository<PropertyEntity, String> {

}
