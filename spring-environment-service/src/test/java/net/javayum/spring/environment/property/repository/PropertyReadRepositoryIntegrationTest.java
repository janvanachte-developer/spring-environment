package net.javayum.spring.environment.property.repository;

import net.javayum.spring.environment.property.datasource.spring.environment.PropertyReadRepositorySpringEnvironmentConfiguraton;
import net.javayum.spring.environment.property.datasource.spring.environment.PropertyReadRepositorySpringEnvironment;
import net.javayum.spring.environment.property.domain.Property;
import net.javayum.spring.environment.property.domain.dto.KeyDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
        loader = AnnotationConfigContextLoader.class,
        classes = PropertyReadRepositorySpringEnvironmentConfiguraton.class
)
public class PropertyReadRepositoryIntegrationTest {

    @Autowired
    private PropertyReadRepository repository;

    @Test
    public void test_find_one_default_spring_environment_property() throws Exception {

        given_repository_is_backed_by_spring_environmnt();

        String key = "file.encoding";
        Property result = when_property_is_read(key);

        String value = "UTF-8";
        then_value_should_be(result, value);
    }

    @Test
    public void test_show_all_spring_environment_properties() throws Exception {

        given_repository_is_backed_by_spring_environmnt();

        List<Property> result = when_all_properties_are_read();

        int number = 10;
        then_there_should_be_a_mininum_number_of_properties(result, number);
    }

    private void given_repository_is_backed_by_spring_environmnt() {
        assertTrue(repository instanceof PropertyReadRepositorySpringEnvironment);
    }

    private Property when_property_is_read(String key) {
        return repository.findOne(KeyDTO.createFrom(key));
    }

    private List<Property> when_all_properties_are_read() {
        return repository.findAll();
    }
    private void then_value_should_be(Property actual, String expected) {
        assertEquals(expected, actual.getValue().toStringValue());
    }
    private void then_there_should_be_a_mininum_number_of_properties(List<Property> result, int number) {
        assertTrue(result.size() >= number);
    }

    @Test
    public void findOne() throws Exception {
    }

}