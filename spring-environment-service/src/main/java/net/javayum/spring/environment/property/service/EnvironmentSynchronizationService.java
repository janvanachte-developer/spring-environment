package net.javayum.spring.environment.property.service;

import net.javayum.spring.environment.property.domain.Property;

public interface EnvironmentSynchronizationService {

    Property sync(Property property);
}
