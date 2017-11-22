package net.javayum.spring.environment.property;

import cucumber.api.DataTable;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.javayum.spring.environment.property.infrastructure.persistence.jpa.PropertyEntity;
import net.javayum.spring.environment.property.model.Property;
import net.javayum.spring.environment.property.model.dto.KeyDTO;
import net.javayum.spring.environment.property.model.dto.ValueDTO;
import net.javayum.spring.environment.property.resource.PropertyResource;
import net.javayum.spring.environment.property.resource.rs.PropertyResourceJAXRS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;

import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@ContextConfiguration(classes = ApplicationConfiguration.class)
public class AcceptanceTestSteps {

    @Autowired
    private ApplicationStub application;

    @Autowired
    @Qualifier(PropertyResourceJAXRS.SERVICE_NAME)
    private PropertyResource resource;

    private String key;

    @Given("^following key-value properties exist in a database table$")
    public void given_properties_exist_in_table(DataTable dataTable) throws Throwable {

        Map<String, String> properties = dataTable.asMap(String.class, String.class);

        for ( String key : properties.keySet()) {

            Property property = PropertyEntity.of(KeyDTO.of(key), ValueDTO.of(properties.get(key)));

            if ( resource.get(KeyDTO.of(key)) == null ) {
                resource.create(property);
            } else {
                resource.update(property);
            }
        }

        for ( Property property : resource.getAllFromDatabase() ) {
            System.out.println(property.getKey().toStringValue() + "=" + property.getValue().toStringValue());
        }
    }

    @Given("^following key-value properties exist in a property file$")
    public void followingKeyValuePropertiesExistInASpecifiedPropertyFile(DataTable dataTable) throws Throwable {
    }

    @Given("^current Spring environment has following key-value properties$")
    public void current_Spring_environment_has_following_key_value_properties(DataTable dataTable) throws Exception {

        for ( String key : dataTable.asMap(String.class, String.class).keySet()) {

            System.out.println(key + "=" + application.getEnviromentValue(key));
        }
    }

    @When("^an application reads property (.+) from Spring environment$")
    public void gets_property_from_environment(String key) throws Throwable {

        this.key = key;
    }

    @When("^a service client updates property (.+) with (.+)$")
    public void service_update(String key, String value) throws Throwable {

        Property property = PropertyEntity.of(KeyDTO.of(key), ValueDTO.of(value));

        resource.update(property);
    }

    @Then("^this application should get (.+)$")
    public void should_get_value(String value) throws Throwable {

        assertEquals(value, application.getEnviromentValue(key));
    }

    @When("^a service client refreshes property (.+)$")
    public void a_service_client_refreshes_key_key_with_value_value(String key) throws Exception {

        resource.refresh(KeyDTO.of(key));
    }

    @Then("^following key-value properties should exist in a database table$")
    public void then_properties_exist_in_table(DataTable dataTable) throws Throwable {

        Map<String, String> properties = dataTable.asMap(String.class, String.class);

        for ( String key : properties.keySet()) {
            Property actual = resource.get(KeyDTO.of(key));
            assertNotNull("Property " + key + " is not present in table.", actual);

            assertEquals(properties.get(key), actual.getValue().toStringValue());
        }
    }

}
