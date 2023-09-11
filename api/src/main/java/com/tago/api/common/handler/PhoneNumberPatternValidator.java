package com.tago.api.common.handler;

import com.tago.api.common.annotation.PhoneNumberPattern;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PhoneNumberPatternValidator implements ConstraintValidator<PhoneNumberPattern, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return false;
        }
        return value.matches("(01[016789])-(\\d{3,4})-(\\d{4})");
    }
}
