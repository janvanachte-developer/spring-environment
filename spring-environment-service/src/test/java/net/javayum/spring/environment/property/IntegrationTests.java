package net.javayum.spring.environment.property;

import net.javayum.spring.environment.property.infrastructure.application.WebApplicationConfiguration;
import net.javayum.spring.environment.property.infrastructure.persistence.jpa.PropertyEntity;
import net.javayum.spring.environment.property.model.Key;
import net.javayum.spring.environment.property.model.Value;
import net.javayum.spring.environment.property.model.dto.KeyDTO;
import net.javayum.spring.environment.property.model.dto.ValueDTO;
import net.javayum.spring.environment.property.resource.rs.PropertyResourceJAXRS;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import net.javayum.spring.environment.property.model.Property;
import net.javayum.spring.environment.property.resource.PropertyResource;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
        classes = {WebApplicationConfiguration.class}
)
public class IntegrationTests {

    @Autowired
    @Qualifier(PropertyResourceJAXRS.SERVICE_NAME)
    private PropertyResource resource;

    @Test
    public void service_client_calls_method_get() throws Throwable {

        Property actual = resource.get(TestData.KEY);

        Assert.assertEquals(TestData.PROPERTY, actual);
    }

    @Test
    public void service_client_calls_method_create() throws Throwable {

        Key key = KeyDTO.createFrom("key10");
        Value value = ValueDTO.createFrom("value4");
        Property property = resource.create(PropertyEntity.of(key, value));

        Property actual = resource.get(key);

        assertEquals(property, actual);
    }

    @Test
    public void service_client_calls_method_update() throws Throwable {

        Value value = ValueDTO.createFrom("value5");
        Property property = PropertyEntity.of(TestData.KEY, value);

        resource.update(property);

        Property actual = resource.get(TestData.KEY);

        assertEquals(property, actual);
    }

    @Test
    public void service_client_calls_method_delete() throws Throwable {

        resource.delete(TestData.PROPERTY);

        Property actual = resource.get(TestData.KEY);

        assertNull(actual);
    }
}
