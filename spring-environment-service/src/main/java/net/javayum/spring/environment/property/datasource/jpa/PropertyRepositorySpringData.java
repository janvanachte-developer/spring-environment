package net.javayum.spring.environment.property.datasource.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PropertyRepositorySpringData extends JpaRepository<PropertyEntity, String> {

}
