package net.javayum.spring.environment.property.domain.dto;

import com.fasterxml.jackson.annotation.JsonGetter;
import net.javayum.spring.environment.property.domain.Value;

import java.io.Serializable;

public class ValueDTO implements Value, Serializable {

    private final String value;

    private ValueDTO(String value) {
        this.value = value;
    }

    public static Value createFrom(String value) {
        return new ValueDTO(value);
    }

    @JsonGetter("stringValue")
    public String toStringValue() {
        return value;
    }
}
