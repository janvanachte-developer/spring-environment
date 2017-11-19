package net.javayum.spring.environment.property.property.service;

import net.javayum.spring.environment.property.property.infrastructure.persistence.jpa.PropertyEntity;
import net.javayum.spring.environment.property.property.model.Key;
import net.javayum.spring.environment.property.property.model.Property;
import net.javayum.spring.environment.property.property.model.Value;
import net.javayum.spring.environment.property.property.model.dto.KeyDTO;
import net.javayum.spring.environment.property.property.model.dto.ValueDTO;
import net.javayum.spring.environment.property.property.service.rs.PropertyServiceJAXRSConfiguration;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
        classes = {PropertyServiceJAXRSConfiguration.class}
)
public class IntegrationTests {

    @Autowired
    @Qualifier(PropertyServiceJAXRSConfiguration.SERVICE_NAME)
    private PropertyService service;

    @Test
    public void service_client_calls_method_get() throws Throwable {

        Property actual = service.get(TestData.KEY);

        Assert.assertEquals(TestData.PROPERTY, actual);
    }

    @Test
    public void service_client_calls_method_create() throws Throwable {

        Key key = KeyDTO.of("key10");
        Value value = ValueDTO.of("value4");
        Property property = service.create(PropertyEntity.of(key, value));

        Property actual = service.get(key);

        assertEquals(property, actual);
    }

    @Test
    public void service_client_calls_method_update() throws Throwable {

        Value value = ValueDTO.of("value5");
        Property property = PropertyEntity.of(TestData.KEY, value);

        service.update(property);

        Property actual = service.get(TestData.KEY);

        assertEquals(property, actual);
    }

    @Test
    public void service_client_calls_method_delete() throws Throwable {

        service.delete(TestData.PROPERTY);

        Property actual = service.get(TestData.KEY);

        assertNull(actual);
    }
}
