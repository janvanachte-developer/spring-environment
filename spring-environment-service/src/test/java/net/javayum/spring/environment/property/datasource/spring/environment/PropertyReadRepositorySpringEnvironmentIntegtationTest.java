package net.javayum.spring.environment.property.datasource.spring.environment;

import net.javayum.spring.environment.property.domain.Property;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
public class PropertyReadRepositorySpringEnvironmentIntegtationTest {

    List<Property> result;
    
    @Test
    public void repository_should_render_many_spring_environment_properties() {
        
        given_environment_is_available();
        
        when_service_client_requests_all_properties();
        
        then_default_spring_environment_properties_should_be_provided();
        // and
        then_many_spring_environment_properties_should_be_provided();
    }

    private void then_many_spring_environment_properties_should_be_provided() {
        assertNotNull(result);
        assertTrue(result.size() > 50);
    }

    private void then_default_spring_environment_properties_should_be_provided() {
        assertNotNull(result);
    }

    private void when_service_client_requests_all_properties() {
    }

    private void given_environment_is_available() {

    }
}