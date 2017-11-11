package net.javayum.spring.environment.property.infrastructure.persistence.jpa;

import net.javayum.spring.environment.property.infrastructure.persistence.PersistenceConfiguration;
import net.javayum.spring.environment.property.model.Key;
import net.javayum.spring.environment.property.model.Property;
import net.javayum.spring.environment.property.model.Value;
import net.javayum.spring.environment.property.model.dto.KeyDTO;
import net.javayum.spring.environment.property.model.dto.ValueDTO;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = PersistenceConfiguration.TABLE_NAME_PROPERTIES)
public class PropertyEntity implements Property {

    private static final String COLUMN_NAME_KEY = "KEY";
    private static final String COLUMN_NAME_VALUE = "VALUE";

    @Id
    @Column(name = COLUMN_NAME_KEY)
    private String key;

    @Column(name = COLUMN_NAME_VALUE)
    private String value;

    protected PropertyEntity(){}

    private PropertyEntity(String key, String value){this.key = key; this.value = value;}

    public static PropertyEntity of(Key key, Value value) { return new PropertyEntity(key.toStringValue(), value.toStringValue());}

    public Key getKey() {
        return KeyDTO.of(key);
    }

    public Value getValue() {
        return ValueDTO.of(value);
    }
}
