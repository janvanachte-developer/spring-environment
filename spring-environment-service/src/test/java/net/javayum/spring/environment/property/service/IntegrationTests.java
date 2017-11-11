package net.javayum.spring.environment.property.service;

import net.javayum.spring.environment.property.infrastructure.persistence.jpa.PropertyEntity;
import net.javayum.spring.environment.property.model.Key;
import net.javayum.spring.environment.property.model.Property;
import net.javayum.spring.environment.property.model.dto.KeyDTO;
import net.javayum.spring.environment.property.model.dto.ValueDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
        classes = {PropertyServiceConfiguration.class}
)
public class IntegrationTests {

    @Autowired
    private PropertyService service;

    @Test
    public void service_client_calls_method_get() throws Throwable {

        Property actual = service.get(TestData.KEY);

        assertEquals(TestData.PROPERTY, actual);
    }

    @Test
    public void service_client_calls_method_create() throws Throwable {

        Key key = KeyDTO.of("key10");
        Property seed = service.create(PropertyEntity.of(key, ValueDTO.of("value4")));
        Property actual = service.get(key);

        assertEquals(seed, actual);
    }

}
