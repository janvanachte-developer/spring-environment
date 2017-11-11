package net.javayum.spring.environment.property.model.dto;

import net.javayum.spring.environment.property.model.Value;

import java.io.Serializable;

public class ValueDTO implements Value, Serializable {

    private final String value;

    private ValueDTO(String value) {
        this.value = value;
    }

    public static Value of(String value) {
        return new ValueDTO(value);
    }

    public String toStringValue() {
        return value;
    }
}
