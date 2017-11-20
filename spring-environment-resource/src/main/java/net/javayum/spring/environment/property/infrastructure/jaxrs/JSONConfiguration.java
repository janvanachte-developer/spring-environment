package net.javayum.spring.environment.property.infrastructure.jaxrs;

import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JSONConfiguration {

    @Bean
    public JacksonJsonProvider jsonProvider() {
        return new JacksonJsonProvider();
    }
}
