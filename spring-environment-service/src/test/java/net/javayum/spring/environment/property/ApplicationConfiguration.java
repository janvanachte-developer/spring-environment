package net.javayum.spring.environment.property;

import net.javayum.spring.environment.property.infrastructure.spring.DatabasePropertySourceConfiguration;
import net.javayum.spring.environment.property.repository.PropertyRepositoryConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan(basePackageClasses = ApplicationConfiguration.class)
@Import({PropertyRepositoryConfiguration.class, DatabasePropertySourceConfiguration.class})
@PropertySource("classpath:application.properties")
public class ApplicationConfiguration {
}
