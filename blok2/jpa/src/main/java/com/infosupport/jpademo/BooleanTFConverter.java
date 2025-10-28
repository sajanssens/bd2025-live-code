package com.infosupport.jpademo;

import jakarta.persistence.AttributeConverter;

public class BooleanTFConverter implements AttributeConverter<Boolean, String> {

    @Override
    public String convertToDatabaseColumn(Boolean attribute) {
        if (attribute == null) {
            return "F";
        }

        if (attribute) {
            return "T";
        } else {
            return "F";
        }
    }

    @Override
    public Boolean convertToEntityAttribute(String dbData) {
        return "T".equalsIgnoreCase(dbData);
    }
}
