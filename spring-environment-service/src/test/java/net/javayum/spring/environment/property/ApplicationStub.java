package net.javayum.spring.environment.property;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class ApplicationStub {

    @Autowired
    private Environment environment;

    public String getEnviromentValue(String key) {

        return environment.getProperty(key, "no value");
    }


}
