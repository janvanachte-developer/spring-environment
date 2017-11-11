package net.javayum.spring.environment.property.model.dto;

import net.javayum.spring.environment.property.model.Key;

public class KeyDTO implements Key {

    final private String key;

    private KeyDTO(String key) {
        this.key = key;
    }

    public static Key of(String key) {
        return new KeyDTO(key);
    }

    public String toStringValue() {
        return key;
    }
}
