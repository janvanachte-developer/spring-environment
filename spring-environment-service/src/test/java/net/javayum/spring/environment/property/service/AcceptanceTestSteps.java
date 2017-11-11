package net.javayum.spring.environment.property.service;

import cucumber.api.DataTable;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.javayum.spring.environment.ApplicationConfiguration;
import net.javayum.spring.environment.ApplicationStub;
import net.javayum.spring.environment.property.infrastructure.persistence.jpa.PropertyEntity;
import net.javayum.spring.environment.property.model.Property;
import net.javayum.spring.environment.property.model.dto.KeyDTO;
import net.javayum.spring.environment.property.model.dto.ValueDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

@ContextConfiguration(classes = ApplicationConfiguration.class)
public class AcceptanceTestSteps {

    @Autowired
    private ApplicationStub application;

    @Autowired
    private PropertyService service;

    private String key;

    @Given("^following key-value properties exist in a database table$")
    public void given_properties_exist_in_table(DataTable dataTable) throws Throwable {

//        Map<String, String> properties = dataTable.asMap(String.class, String.class);
//
//        for ( String key : properties.keySet()) {
//
//            Property property = PropertyEntity.of(KeyDTO.of(key), ValueDTO.of(properties.get(key)));
//            service.create(property);
//        }
//
//        List<Property> propertyList = service.getAllFromDatabase();
    }


    @When("^an application reads property (.+) from Spring environment$")
    public void gets_property_from_environment(String key) throws Throwable {

        this.key = key;
    }

    @When("^a service client updates property (.+) with (.+)$")
    public void service_update(String key, String value) throws Throwable {

    }

    @Then("^this application should get (.+)$")
    public void should_get_value(String value) throws Throwable {

        assertEquals(application.getEnviromentValue(key),value);
    }

    @Given("^following key-value properties exist in a property file$")
    public void followingKeyValuePropertiesExistInASpecifiedPropertyFile(DataTable dataTable) throws Throwable {
    }

    @Given("^current Spring environment has following key-value properties$")
    public void current_Spring_environment_has_following_key_value_properties(DataTable arg1) throws Exception {
        // Write code here that turns the phrase above into concrete actions
        // For automatic transformation, change DataTable to one of
        // List<YourType>, List<List<E>>, List<Map<K,V>> or Map<K,V>.
        // E,K,V must be a scalar (String, Integer, Date, enum etc)
//        throw new PendingException();
    }

    @When("^a service client refreshes property (.+)$")
    public void a_service_client_refreshes_key_key_with_value_value(String key) throws Exception {

        service.refresh(KeyDTO.of(key));
    }
}
