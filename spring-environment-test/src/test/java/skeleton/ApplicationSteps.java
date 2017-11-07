package skeleton;

import cucumber.api.DataTable;
import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.javayum.spring.environment.Application;
import net.javayum.spring.environment.ApplicationConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import static org.junit.Assert.assertEquals;

@ContextConfiguration(classes = ApplicationConfiguration.class)
public class ApplicationSteps {

    @Autowired
    private Application application;

    private String key;

    @Given("^Following key-value properties exist in a specified database table$")
    public void given_properties_exist_in_table(DataTable dataTable) throws Throwable {
    }


    @When("^Application reads key (.+) from Spring environment$")
    public void gets_property_from_environment(String key) throws Throwable {

        this.key = key;
    }

    @Then("^Application should get value (.+)$")
    public void should_get_value(String value) throws Throwable {

        System.out.println("then " + value);
        assertEquals(application.getEnviromentValue(key),value);

    }

    @Given("^Following key-value properties exist in a specified property file$")
    public void followingKeyValuePropertiesExistInASpecifiedPropertyFile(DataTable dataTable) throws Throwable {
    }
}
