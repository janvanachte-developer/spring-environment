package net.javayum.spring.environment.property.model.dto;

import com.fasterxml.jackson.annotation.JsonGetter;
import net.javayum.spring.environment.property.model.Key;

import java.io.Serializable;

public class KeyDTO implements Key, Serializable {

    final private String key;

    private KeyDTO(String key) {
        this.key = key;
    }

    public static Key of(String key) {
        return new KeyDTO(key);
    }

    @JsonGetter("stringValue")
    public String toStringValue() {
        return key;
    }
}