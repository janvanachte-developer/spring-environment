package net.javayum.spring.environment;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan(basePackageClasses = ApplicationConfiguration.class)
@PropertySource("classpath:application.properties")
public class ApplicationConfiguration {
}
