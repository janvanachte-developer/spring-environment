package net.javayum.spring.environment.property.domain;

public interface Value {

    Value NO_VALUE = new Value() {
        @Override
        public String toStringValue() {
            return "";
        }
    };

    String toStringValue();
}
