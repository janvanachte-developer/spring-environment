package net.javayum.spring.environment.property.infrastructure.persistence.jpa;

import net.javayum.spring.environment.property.model.Key;
import net.javayum.spring.environment.property.model.Value;
import net.javayum.spring.environment.property.model.dto.KeyDTO;
import net.javayum.spring.environment.property.model.Property;
import net.javayum.spring.environment.property.model.dto.ValueDTO;

import javax.persistence.*;

@Entity
@Table(name = PropertyRepositoryConfiguration.TABLE_NAME_PROPERTIES)
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

    @Transient
    public Key getKey() {
        return KeyDTO.createFrom(key);
    }

    @Transient
    public Value getValue() {
        return ValueDTO.createFrom(value);
    }

    @Override
    public String toString() {

        return key + "=" + value;
    }

    @Override
    public boolean equals(Object object) {

        boolean result = false;

        if ( object instanceof Property ) {

            Property property = (Property) object;

            if ( property.getKey().toStringValue().equals(key) && property.getValue().toStringValue().equals(value)) {
                result = true;
            }
        }

        return result;
    }

    @Override
    public int hashCode() {

        return key.hashCode();
    }
}