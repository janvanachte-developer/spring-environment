package net.javayum.spring.environment.property.resource.rs;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRawValue;
import net.javayum.spring.environment.property.model.Key;
import net.javayum.spring.environment.property.model.Property;
import net.javayum.spring.environment.property.model.Value;
import net.javayum.spring.environment.property.model.dto.KeyDTO;
import net.javayum.spring.environment.property.model.dto.ValueDTO;

import java.util.ArrayList;
import java.util.List;

public class PropertyJSON implements Property {

    @JsonProperty
    public String key;
    @JsonProperty
    public String value;

    private PropertyJSON(Property property) {
        this.key = property.getKey().toStringValue();
        this.value = property.getValue().toStringValue();
    }

    public PropertyJSON(){};

    @JsonIgnore
    @Override
    public Key getKey() {
        return KeyDTO.createFrom(this.key);
    }

    @JsonIgnore
    @Override
    public Value getValue() {
        return ValueDTO.createFrom(this.value);
    }

    public static Property createFrom(Property property) {
        return new PropertyJSON(property);
    }

    public static List<Property> createFrom(List<Property> properties) {
        List<Property> result = new ArrayList<>();
        for ( Property property : properties) {
            result.add(createFrom(property));
        }
        return result;
    }
}
